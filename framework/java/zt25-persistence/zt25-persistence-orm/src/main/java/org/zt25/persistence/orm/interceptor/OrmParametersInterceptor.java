package org.zt25.persistence.orm.interceptor;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.zt25.converter.enums.SupportDataConverter;
import org.zt25.core.ExceptionFactory;
import org.zt25.persistence.DefaultValueFillStrategy;
import org.zt25.persistence.PersistenceException;
import org.zt25.persistence.annotation.DefaultValue;
import org.zt25.persistence.annotation.PrimaryKey;
import org.zt25.persistence.domain.DO;
import org.zt25.persistence.enums.PersistenceError;
import org.zt25.persistence.enums.PkType;
import org.zt25.persistence.orm.OrmEntity;
import org.zt25.persistence.orm.SqlCommandTypeTranslator;
import org.zt25.persistence.orm.config.OrmProperties;
import org.zt25.persistence.orm.generator.DefaultPrimaryKeyGenerator;
import org.zt25.tools.SpringUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * 公共参数处理拦截器
 *
 * <p>
 * MongoDB数据库的一种唯一ID生成策略，是UUID version1的变种
 * </p>
 *
 * @author zhaotaiyu
 * @version 1.0
 * @since 2023/3/3 9:58
 **/
@Component
@EnableConfigurationProperties(OrmProperties.class)
@Intercepts({
        //type指定代理的是那个对象，method指定代理Executor中的那个方法,args指定Executor中的query方法都有哪些参数对象
        //由于Executor中有两个query，因此需要两个@Signature
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),//需要代理的对象和方法
})
@RequiredArgsConstructor
@Slf4j
public class OrmParametersInterceptor implements Interceptor {

    private final OrmProperties ormProperties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        this.analyze(invocation.getArgs());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }

    private void analyze(Object[] params) throws PersistenceException {
        if (params == null || params.length < 2 || !(params[0] instanceof MappedStatement) || params[1] == null) {
            log.debug("参数为空或者少于两个或者第一个参数不是MappedStatement或者第二个参数为空, 直接返回");
        } else {
            SqlCommandType type = ((MappedStatement) params[0]).getSqlCommandType();
            switch (type) {
                case INSERT, UPDATE -> {
                    if (params[1] instanceof OrmEntity<?>) {
                        log.debug("参数是一个BaseEntity或其子类的实例");
                        fillParameters((OrmEntity) params[1], type);
                    }
                }
                default -> log.debug("无需处理的SQL类型");
            }
        }
    }

    /**
     * 填充主键
     */
    private void fillParameters(OrmEntity param, SqlCommandType commandType) throws PersistenceException {
        if (commandType == SqlCommandType.INSERT) {
            log.debug("插入时填充主键");
            this.fillForPk(param);
        }
        this.fillDefaultValue(param,commandType);
    }

    /**
     * 主键填充
     *
     * @param entity {@link OrmEntity}
     */
    private void fillForPk(OrmEntity entity) throws PersistenceException {
        log.debug("插入时生成主键数据");
        if (StrUtil.isBlankIfStr(entity.getId())) {
            PrimaryKey primaryKey = entity.getClass().getAnnotation(PrimaryKey.class);
            PkType pkType = null;

            Object pk = null;

            if (ObjectUtil.isNotNull(primaryKey)) {
                pkType = primaryKey.type();
            } else {
                pkType = ormProperties.getPkType();
            }

            if(pkType==PkType.CUSTOM){
                pk = SpringUtil.getBean(primaryKey.generator()).getPk();
            }else{
                pk = DefaultPrimaryKeyGenerator.valueOf(pkType.getCode().toUpperCase()).get();
            }

            setIdField(entity, pk);
        }
    }

    /**
     * 填充默认值
     *
     * @param target {@link OrmEntity}
     */
    private void fillDefaultValue(OrmEntity target, SqlCommandType commandType) {
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            DefaultValue defaultValue = field.getAnnotation(DefaultValue.class);
            if (ObjectUtil.isNull(defaultValue) || defaultValue.commandType() != SqlCommandTypeTranslator.translate(commandType)) {
                continue;
            }

            try{
                field.setAccessible(true);
                if( ReflectionUtils.getField(field, target) != null){
                    continue;
                }
                if(!ClassUtil.isInterface(defaultValue.strategyClass())){
                    DefaultValueFillStrategy strategy = SpringUtil.getBean(defaultValue.strategyClass());
                    ReflectionUtils.setField(field, target, strategy.value(target));
                }else{
                    ReflectionUtils.setField(field, target, SupportDataConverter.valueOf(field.getType().getSimpleName().toUpperCase()).convert(defaultValue.value()));
                }
            }catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(field.getType().getSimpleName() + "类型的数据暂未支持设置默认值");
            }

        }
    }

    /**
     * 设置属性
     */
    private void setIdField(OrmEntity target, Object value) throws PersistenceException {
        try {
            Field field = ReflectUtil.getField(target.getClass(), DO.Fields.id);
            field.setAccessible(true);
            String pkClass = target.pkClass();
            if (CharSequenceUtil.equalsIgnoreCase(pkClass, Long.class.getName())) {
                field.set(target, Long.valueOf(value.toString()));
            } else if (CharSequenceUtil.equalsIgnoreCase(pkClass, String.class.getName())) {
                field.set(target, value.toString());
            } else {
                throw ExceptionFactory.instance(PersistenceException.class, PersistenceError.UNKNOWN_PK_TYPE, String.format(PersistenceError.UNKNOWN_PK_TYPE.getDesc(), pkClass));
            }
            log.debug("设置属性{}成功，值为{}", DO.Fields.id, value);
        } catch (IllegalAccessException e) {
            log.warn("访问不了{}是什么情况", DO.Fields.id);
            throw ExceptionFactory.instance(PersistenceException.class, PersistenceError.PROPERTY_NOT_VISIBLE, String.format(PersistenceError.PROPERTY_NOT_VISIBLE.getDesc(), DO.Fields.id));
        }
    }
}

package org.zt25.persistence.orm.wrapper;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.yulichang.query.MPJQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.zt25.persistence.annotation.Condition;
import org.zt25.persistence.annotation.OrderBy;
import org.zt25.persistence.annotation.OrderByField;
import org.zt25.persistence.domain.CO;
import org.zt25.persistence.domain.DO;
import org.zt25.persistence.enums.Connector;
import org.zt25.persistence.enums.OrderByMode;
import org.zt25.persistence.orm.ColumnTranslator;
import org.zt25.persistence.orm.OrmEntity;
import org.zt25.persistence.orm.annotation.Join;
import org.zt25.persistence.orm.annotation.Relation;
import org.zt25.persistence.orm.annotation.ResultField;
import org.zt25.persistence.orm.annotation.SubCond;
import org.zt25.persistence.orm.enums.JoinMode;
import org.zt25.persistence.orm.enums.JoinSqlOperatorClause;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * mybatis-plus查询构造器
 *
 * <p>
 * </p>
 *
 * @author zhaotaiyu
 * @version 1.0
 * @since 2023/9/22 19:58
 **/
@Slf4j
@Component
public class JoinQueryWrapperCreator<E extends OrmEntity<? extends Serializable>> implements MybatisPlusWrapperCreator<E, MPJQueryWrapper<E>> {

    @Override
    public MPJQueryWrapper<E> create(Class<E> entityType, CO co) {
        Assert.notNull(entityType, "entityType不能为空");
        Assert.notNull(co, "条件对象不能为空");

        MPJQueryWrapper<E> wrapper = new MPJQueryWrapper<>();
        return this.create(entityType, wrapper, co);
    }

    @Override
    public MPJQueryWrapper<E> create(Class<E> entityType, MPJQueryWrapper<E> wrapper, CO co) {
        Assert.notNull(entityType, "entityType不能为空");
        Assert.notNull(co, "条件对象不能为空");

        if (ObjectUtil.isNull(wrapper)) {
            return this.create(entityType, co);
        }

        // 设置查询属性.主类查询全部
        wrapper.selectAll(entityType);

        // 获取条件对象中join注解
        Relation relation = co.getClass().getAnnotation(Relation.class);
        Map<Class<?>,String> classMap = new HashMap<>();
        if(ObjectUtil.isNotNull(relation)){
            for(Join join:relation.join()){
                classMap.put(join.entity(),CharSequenceUtil.isEmpty(join.alias()) ? join.entity().getSimpleName() : join.alias());
            }
        }

        // 关联类查询字段, 从VO中获取
        this.analyzeFieldClause(wrapper, relation);
        // 处理join条件
        this.analyzeJoinClause(entityType, wrapper, co.getClass().getAnnotation(Relation.class));

        // 获取当前条件对象中的所有属性列表
        Field[] fields = co.getClass().getDeclaredFields();

        // 条件属性集合
        List<Field> condFields = new ArrayList<>();

        Map<String, List<Field>> subCondMap = new HashMap<>();

        // 排序属性
        Field orderByField = null;

        // 循环所有属性,获取值不为null的属性.null值属性不做处理
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if (ObjectUtil.isNotNull(field.get(co))) {
                    SubCond subCond = field.getAnnotation(SubCond.class);
                    Condition condition = field.getAnnotation(Condition.class);
                    OrderBy orderBy = field.getAnnotation(OrderBy.class);
                    if(ObjectUtil.isNotNull(orderBy)){
                        orderByField = field;
                    }else{
                        if (ObjectUtil.isNull(subCond)) {
                            condFields.add(field);
                        } else {
                            if (ObjectUtil.isNull(subCondMap.get(subCond.group()))) {
                                List<Field> subCondFields = new ArrayList<>();
                                subCondFields.add(field);
                                subCondMap.put(subCond.group(), subCondFields);
                            } else {
                                subCondMap.get(subCond.group()).add(field);
                            }

                        }
                    }

                }
            } catch (IllegalAccessException e) {
                log.error("解析条件对象属性异常:", e);
            }
        }

        List<SubCondObj> subConds = new ArrayList<>();
        Map<String, SubCondObj> subCondObjMap = new HashMap<>();

        Iterator<Map.Entry<String, List<Field>>> iterator = subCondMap.entrySet().iterator();

        while (MapUtil.isNotEmpty(subCondMap)) {

            Map.Entry<String, List<Field>> entry = iterator.next();
            SubCond sc = entry.getValue().get(0).getAnnotation(SubCond.class);
            if (CharSequenceUtil.isBlank(sc.superGroup())) {
                SubCondObj subCondObj = new SubCondObj();
                subCondObj.setSubCond(sc);
                subCondObj.setFields(entry.getValue());
                //subConds.add(subCondObj);
                subCondObjMap.put(sc.group(), subCondObj);
                iterator.remove();
                iterator = subCondMap.entrySet().iterator();
            } else {
                if (ObjectUtil.isNotNull(subCondObjMap.get(sc.superGroup()))) {
                    SubCondObj subCondObj = new SubCondObj();
                    subCondObj.setSubCond(sc);
                    subCondObj.setFields(entry.getValue());

                    subCondObjMap.get(sc.superGroup()).getChildSubCondObjs().add(subCondObj);

                    subCondObjMap.put(sc.group(), subCondObj);
                    iterator.remove();
                    iterator = subCondMap.entrySet().iterator();
                }
            }
        }

        for (Map.Entry<String, SubCondObj> entry : subCondObjMap.entrySet()) {
            if (CharSequenceUtil.isBlank(entry.getValue().getSubCond().superGroup())) {
                subConds.add(entry.getValue());
            }
        }

        this.analyzeConditionClause(entityType, wrapper, co, condFields, relation);
        this.analyzeSubConditionClause(entityType, wrapper, co, subConds, relation);

        // TODO orderBy 处理.待完善!!!!!!!!!!
        this.analyzeOrderByClause(wrapper, co,classMap, orderByField);
        return wrapper;
    }

    // 关联查询的属性处理
    private void analyzeFieldClause(MPJQueryWrapper<E> wrapper, Relation relation) {
        if(!Class.class.equals(relation.result())){
            // 循环join,获取关联实体的信息
            for (Join join : relation.join()) {
                // 获取vo对象的属性集合
                Field[] fields = relation.result().getDeclaredFields();
                // 循环判断每个属性中是否包含ResultField注解.用于拼接sql中的关联表查询属性的处理
                for (Field field : fields) {
                    ResultField rf = field.getAnnotation(ResultField.class);
                    // 判断当前属性的对应的实体类是否与join的实体是同一个,用户获取join中对应的实体别名数据
                    if (ObjectUtil.isNotNull(rf) && (join.entity().equals(rf.clazz()))) {
                        wrapper.select(CharSequenceUtil.isEmpty(join.alias()) ? join.entity().getSimpleName() : join.alias() + "." + ColumnTranslator.translatePropertyToColumn(rf.clazz(), rf.field()) + " as " + field.getName());
                    }
                }
            }
        }
    }

    // 处理查询条件
    private MPJQueryWrapper<E> analyzeConditionClause(Class<E> entityType, MPJQueryWrapper<E> wrapper, CO co, List<Field> fields, Relation relation) {

        // 对属性的连接符进行排序.and在前,or在后.避免因顺序导致or连接无法正常拼接
        //List<Field> orFields = new ArrayList<>();
        //List<Field> orderFields = new ArrayList<>();
        //for(Field field:fields){
        //    Condition cond = field.getAnnotation(Condition.class);
        //    if(cond.connector().equals(Connector.OR)){
        //        orFields.add(field);
        //    }else{
        //        orderFields.add(field);
        //    }
        //}
        //orderFields.addAll(orFields);

        for (Field field : fields) {
            Condition cond = field.getAnnotation(Condition.class);

            String alias = wrapper.getAlias();
            Class<?> entityClass = entityType;
            // 如果未制定对应的实体类型,则按照当前wrapper中的实体类型处理.DO.class为默认类型
            if (!cond.entity().equals(DO.class)) {
                for (Join join : relation.join()) {
                    if (cond.entity().equals(join.entity())) {
                        alias = CharSequenceUtil.isEmpty(join.alias()) ? join.entity().getSimpleName() : join.alias();
                        entityClass = join.entity();
                    }
                }
            }
            JoinSqlOperatorClause.valueOf(cond.clause().name().toUpperCase()).build(wrapper, entityClass, co, field, alias);
        }
        return wrapper;
    }

    private MPJQueryWrapper<E> analyzeSubConditionClause(Class<E> entityType, MPJQueryWrapper<E> wrapper, CO co, List<SubCondObj> subConds, Relation relation) {
        if (ObjectUtil.isNotNull(subConds)) {
            for (SubCondObj sc : subConds) {
                if (sc.getSubCond().connector().equals(Connector.OR)) {
                    wrapper.or(w -> this.analyzeConditionClause(entityType, w, co, sc.getFields(), relation));
                } else {
                    wrapper.and(w -> this.analyzeConditionClause(entityType, w, co, sc.getFields(), relation));
                }
                if (!sc.getChildSubCondObjs().isEmpty()) {
                    if (sc.getSubCond().connector().equals(Connector.OR)) {
                        wrapper.or(w -> this.analyzeSubConditionClause(entityType, w, co, sc.getChildSubCondObjs(), relation));
                    } else {
                        wrapper.and(w -> this.analyzeSubConditionClause(entityType, w, co, sc.getChildSubCondObjs(), relation));
                    }
                }
            }
        }
        return wrapper;
    }

    private void analyzeOrderByClause(MPJQueryWrapper<E> wrapper, CO co,Map<Class<?>,String> classMap,Field orderByField){

        OrderBy ob = orderByField.getAnnotation(OrderBy.class);
        try {
            LinkedHashMap<String, OrderByMode> orderByList = (LinkedHashMap<String, OrderByMode>) orderByField.get(co);
            for(OrderByField obf:ob.fields()){
                String field = CharSequenceUtil.isEmpty(obf.alias()) ? obf.field() : obf.alias();
                String column = ColumnTranslator.translatePropertyToColumn(obf.entity(), obf.field());
                String alias = ObjectUtil.isNotNull(classMap.get(obf.entity()))?classMap.get(obf.entity()):wrapper.getAlias();
                OrderByMode mode = ObjectUtil.isNotNull(orderByList.get(field))?orderByList.get(field):obf.defaultMode();
                if(mode.equals(OrderByMode.ASC)){
                    wrapper.orderByAsc(alias+"."+column);
                }else{
                    wrapper.orderByDesc(alias+"."+column);
                }
            }
        } catch (IllegalAccessException e) {
            log.error("解析条件对象属性异常:", e);
        }

    }

    // join的处理
    private void analyzeJoinClause(Class<E> entityType, MPJQueryWrapper<E> wrapper, Relation relation) {
        for (Join join : relation.join()) {
            JoinMode.valueOf(join.mode().name().toUpperCase()).build(entityType, wrapper, join);
        }
    }

}

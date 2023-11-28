package org.zt25.persistence.orm.wrapper;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.zt25.persistence.domain.CO;
import org.zt25.persistence.orm.OrmEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Serializable;

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
public class QueryWrapperCreator<E extends OrmEntity<? extends Serializable>> implements MybatisPlusWrapperCreator<E, QueryWrapper<E>> {

    @Override
    public QueryWrapper<E> create(Class<E> entityType, CO co) {
        Assert.notNull(entityType, "entityType不能为空");
        Assert.notNull(co, "条件对象不能为空");

        QueryWrapper<E> wrapper = new QueryWrapper<>();

        return this.create(entityType,wrapper,co);
    }

    @Override
    public QueryWrapper<E> create(Class<E> entityType, QueryWrapper<E> wrapper, CO co) {
        Assert.notNull(entityType, "entityType不能为空");
        Assert.notNull(co, "条件对象不能为空");

        if (ObjectUtil.isNull(wrapper)) {
            return this.create(entityType, co);
        }

        // TODO 处理wrapper的拼接

        return wrapper;
    }

}

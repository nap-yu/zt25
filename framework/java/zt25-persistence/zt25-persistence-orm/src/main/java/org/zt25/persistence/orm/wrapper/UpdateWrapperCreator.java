package org.zt25.persistence.orm.wrapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.zt25.persistence.domain.CO;

/**
 * mybatis-plus更新构造器
 *
 * <p>
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/9/22 19:58
 * @version 1.0
 **/
public final class UpdateWrapperCreator<E> implements MybatisPlusWrapperCreator<E, UpdateWrapper<E>> {


    @Override
    public UpdateWrapper<E> create(Class<E> entityType, CO qo) {
        return null;
    }

    @Override
    public UpdateWrapper<E> create(Class<E> entityType, UpdateWrapper<E> wrapper, CO qo) {
        return null;
    }
}

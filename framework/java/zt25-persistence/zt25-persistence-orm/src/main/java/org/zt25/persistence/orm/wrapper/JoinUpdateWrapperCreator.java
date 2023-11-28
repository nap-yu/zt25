package org.zt25.persistence.orm.wrapper;

import com.github.yulichang.wrapper.UpdateJoinWrapper;
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
public final class JoinUpdateWrapperCreator<E> implements MybatisPlusWrapperCreator<E, UpdateJoinWrapper<E>> {


    @Override
    public UpdateJoinWrapper<E> create(Class<E> entityType, CO qo) {
        return null;
    }

    @Override
    public UpdateJoinWrapper<E> create(Class<E> entityType, UpdateJoinWrapper<E> wrapper, CO qo) {
        return null;
    }
}

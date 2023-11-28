package org.zt25.persistence.orm.wrapper;

import com.github.yulichang.wrapper.DeleteJoinWrapper;
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
public final class JoinDeleteWrapperCreator<E> implements MybatisPlusWrapperCreator<E, DeleteJoinWrapper<E>> {

    @Override
    public DeleteJoinWrapper<E> create(Class<E> entityType, CO qo) {
        return null;
    }

    @Override
    public DeleteJoinWrapper<E> create(Class<E> entityType, DeleteJoinWrapper<E> wrapper, CO qo) {
        return null;
    }
}

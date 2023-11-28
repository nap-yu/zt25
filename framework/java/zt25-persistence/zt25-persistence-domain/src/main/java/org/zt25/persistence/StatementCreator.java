package org.zt25.persistence;


import org.zt25.persistence.domain.CO;

/**
 * 创建查询构造器接口
 *
 * <p>
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/9/22 19:58
 * @version 1.0
 **/
public interface StatementCreator <E, W> {

    /**
     * 根据QO创建对应的wrapper对象
     *
     * @param entityType 实体类型
     * @param co       {@link  CO}
     */
    W create(Class<E> entityType, CO co);

    /**
     * 根据QO 和 固定wrapper 创建Wrapper
     * @param entityType 实体类型
     * @param wrapper    查询构造器
     * @param co       {@link  CO}
     */
    W create(Class<E> entityType, W wrapper, CO co);
}

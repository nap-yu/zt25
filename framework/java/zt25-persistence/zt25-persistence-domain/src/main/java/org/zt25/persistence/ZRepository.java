package org.zt25.persistence;


import org.zt25.core.ZObject;

import java.io.Serializable;
import java.util.List;


/**
 * 仓储类型接口定义
 *
 * <p>
 * 仓储类型接口
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
public interface ZRepository<T extends ZObject> {

    /**
     * 通过主键查询
     * @param id 主键
     * @return 实体对象
     */
    T get(Serializable id) throws PersistenceException;

    /**
     * 新增
     * @param entity 实体对象
     * @return 实体对象
     */
    T add(T entity) throws PersistenceException;

    /**
     * 新增(批量)
     * @param entities 实体对象集合
     * @return 实体对象
     */
    List<T> addBatch(List<T> entities) throws PersistenceException;

    /**
     * 更新
     * @param entity 实体对象
     * @return 实体对象
     */
    T modify(T entity) throws PersistenceException;

    /**
     * 更新(批量)
     * @param entities 实体对象集合
     * @return 实体对象
     */
    List<T> saveBatch(List<T> entities) throws PersistenceException;

    /**
     *  新增及修改
     *  根据主键判断,主键为空、空字符串、null时新增否则根据主键修改
     * @param entity 实体对象
     * @return 实体对象
     */
    T upsert(T entity) throws PersistenceException;

    /**
     *  新增及修改(批量)
     *  根据主键判断,主键为空、空字符串、null时新增否则根据主键修改
     * @param entities 实体对象集合
     * @return 实体对象
     */
    List<T> upsertBatch(List<T> entities) throws PersistenceException;

    /**
     * 根据主键删除
     * @param id 主键
     * @return {@link Boolean}
     */
    boolean remove(Serializable id) throws PersistenceException;

    /**
     * 根据实体对象删除
     * @param entity 实体对象
     * @return {@link Boolean}
     */
    boolean remove(T entity) throws PersistenceException;

    /**
     * 根据实体对象集合删除
     * @param entities 实体对象集合
     * @return {@link Boolean}
     */
    boolean remove(List<T> entities) throws PersistenceException;

}

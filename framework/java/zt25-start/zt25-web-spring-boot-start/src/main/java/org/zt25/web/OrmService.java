package org.zt25.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import org.zt25.persistence.PersistenceException;
import org.zt25.persistence.domain.CO;
import org.zt25.persistence.enums.PersistenceError;
import org.zt25.persistence.orm.OrmEntity;
import org.zt25.persistence.orm.OrmRepository;
import org.zt25.persistence.orm.annotation.Relation;
import org.zt25.persistence.orm.wrapper.JoinQueryWrapperCreator;
import org.zt25.web.domain.ZPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Transactional(
        rollbackFor = {Exception.class}
)
public abstract class OrmService<E extends OrmEntity<? extends Serializable>, R extends OrmRepository<E>> {

    @Autowired(required = false)
    protected R repository;

    @Autowired(required = false)
    private JoinQueryWrapperCreator wrapperCreator;

    /**
     * 获取当前实体类型
     * @return Class<E> 实体类型
     * @throws PersistenceException
     */
    protected Class<E> getEntityType() throws PersistenceException {
        Type type = repository.getEntityClass();
        String typeName = type.getTypeName();
        try {
            return (Class<E>) Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            throw new PersistenceException(PersistenceError.GET_ENTITY_TYPE_EXCEPTION);
        }
    }

    /**
     * 通过主键查询
     * @param id 主键
     * @return 实体对象
     */
    @Transactional(readOnly = true)
    public E get(Serializable id) {
        return this.repository.get(id);
    }

    /**
     * 条件查询单条数据
     * @param co 条件查询对象
     * @return V
     * @param <C> 条件对象泛型类型
     * @param <V> 视图对象
     */
    @Transactional(readOnly = true)
    public <C extends CO, V> V findOne(C co) {
        // 获取条件对象上的注解
        Relation relation = co.getClass().getAnnotation(Relation.class);
        // 通过条件对象创建wrapper
        MPJQueryWrapper<E> wrapper = wrapperCreator.create(this.getEntityType(),co);
        // 获取返回对象类型
        Class<?> voClass = relation.result();
        // 如果没有定义返回对象类型,则默认使用当前实体对象
        if(voClass.equals(Class.class)){
            return (V) this.repository.getMapper().selectJoinOne(this.getEntityType(),wrapper);
        }else{
            return (V) this.repository.getMapper().selectJoinOne(voClass,wrapper);
        }
    }

    /**
     * 条件查询单条数据
     * @param co 条件查询对象
     * @return Map<String,Object>
     * @param <C> 条件对象泛型类型
     */
    @Transactional(readOnly = true)
    public <C extends CO> Map<String,Object> findOneForMap(C co) {
        // 获取条件对象上的注解
        Relation relation = co.getClass().getAnnotation(Relation.class);
        // 通过条件对象创建wrapper
        MPJQueryWrapper<E> wrapper = wrapperCreator.create(this.getEntityType(),co);
        return this.repository.getMapper().selectJoinMap(wrapper);
    }

    /**
     * 条件查询数据
     * @param co 条件查询对象
     * @return List<V>
     * @param <C> 条件对象泛型类型
     * @param <V> 视图对象
     */
    @Transactional(readOnly = true)
    public <C extends CO, V> List<V> find(C co){
        // 获取条件对象上的注解
        Relation relation = co.getClass().getAnnotation(Relation.class);
        // 通过条件对象创建wrapper
        MPJQueryWrapper<E> wrapper = wrapperCreator.create(this.getEntityType(),co);
        // 获取返回对象类型
        Class<?> voClass = relation.result();
        // 如果没有定义返回对象类型,则默认使用当前实体对象
        if(voClass.equals(Class.class)){
            return (List<V>) this.repository.getMapper().selectJoinList(this.getEntityType(),wrapper);
        }else{
            return (List<V>) this.repository.getMapper().selectJoinList(voClass,wrapper);
        }
    }

    /**
     * 条件查询数据
     * @param co 条件查询对象
     * @return Map<String,Object>
     * @param <C> 条件对象泛型类型
     */
    @Transactional(readOnly = true)
    public <C extends CO> List<Map<String,Object>> findForMap(C co){
        // 获取条件对象上的注解
        Relation relation = co.getClass().getAnnotation(Relation.class);
        // 通过条件对象创建wrapper
        MPJQueryWrapper<E> wrapper = wrapperCreator.create(this.getEntityType(),co);
        return this.repository.getMapper().selectJoinMaps(wrapper);
    }

    /**
     * 分页查询
     * @param co 条件查询对象
     * @param page 分页对象
     * @return PPage<V>
     * @param <C> 条件对象泛型类型
     * @param <V> 视图对象
     */
    @Transactional(readOnly = true)
    public <C extends CO, V> ZPage<V> page(C co, ZPage page){
        // 获取条件对象上的注解
        Relation relation = co.getClass().getAnnotation(Relation.class);
        // 通过条件对象创建wrapper
        MPJQueryWrapper<E> wrapper = wrapperCreator.create(this.getEntityType(),co);
        // 获取返回对象类型
        Class<?> voClass = relation.result();

        Page p = new Page<>(page.getCurrent(),page.getSize());

        // 如果没有定义返回对象类型,则默认使用当前实体对象
        if(voClass.equals(Class.class)){
            this.repository.getMapper().selectJoinPage(p,this.getEntityType(),wrapper);
        }else{
            this.repository.getMapper().selectJoinPage(new Page<>(page.getCurrent(),page.getSize()),voClass,wrapper);
        }
        page.setTotal(p.getTotal()).setPages(p.getPages()).setData((List<V>) p.getRecords());
        return page;
    }

    /**
     * 分页查询
     * @param co 条件查询对象
     * @param page 分页对象
     * @return PPage<Map<String,Object>>
     * @param <C> 条件对象泛型类型
     */
    @Transactional(readOnly = true)
    public <C extends CO> ZPage<Map<String,Object>> pageForMap(C co, ZPage<Map<String, Object>> page){
        // 获取条件对象上的注解
        Relation relation = co.getClass().getAnnotation(Relation.class);
        // 通过条件对象创建wrapper
        MPJQueryWrapper<E> wrapper = wrapperCreator.create(this.getEntityType(),co);

        Page<Map<String, Object>> p = new Page<>(page.getCurrent(),page.getSize());

        this.repository.getMapper().selectJoinMapsPage(p,wrapper);

        page.setTotal(p.getTotal()).setPages(p.getPages()).setData(p.getRecords());

        return page;

    }

    /**
     * 保存
     * 有主键时更新,否则新增
     * @param entity 实体
     * @return E
     * @throws PersistenceException
     */
    public E save(E entity) throws PersistenceException {
        return this.repository.upsert(entity);
    }

    /**
     * 批量保存.使用循环方式
     * 有主键时更新,否则新增
     * @param entities 实体集合
     * @return List<E>
     * @throws PersistenceException
     */
    public List<E> saveBatch(List<E> entities) throws PersistenceException {
        return this.repository.saveBatch(entities);
    }

    /**
     * 通过实体删除
     * @param entity 实体
     * @return boolean
     * @throws PersistenceException
     */
    public boolean delete(E entity) throws PersistenceException {
        return this.repository.remove(entity);
    }

    /**
     * 通过主键删除
     * @param id 主键
     * @return boolean
     * @throws PersistenceException
     */
    public boolean delete(Serializable id) throws PersistenceException {
        return this.repository.remove(id);
    }

    /**
     * 通过实体集合批量删除
     * @param entities 实体集合
     * @return boolean
     * @throws PersistenceException
     */
    public boolean delete(List<E> entities) throws PersistenceException {
        return this.repository.remove(entities);
    }

    /**
     * 通过主键集合批量删除
     * @param ids 主键集合
     * @return boolean
     * @throws PersistenceException
     */
    public boolean deleteByIds(List<Serializable> ids) throws PersistenceException {
        int i = this.repository.getMapper().deleteBatchIds(ids);
        if(i==ids.size()){
            return true;
        }else{
            throw new PersistenceException(PersistenceError.DELETE_FAILED,"部分数据删除失败");
        }
    }

}

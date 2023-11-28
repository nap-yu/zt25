package org.zt25.persistence.orm;

import cn.hutool.core.util.ObjectUtil;
import org.mybatis.spring.MyBatisSystemException;
import org.zt25.core.ExceptionFactory;
import org.zt25.persistence.ZRepository;
import org.zt25.persistence.PersistenceException;
import org.zt25.persistence.enums.PersistenceError;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ORM仓储基类
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
public interface OrmRepository<E extends OrmEntity<? extends Serializable>> extends ZRepository<E> {

    Class<E> getEntityClass();

    OrmMapper<E> getMapper();

    @Override
    default E get(Serializable id) throws PersistenceException {
        try{
            return this.getMapper().selectById(id);
        }catch (MyBatisSystemException e){
            throw new PersistenceException(e.getMostSpecificCause().getMessage());
        }catch (Exception e){
            throw new PersistenceException(e);
        }
    }

    @Override
    default E add(E entity) throws PersistenceException {
        try{
            int result = this.getMapper().insert(entity);
            if(result<=0){
                throw ExceptionFactory.instance(PersistenceException.class, PersistenceError.SAVE_FAILED);
            }

            return entity;
        }catch (PersistenceException e){
            throw e;
        }catch (MyBatisSystemException e){
            throw new PersistenceException(e.getMostSpecificCause().getMessage());
        }catch (Exception e){
            throw new PersistenceException(PersistenceError.SAVE_FAILED);
        }
    }

    @Override
    @Transactional(
            rollbackFor = {Exception.class}
    )
    default List<E> addBatch(List<E> entities) throws PersistenceException {
        try{
            int result = this.getMapper().insertBatchSomeColumn(entities);

            if(result!=entities.size()){
                throw ExceptionFactory.instance(PersistenceException.class, PersistenceError.SAVE_FAILED);
            }
            return entities;
        }catch (PersistenceException e){
            throw e;
        }catch (MyBatisSystemException e){
            throw new PersistenceException(e.getMostSpecificCause().getMessage());
        }catch (Exception e){
            throw new PersistenceException(PersistenceError.SAVE_FAILED);
        }
    }

    @Override
    default E modify(E entity) throws PersistenceException {
        try{
            int result = this.getMapper().updateById(entity);
            if(result<=0){
                throw ExceptionFactory.instance(PersistenceException.class, PersistenceError.SAVE_FAILED);
            }
            return entity;
        }catch (PersistenceException e){
            throw e;
        }catch (MyBatisSystemException e){
            throw new PersistenceException(e.getMostSpecificCause().getMessage());
        }catch (Exception e){
            throw new PersistenceException(PersistenceError.SAVE_FAILED);
        }
    }

    default List<E> saveBatch(List<E> entities) throws PersistenceException {
        try{
            for(E entity:entities){
                if(ObjectUtil.isNotEmpty(entity.getId())){
                    this.modify(entity);
                }else{
                    this.add(entity);
                }
            }

            return entities;
        }catch (PersistenceException e){
            throw e;
        }catch (MyBatisSystemException e){
            throw new PersistenceException(e.getMostSpecificCause().getMessage());
        }catch (Exception e){
            throw new PersistenceException(PersistenceError.SAVE_FAILED);
        }
    }

    default E upsert(E entity) throws PersistenceException {
        if(ObjectUtil.isNull(entity.getId())){
            return this.add(entity);
        }else{
            return this.modify(entity);
        }
    }

    @Override
    default List<E> upsertBatch(List<E> entities) throws PersistenceException {
        try{
            int result = this.getMapper().upsert(entities);
            if(result!=entities.size()){
                throw ExceptionFactory.instance(PersistenceException.class, PersistenceError.SAVE_FAILED);
            }
            return entities;
        }catch (PersistenceException e){
            throw e;
        }catch (MyBatisSystemException e){
            throw new PersistenceException(e.getMostSpecificCause().getMessage());
        }catch (Exception e){
            throw new PersistenceException(PersistenceError.SAVE_FAILED);
        }
    }

    @Override
    default boolean remove(Serializable id) throws PersistenceException {
        try{
            return this.getMapper().deleteById(id) > 0;
        }catch (MyBatisSystemException e){
            throw new PersistenceException(e.getMostSpecificCause().getMessage());
        }catch (Exception e){
            throw new PersistenceException(PersistenceError.DELETE_FAILED,e.getMessage());
        }
    }

    @Override
    default boolean remove(E entity) throws PersistenceException {
        try{
            return this.getMapper().deleteById(entity) > 0;
        }catch (MyBatisSystemException e){
            throw new PersistenceException(e.getMostSpecificCause().getMessage());
        }catch (Exception e){
            throw new PersistenceException(PersistenceError.DELETE_FAILED,e.getMessage());
        }
    }

    default boolean remove(List<E> entities) throws PersistenceException {
        try{
            List<Object> ids = new ArrayList<>();
            for(E entity : entities){
                ids.add(entity.getId());
            }
            int i = this.getMapper().deleteBatchIds(ids);
            if(i==entities.size()){
                return true;
            }else{
                throw new PersistenceException(PersistenceError.DELETE_FAILED,"部分数据删除失败");
            }
        }catch (MyBatisSystemException e){
            throw new PersistenceException(e.getMostSpecificCause().getMessage());
        }catch (Exception e){
            throw new PersistenceException(PersistenceError.DELETE_FAILED,e.getMessage());
        }
    }

}

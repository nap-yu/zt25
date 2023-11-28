package org.zt25.example.services;

import org.zt25.example.entity.purview.DeptEntity;
import org.zt25.example.repository.DeptRepository;
import org.zt25.persistence.orm.OrmMapper;

import java.io.Serializable;

public interface DeptService<E extends DeptEntity, R extends DeptRepository<E,? extends OrmMapper<E>>> {

    //default E add(E entity){
    //    return this.getRepository().add(entity);
    //}

    E get(Serializable id);

}

package org.zt25.example.repository;

import org.zt25.example.entity.purview.DeptEntity;
import org.zt25.persistence.orm.OrmRepository;
import org.zt25.persistence.orm.OrmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("deptRepository")
public class DeptRepository<E extends DeptEntity,M extends OrmMapper<E>> implements OrmRepository<E> {

    private final M mapper;

    @Autowired
    public DeptRepository(M mapper) {
        this.mapper = mapper;
    }

    @Override
    public Class<E> getEntityClass() {
        return (Class<E>) DeptEntity.class;
    }

    @Override
    public M getMapper() {
        return this.mapper;
    }

}

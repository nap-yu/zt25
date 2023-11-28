package org.zt25.example.services.impl;

import org.zt25.example.entity.purview.DeptEntity;
import org.zt25.example.repository.DeptRepository;
import org.zt25.example.services.DeptService;
import org.zt25.persistence.orm.OrmMapper;
import org.zt25.web.OrmService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
//public class DeptServiceImpl<E extends DeptEntity,R extends DeptRepository<E,? extends PMapper<E>>> implements DeptService<E,R> {
public class DeptServiceImpl<E extends DeptEntity,R extends DeptRepository<E,? extends OrmMapper<E>>> extends OrmService<E,R> implements DeptService<E,R> {
    @Override
    public E get(Serializable id) {
        return this.repository.get(id);
    }

    //private final R mapper;

    //@Autowired
    //public DeptServiceImpl(R mapper) {
    //    this.mapper = mapper;
    //}

    //@Override
    //public R getRepository() {
    //    return this.mapper;
    //}
}

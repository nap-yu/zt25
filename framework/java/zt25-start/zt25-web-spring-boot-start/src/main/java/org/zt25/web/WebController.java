package org.zt25.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.zt25.persistence.orm.OrmEntity;
import org.zt25.persistence.orm.OrmRepository;

import java.io.Serializable;

@Slf4j
public abstract class WebController<E extends OrmEntity<? extends Serializable>,S extends OrmService<E,? extends OrmRepository<E>>> extends ZController {

    private S service;

    @Autowired
    public void setService(S service) {
        this.service = service;
    }

}

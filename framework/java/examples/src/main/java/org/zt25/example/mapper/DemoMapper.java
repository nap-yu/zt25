package org.zt25.example.mapper;

import org.zt25.example.entity.Demo;
import org.zt25.persistence.orm.OrmMapper;
import org.springframework.stereotype.Repository;

@Repository("demoMapper")
public interface DemoMapper extends OrmMapper<Demo> {
}

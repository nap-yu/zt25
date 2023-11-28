package org.zt25.example.mapper;

import org.zt25.example.entity.purview.DeptEntity;
import org.zt25.persistence.orm.OrmMapper;
import org.springframework.stereotype.Repository;

@Repository("deptMapper")
public interface DeptMapper extends OrmMapper<DeptEntity> {

}

package org.zt25.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.zt25.example.entity.purview.OrgEntity;
import org.zt25.persistence.orm.OrmMapper;
import org.springframework.stereotype.Repository;

@Repository("orgMapper")
public interface OrgMapper extends OrmMapper<OrgEntity> {


    Long selectCount(@Param("code") String code);
}

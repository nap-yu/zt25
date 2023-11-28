package org.zt25.example.qo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zt25.example.entity.purview.DeptEntity;
import org.zt25.example.entity.purview.OrgEntity;
import org.zt25.example.vo.DeptVo;
import org.zt25.persistence.annotation.Condition;
import org.zt25.persistence.annotation.OrderBy;
import org.zt25.persistence.annotation.OrderByField;
import org.zt25.persistence.domain.CO;
import org.zt25.persistence.domain.DO;
import org.zt25.persistence.enums.Connector;
import org.zt25.persistence.enums.OrderByMode;
import org.zt25.persistence.enums.SqlClause;
import org.zt25.persistence.orm.annotation.Join;
import org.zt25.persistence.orm.annotation.Relation;
import org.zt25.persistence.orm.annotation.SubCond;
import org.zt25.persistence.orm.enums.JoinMode;

import java.util.LinkedHashMap;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Relation(result = DeptVo.class, join = {
        @Join(entity = OrgEntity.class, mode = JoinMode.LEFT, alias = "org", thisField = DO.Fields.id, joinField = DeptEntity.Fields.orgId)
})
public class DeptCo extends CO {

    @SubCond(group = "子条件2", connector = Connector.OR)
    @Condition(clause = SqlClause.LIKE)
    private String name;

    @SubCond(group = "子条件3", superGroup = "子条件2")
    @Condition(clause = SqlClause.IN)
    private List<String> code;

    @SubCond(group = "子条件1", connector = Connector.OR)
    @Condition(entity = OrgEntity.class, connector = Connector.OR, field = OrgEntity.Fields.name, clause = SqlClause.LIKE)
    private String orgName;

    @OrderBy(fields = {
            @OrderByField(entity = DeptEntity.class, field = DeptEntity.Fields.code),
            @OrderByField(entity = OrgEntity.class, field = OrgEntity.Fields.code, alias = "orgCode")
    })
    private LinkedHashMap<String, OrderByMode> orderByList = new LinkedHashMap<>();
}

package org.zt25.example.vo;

import lombok.Data;
import lombok.ToString;
import org.zt25.example.entity.purview.OrgEntity;
import org.zt25.persistence.orm.annotation.ResultField;

/**
 * @author : zhaotaiyu
 * @description : 部门
 * @date : 2020-09-07 22:56
 */
@Data
@ToString(callSuper=true)
public class DeptVo {

    /**
     * 所属机构id。关联机构表（purview_org）主键
     */
    private String orgId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门代码
     */
    private String code;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系人手机号
     */
    private String mobile;

    /**
     * 说明
     */
    private String memo;

    /**
     * 上级部门主键
     */
    private String parentId;

    @ResultField(clazz = OrgEntity.class, field = OrgEntity.Fields.name)
    private String orgName;
}

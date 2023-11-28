package org.zt25.example.dto.purview;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.zt25.web.domain.ZDto;

/**
 * @author : zhaotaiyu
 * @description : 部门
 * @date : 2020-09-07 22:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper=true)
public class DeptDto extends ZDto<Long> {

    /**
     * 所属机构id。关联机构表（purview_org）主键
     */
    @NotNull(message = "请选择所属机构")
    private String orgId;

    /**
     * 部门名称
     */
    @NotNull
    @Size(min = 2, max = 200,message = "部门名称长度在5~200")
    private String name;

    /**
     * 部门代码
     */
    @NotNull
    @Size(min = 2, max = 50,message = "部门编码长度在5~50")
    private String code;

    /**
     * 联系电话
     */
    @Size(min = 5, max = 50,message = "联系电话长度在5~50")
    private String phone;

    /**
     * 联系人
     */
    @Size(min = 2, max = 50,message = "联系人在2~50")
    private String contactPerson;

    /**
     * 联系人手机号
     */
    @Size(min = 5, max = 50,message = "联系人手机号在5~50")
    private String mobile;

    /**
     * 说明
     */
    @Size(max = 500,message = "说明长度在5~50")
    private String memo;

    /**
     * 上级部门主键
     */
    private String parentId;

    private String orgName;
}

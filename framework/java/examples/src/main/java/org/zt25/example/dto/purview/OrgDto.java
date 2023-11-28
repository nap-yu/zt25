package org.zt25.example.dto.purview;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.zt25.converter.annotation.ZDataHandler;
import org.zt25.converter.enums.DataHandlerType;

/**
 * @author : zhaotaiyu
 * @description : 机构信息
 * @date : 2020-09-07 22:56
 */
@Data
public class OrgDto {

    private Long id;

    /**
     * 机构编号
     */
    @NotNull
    @Size(min = 5, max = 50,message = "机构编号长度在5~50")
    private String code;

    /**
     * 机构名称
     */
    @NotNull
    @Size(min = 5, max = 200,message = "机构名称长度在5~200")
    private String name;

    /**
     * 机构简称
     */
    @Size(min = 2, max = 200,message = "机构简称长度在5~200")
    private String shortName;

    /**
     * 机构英文名称
     */
    @Size(min = 2, max = 200,message = "机构英文名称长度在5~200")
    private String enName;

    /**
     * 社会统一信用代码
     */
    @NotNull
    @Size(min = 5, max = 50,message = "社会统一信用代码长度在5~50")
    @ZDataHandler(type = DataHandlerType.ADDRESS)
    private String orgNo;

    /**
     * 法人
     */
    @NotNull
    @Size(min = 2, max = 50,message = "法人姓名长度在5~50")
    private String legalPerson;

    /**
     * 签发日期
     */
    @NotNull
    @Pattern(regexp = "(((0[48]|[2468][048]|[1357][26])00|\\d\\d(0[48]|[2468][048]|[1357][26]))-02-29|\\d{4}-(0[13578]|1[02])-(0[1-9]|1\\d|2\\d|31)|(0[469]|11)-(0[1-9]|1\\d|2\\d|30)|02-(0[1-9]|1\\d|2[0-8]))",message = "签发日期应为\"yyyy-MM-dd\"格式")
    private String visaDate;

    /**
     * 生效日期
     */
    @NotNull
    @Pattern(regexp = "(((0[48]|[2468][048]|[1357][26])00|\\d\\d(0[48]|[2468][048]|[1357][26]))-02-29|\\d{4}-(0[13578]|1[02])-(0[1-9]|1\\d|2\\d|31)|(0[469]|11)-(0[1-9]|1\\d|2\\d|30)|02-(0[1-9]|1\\d|2[0-8]))",message = "生效日期应为\"yyyy-MM-dd\"格式")
    private String effectiveDate;

    /**
     * 地址
     */
    @Size(min = 5, max = 500,message = "地址长度在5~500")
    private String address;

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
     * 上级机构主键
     */
    private String parentId;
}

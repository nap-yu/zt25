package org.zt25.example.entity.purview;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.apache.ibatis.type.JdbcType;
import org.zt25.converter.annotation.ZDataHandler;
import org.zt25.converter.enums.DataHandlerType;
import org.zt25.persistence.orm.OrmEntity;

/**
 * @author : zhaotaiyu
 * @description : 机构信息
 * @date : 2020-09-07 22:56
 */
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper=true)
@TableName("purview_org")
public class OrgEntity extends OrmEntity<Long> {

    /**
     * 机构编号
     */
    @TableField(value = "code",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String code;

    /**
     * 机构名称
     */
    @TableField(value = "name",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String name;

    /**
     * 机构简称
     */
    @TableField(value = "short_name",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String shortName;

    /**
     * 机构英文名称
     */
    @TableField(value = "en_name",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String enName;

    /**
     * 社会统一信用代码
     */
    @TableField(value = "org_no",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    @ZDataHandler(type = DataHandlerType.ADDRESS)
    private String orgNo;

    /**
     * 法人
     */
    @TableField(value = "legal_person",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String legalPerson;

    /**
     * 签发日期
     */
    @TableField(value = "visa_date",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String visaDate;

    /**
     * 生效日期
     */
    @TableField(value = "effective_date",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String effectiveDate;

    /**
     * 地址
     */
    @TableField(value = "address",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String address;

    /**
     * 联系电话
     */
    @TableField(value = "phone",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String phone;

    /**
     * 联系人
     */
    @TableField(value = "contact_person",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String contactPerson;

    /**
     * 联系人手机号
     */
    @TableField(value = "mobile",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String mobile;

    /**
     * 上级机构主键
     */
    @TableField(value = "parent_id",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String parentId;
}

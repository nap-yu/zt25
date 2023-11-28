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
import org.zt25.example.interceptor.DeptFillHandler;
import org.zt25.persistence.orm.OrmEntity;
import org.zt25.persistence.orm.annotation.ZFillHandler;

/**
 * @author : zhaotaiyu
 * @description : 部门
 * @date : 2020-09-07 22:56
 */
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper=true)
@TableName("purview_dept")
@ZFillHandler(fill = DeptFillHandler.class,skipDefaultFill = true)
public class DeptEntity extends OrmEntity<Long> {

    /**
     * 所属机构id。关联机构表（purview_org）主键
     */
    @TableField(value = "org_id",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String orgId;

    /**
     * 部门名称
     */
    @TableField(value = "name",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String name;

    /**
     * 部门代码
     */
    @TableField(value = "code",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String code;

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
     * 说明
     */
    @TableField(value = "memo",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String memo;

    /**
     * 上级部门主键
     */
    @TableField(value = "parent_id",jdbcType = JdbcType.VARCHAR,fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.ALWAYS)
    private String parentId;
}

package org.zt25.persistence.orm;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;
import org.apache.ibatis.type.JdbcType;
import org.zt25.persistence.domain.DO;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * ORM实体基类
 *
 * <p>
 * 需继承
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class OrmEntity<T extends Serializable> extends DO<T> {

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT,jdbcType = JdbcType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by",fill = FieldFill.INSERT,jdbcType = JdbcType.VARBINARY)
    private String createBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.UPDATE,jdbcType = JdbcType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by",fill = FieldFill.UPDATE,jdbcType = JdbcType.VARCHAR)
    private String updateBy;

    /**
     * 删除标识 0-有效 1-无效
     */
    //@TableLogic
    //@TableField(value = "del_flag",fill = FieldFill.INSERT,jdbcType = JdbcType.INTEGER)
    //private Long delFlag;

    /**
     * 版本号
     */
    //@Version
    //@TableField(value = "version",fill = FieldFill.INSERT,jdbcType = JdbcType.INTEGER)
    //private Integer version;
}

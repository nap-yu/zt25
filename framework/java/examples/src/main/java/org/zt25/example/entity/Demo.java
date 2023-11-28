package org.zt25.example.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;
import org.zt25.example.interceptor.DemoSexDefaultValueFillStrategy;
import org.zt25.persistence.annotation.DefaultValue;
import org.zt25.persistence.annotation.PrimaryKey;
import org.zt25.persistence.enums.ResourceOperationType;
import org.zt25.persistence.orm.OrmEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@PrimaryKey()
public class Demo extends OrmEntity<String> {

    @NotNull
    @JSONField(schema = "{'minimum':0,'maximum':100}")
    @TableField(value = "name",fill = FieldFill.INSERT,jdbcType = JdbcType.VARBINARY)
    private String name;

    @TableField(value = "idNo",fill = FieldFill.INSERT,jdbcType = JdbcType.VARBINARY)
    private String idNo;

    @TableField(value = "age",fill = FieldFill.INSERT,jdbcType = JdbcType.INTEGER)
    @DefaultValue(strategyClass = DemoSexDefaultValueFillStrategy.class)
    private Integer age;

    @TableField(value = "sex",fill = FieldFill.INSERT,jdbcType = JdbcType.CHAR)
    @DefaultValue(commandType = ResourceOperationType.SQL_INSERT,value = "1")
    private Character sex;

    @TableField(value = "status",fill = FieldFill.INSERT,jdbcType = JdbcType.BOOLEAN)
    @DefaultValue("1")
    private Boolean status;
}

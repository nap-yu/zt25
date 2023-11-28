package org.zt25.persistence.orm.annotation;

import org.zt25.persistence.domain.DO;
import org.zt25.persistence.orm.enums.JoinMode;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Join {

    /**
     * 子表实体类型
     */
    Class<? extends DO> entity();

    /**
     * 关联模式 {@link JoinMode}
     */
    JoinMode mode();

    /**
     * 别名
     */
    String alias() default "";

    /**
     * 主表关联字段
     */
    String thisField() default "";

    /**
     * 子表关联字段
     */
    String joinField() default "";
}

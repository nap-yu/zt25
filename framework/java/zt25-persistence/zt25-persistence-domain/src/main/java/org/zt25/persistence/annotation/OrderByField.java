package org.zt25.persistence.annotation;

import org.zt25.persistence.domain.DO;
import org.zt25.persistence.enums.OrderByMode;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface OrderByField {

    Class<? extends DO> entity();

    String field();

    OrderByMode defaultMode() default OrderByMode.ASC;

    /**
     * 别名
     */
    String alias() default "";

    /**
     * 排序,如果为-9,则按参数传入顺序处理.-9的优先级最低,优先处理有顺序的字段
     */
    //int order() default -9;

}

package org.zt25.persistence.annotation;

import org.zt25.persistence.domain.DO;
import org.zt25.persistence.enums.Connector;
import org.zt25.persistence.enums.SqlClause;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Condition {

    SqlClause clause();

    Connector connector() default Connector.AND;

    /**
     * 当前条件所属实体类的类型
     */
    Class<? extends DO> entity() default DO.class;

    /**
     *  实体属性名
     */
    String field() default "";

    /**
     * 忽略""
     */
    boolean ignoreEmpty() default false;
}

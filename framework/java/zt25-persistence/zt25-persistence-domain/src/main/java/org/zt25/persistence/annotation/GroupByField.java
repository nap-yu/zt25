package org.zt25.persistence.annotation;

import org.zt25.persistence.domain.DO;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface GroupByField {

    Class<? extends DO> entity();

    String field();

    /**
     * 别名
     */
    String alias() default "";

}

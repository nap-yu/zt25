package org.zt25.persistence.orm.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Result {

    /**
     * 子表实体类型
     */
    Class<?> clazz() default Class.class;

}

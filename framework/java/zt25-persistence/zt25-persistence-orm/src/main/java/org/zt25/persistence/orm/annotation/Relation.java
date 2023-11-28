package org.zt25.persistence.orm.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Relation {
    Join[] join() default {};
    Class<?> result() default Class.class;
}
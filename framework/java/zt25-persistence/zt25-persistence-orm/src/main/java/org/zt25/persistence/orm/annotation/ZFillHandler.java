package org.zt25.persistence.orm.annotation;

import org.zt25.persistence.orm.OrmFillHandler;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ZFillHandler {

    Class<? extends OrmFillHandler> fill();

    boolean skipDefaultFill() default false;
}

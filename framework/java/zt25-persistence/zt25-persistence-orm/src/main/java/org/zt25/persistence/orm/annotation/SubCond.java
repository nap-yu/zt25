package org.zt25.persistence.orm.annotation;

import org.zt25.persistence.enums.Connector;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SubCond {

    Connector connector() default Connector.AND;
    /**
     * 分组名称
     */
    String group() default "";

    /**
     * 上级分组名称(多级嵌套时使用)
     */
    String superGroup() default "";
}

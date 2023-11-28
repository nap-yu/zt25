package org.zt25.persistence.orm.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ResultField {

    /**
     * 关联的实体类型
     */
    Class<?> clazz();

    /**
     * 关联实体中的属性名称
     */
    String field();

}

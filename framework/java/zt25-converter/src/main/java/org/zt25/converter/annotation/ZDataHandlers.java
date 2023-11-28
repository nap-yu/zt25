package org.zt25.converter.annotation;


import java.lang.annotation.*;

/**
 * 数据处理集合注解
 *
 * <p>
 * 只针对属性类型为map时生效
 * </p>
 *
 * @author zhaotaiyu
 * @version 1.0
 * @since 2023/9/20 9:58
 **/
@Documented
@Target({ElementType.FIELD})
@Inherited
@Retention(RetentionPolicy.RUNTIME )
public @interface ZDataHandlers {

    ZDataHandler[] value() default {};

}

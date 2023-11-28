package org.zt25.persistence.annotation;

import org.zt25.persistence.DefaultValueFillStrategy;
import org.zt25.persistence.enums.ResourceOperationType;

import java.lang.annotation.*;

/**
 * 属性默认值直接
 *
 * <p>
 * 在mybatis拦截器中根据此注解进行默认值处理
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DefaultValue {
    String value() default "";

    /**
     * 操作类型,根据操作类型在相应的数据操作时处理默认值
     * @return {@link ResourceOperationType}
     */
    ResourceOperationType commandType() default ResourceOperationType.SQL_INSERT;

    /**
     * 自定义默认值填充策略.通过此类型从spring环境中获取实例
     */
    Class<? extends DefaultValueFillStrategy> strategyClass() default DefaultValueFillStrategy.class;
}

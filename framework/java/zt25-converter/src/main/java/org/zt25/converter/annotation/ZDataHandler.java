package org.zt25.converter.annotation;


import org.zt25.converter.enums.DataHandlerType;
import org.zt25.converter.plugin.ZDataHandlerPlugin;

import java.lang.annotation.*;

/**
 * 数据处理注解
 *
 * <p>
 * 如果对象或map属性需要额外的处理,在属性上添加这个注解
 * map属性可添加多个
 * </p>
 *
 * @author zhaotaiyu
 * @version 1.0
 * @since 2023/9/20 9:58
 **/
@Repeatable(ZDataHandlers.class)
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME )
public @interface ZDataHandler {

    /**
     * 处理类型 {@link DataHandlerType}
     */
    DataHandlerType type() default DataHandlerType.NO_MASK;

    /**
     * 键名称,只在属性类型为map时生效
     */
    String key() default "";

    /**
     * 证件号脱敏时,前面显示的位数
     */
    int front() default 3;

    /**
     * 证件号脱敏时,后面显示的位数
     */
    int end() default 2;

    /**
     * 地址脱敏时,掩饰后多少位
     */
    int sensitiveSize() default 8;

    /**
     * 自定义处理器的对象类型
     */
    Class<? extends ZDataHandlerPlugin> handler() default ZDataHandlerPlugin.class;
}

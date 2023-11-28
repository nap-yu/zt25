package org.zt25.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求入参解密注解
 *
 * <p>
 * 定义在controller方法入参上
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface Decrypt {
}

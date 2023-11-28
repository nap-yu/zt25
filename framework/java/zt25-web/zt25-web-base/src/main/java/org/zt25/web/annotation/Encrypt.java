package org.zt25.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 出参加密注解
 *
 * <p>
 * 定义在controller方法上,在请求返回时对出参进行加密.
 * 如果出参类型为 org.zt25.core.domain.web.R 时对R.data进行机密.其他情况对整体出参内容进行加密.
 * 在出参的header中会定义加密类型
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Encrypt {
}

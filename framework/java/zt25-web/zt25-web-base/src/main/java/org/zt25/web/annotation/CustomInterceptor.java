package org.zt25.web.annotation;

import java.lang.annotation.*;

/**
 * 自定义拦截器注解
 *
 * <p>
 * 使用此注解定义的拦截器,在启动时自动注册
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Inherited // 子类是否继承注解
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomInterceptor {

    /**
     * 需拦截器处理的请求路径.
     * 默认为:/**
     */
    String[] addPath() default {};

    /**
     * 排除路径
     */
    String[] excludePath() default {};


    int order() default 200;
}


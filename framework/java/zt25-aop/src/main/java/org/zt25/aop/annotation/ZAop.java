package org.zt25.aop.annotation;


import org.zt25.aop.AnnotationActuator;
import org.zt25.aop.enums.Advice;

import java.lang.annotation.*;

/**
 * @Author List
 * @Description 通用注解
 * @Date 10:43 2022/7/8
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZAop {

    /**
     * @Author List
     * @Description 切点
     * @Date 10:44 2022/7/8
     * @Param []
     * @return com.ax.pg.enums.AdviceEnum
     **/
    Advice point() default Advice.BEFORE;

    /**
     * @Author List
     * @Description 切面
     * @Date 10:45 2022/7/8
     * @Param []
     * @return java.lang.Class<? extends com.ax.pg.annotation.AnnotationActuator>[]
     **/
    Class<? extends AnnotationActuator> process();

}

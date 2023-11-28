package org.zt25.aop;

import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @ClassName AnnotationAttributeSourcePointcut
 * @Description  注解切面类
 * @Author List
 * @Date 2022/7/8 9:58
 * @Version 1.0
 **/
public class AnnotationAttributeSourcePointcut extends StaticMethodMatcherPointcut implements Serializable {


    /**
     * 需要被拦截代理的注解列表
     */
    private Set<Class<? extends Annotation>> annotationsOperation = new LinkedHashSet<>(8);


    /**
     *
     * @param annotation 注解
     */
    public void addAnnotations(Class<? extends Annotation> annotation) {
        annotationsOperation.add(annotation);
    }

    /**
     * 匹配注解
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        if (isContainAnnotations(annotationsOperation, method)) {
            return true;
        }
        return false;
    }

    /**
     * 注解判断
     */
    public boolean isContainAnnotations(Set<Class<? extends Annotation>> annotations, AnnotatedElement element) {
        boolean isContain = false;
        for (Class<? extends Annotation> annotation : annotations) {
            if (AnnotatedElementUtils.hasAnnotation(element, annotation)) {
                isContain = true;
                break;
            }
        }
        return isContain;
    }


}

package org.zt25.aop;

import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import java.util.List;

/**
 * @Author List
 * @Description AOP配置类
 * @Date 10:43 2022/7/8
 **/
@Configuration
public class AopConfig {

    /**
     * @Author List
     * @Description   拦截器配置
     * @Date 10:38 2022/7/8
     * @Param [annotationAttributeSourcePointcut]
     * @return org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor
     **/
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @ConditionalOnBean(AnnotationProcessService.class)
    public DefaultBeanFactoryPointcutAdvisor transactionAdvisor(
            AnnotationAttributeSourcePointcut annotationAttributeSourcePointcut) {
        DefaultBeanFactoryPointcutAdvisor advisor = new DefaultBeanFactoryPointcutAdvisor();
        // 具体的拦截器
        advisor.setAdvice(annotationInterceptor());
        // 需要被拦截方法的规则判断器，一旦符合，才会被代理给拦截器处理
        advisor.setPointcut(annotationAttributeSourcePointcut);
        return advisor;
    }

    /**
     * @Author List
     * @Description 注解处理类
     * @Date 10:39 2022/7/8
     * @Param [annotationProcessServiceList]
     * @return com.ax.pg.annotation.AnnotationAttributeSourcePointcut
     **/
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @ConditionalOnBean(AnnotationProcessService.class)
    public AnnotationAttributeSourcePointcut annotationAttributeSourcePointcut(
            List<AnnotationProcessService> annotationProcessServiceList) {
        // 该类是用作比对方法的注解是否符合代理的条件
        AnnotationAttributeSourcePointcut cacheAttributeSourcePointcut = new AnnotationAttributeSourcePointcut();
        annotationProcessServiceList.forEach((annotation) -> {
            cacheAttributeSourcePointcut.addAnnotations(annotation.annotation());
        });
        return cacheAttributeSourcePointcut;
    }

    /**
     * @Author List
     * @Description 注解拦截器
     * @Date 10:39 2022/7/8
     * @Param []
     * @return com.ax.pg.annotation.AnnotationInterceptor
     **/
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @ConditionalOnBean(AnnotationProcessService.class)
    public AnnotationInterceptor annotationInterceptor() {
        AnnotationInterceptor interceptor = new AnnotationInterceptor();
        return interceptor;
    }

}

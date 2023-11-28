package org.zt25.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.zt25.aop.annotation.ZAop;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName AnnotationInterceptor
 * @Description  注解拦截器
 * @Author List
 * @Date 2022/7/8 10:50
 * @Version 1.0
 **/
public class AnnotationInterceptor implements MethodInterceptor, Serializable {

    /**
     * 注入处理类
     **/
    @Autowired(required = false)
    private List<AnnotationProcessService> cacheProcessServices;

    /**
     * @Author List
     * @Description 执行枚举方法
     * @Date 10:42 2022/7/8
     * @Param [invocation] 拦截方法
     * @return java.lang.Object
     **/
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object proceed = null;
        if (cacheProcessServices != null && invocation instanceof ReflectiveMethodInvocation) {
            ReflectiveMethodInvocation methodInvocation = (ReflectiveMethodInvocation)invocation;
            Method method = invocation.getMethod();
            Annotation[] annotations = method.getAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                Annotation annotation = annotations[i];
                if(annotation.annotationType().getName().equals(ZAop.class.getName())){
                    for (int j = 0; j < cacheProcessServices.size(); j++) {
                        AnnotationProcessService cache = cacheProcessServices.get(i);
                        if (annotation.annotationType() == cache.annotation()) {
                            proceed = cache.invokeWithinTransaction(methodInvocation);
                        }
                    }
                }

            }
            return proceed;
        }
        return invocation.proceed();

    }
}

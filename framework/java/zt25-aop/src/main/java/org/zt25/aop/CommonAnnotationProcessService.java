package org.zt25.aop;

import cn.hutool.core.util.ObjectUtil;
import org.zt25.aop.annotation.ZAop;
import org.zt25.aop.enums.Advice;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author List
 * @Description 通用注解处理类
 * @Date 10:43 2022/7/8
 **/
@Component
public class CommonAnnotationProcessService implements AnnotationProcessService{
    /**
     * 表示只处理@CommonAnnotationn注解内容
     */
    @Override
    public Class<? extends Annotation> annotation() {
        return ZAop.class;
    }

    /**
     * @Author List
     * @Description 具体处理实现
     * @Date 10:45 2022/7/8
     * @Param [invocation] 目标方法
     * @return java.lang.Object
     **/
    @Override
    public Object invokeWithinTransaction(ReflectiveMethodInvocation invocation) throws Throwable {

        Method method = invocation.getMethod();
        ZAop aop = AnnotationUtils.getAnnotation(method, ZAop.class);
        //String clazzName = method.getDeclaringClass().getSimpleName();
        //String name = method.getName();
        //String methodName = clazzName + "." + name;
        //Object[] arguments = invocation.getArguments();
        Object obj = null;

        Class<? extends AnnotationActuator> process = aop.process();
        if(ObjectUtil.isNotEmpty(process)){
            Advice adviceEnum = aop.point();
            if (Advice.BEFORE.equals(adviceEnum)){
                Method before = AnnotationAbstractActuator.class.getDeclaredMethod(Advice.BEFORE.getCode(), ReflectiveMethodInvocation.class);
                before.invoke(process.newInstance(),invocation);
                obj = invocation.proceed();
            }else if (Advice.AFTER.equals(adviceEnum)){
                obj = invocation.proceed();
                Method after = AnnotationAbstractActuator.class.getDeclaredMethod(Advice.AFTER.getCode(), ReflectiveMethodInvocation.class,Object.class);
                after.invoke(process.newInstance(),invocation,obj);
            }else {
                Method before = AnnotationAbstractActuator.class.getDeclaredMethod(Advice.BEFORE.getCode(), ReflectiveMethodInvocation.class);
                before.invoke(process.newInstance(),invocation);
                obj = invocation.proceed();
                Method after = AnnotationAbstractActuator.class.getDeclaredMethod(Advice.AFTER.getCode(), ReflectiveMethodInvocation.class,Object.class);
                after.invoke(process.newInstance(),invocation,obj);
            }
        }else{
            obj = invocation.proceed();
        }

        return obj;

    }

}

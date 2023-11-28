package org.zt25.aop;

import org.springframework.aop.framework.ReflectiveMethodInvocation;

/**
 * 注解执行器
 */
public interface AnnotationActuator {

    /**
     * 在目标方法前执行该方法
     * @param reflectiveMethodInvocation 目标方法 {@link ReflectiveMethodInvocation}
     */
    void before(ReflectiveMethodInvocation reflectiveMethodInvocation);

    /**
     * 在目标方法后执行改方法
     * @param reflectiveMethodInvocation 目标方法 {@link ReflectiveMethodInvocation}
     * @param obj 目标方法出参
     */
    void after(ReflectiveMethodInvocation reflectiveMethodInvocation,Object obj);

}

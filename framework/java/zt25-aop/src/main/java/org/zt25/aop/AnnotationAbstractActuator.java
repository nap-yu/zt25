package org.zt25.aop;

import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * 注解执行器抽象封装
 */
public abstract class AnnotationAbstractActuator implements AnnotationActuator{

    /**
     * 目标对象
     */
    protected Object proxy;

    /**
     * 目标方法参数
     */
    protected Object[] arguments;

    /**
     * 属性组Map
     */
    @Nullable
    private Map<String, Object> userAttributes;

    @Override
    public void before(ReflectiveMethodInvocation reflectiveMethodInvocation) {
        this.arguments = reflectiveMethodInvocation.getArguments();
        this.proxy = reflectiveMethodInvocation.getProxy();
        this.userAttributes = reflectiveMethodInvocation.getUserAttributes();
        doBefore(arguments);
    }

    @Override
    public void after(ReflectiveMethodInvocation reflectiveMethodInvocation,Object obj) {
        this.arguments = reflectiveMethodInvocation.getArguments();
        this.proxy = reflectiveMethodInvocation.getProxy();
        this.userAttributes = reflectiveMethodInvocation.getUserAttributes();
        doAfter(arguments,obj);
    }

    /**
     * 调用方法
     * @param arguments 目标方法
     */
    public abstract void doBefore(Object[] arguments);

    /**
     *  结果增强
     * @param arguments 目标方法参数
     * @param obj 目标方法结果集
     */
    public abstract void doAfter(Object[] arguments,Object obj);
}

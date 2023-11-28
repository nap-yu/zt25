package org.zt25.aop;

import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.core.Ordered;

import java.lang.annotation.Annotation;


/**
 * @ClassName AnnotationProcessService
 * @Description  注解处理接口
 * @Author List
 * @Date 2022/7/8 10:50
 * @Version 1.0
 **/
public interface AnnotationProcessService extends Ordered {

    @Override
    default int getOrder() {
        return LOWEST_PRECEDENCE;
    }

    public Class<? extends Annotation> annotation();

    /**
     * 上面匹配到的注解会被触发，尽量不要对结果做改变。
     *
     * @param invocation {@link ReflectiveMethodInvocation}
     */
    public Object invokeWithinTransaction(ReflectiveMethodInvocation invocation) throws Throwable;

}

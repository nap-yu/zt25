package org.zt25.example.aop;

import org.zt25.aop.AnnotationAbstractActuator;
import org.springframework.stereotype.Component;

@Component
public class CommonActuator extends AnnotationAbstractActuator {
    @Override
    public void doBefore(Object[] arguments) {
        System.out.println("这是自定义aop方法:doInvoke");
    }

    @Override
    public void doAfter(Object[] arguments, Object obj) {
        System.out.println("这是自定义aop方法:doResult");
    }
}

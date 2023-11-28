package org.zt25.example.interceptor;

import org.zt25.persistence.DefaultValueFillStrategy;
import org.springframework.stereotype.Component;

@Component
public class DemoSexDefaultValueFillStrategy implements DefaultValueFillStrategy<Integer> {
    @Override
    public Integer value(Object o) {
        return 33;
    }
}

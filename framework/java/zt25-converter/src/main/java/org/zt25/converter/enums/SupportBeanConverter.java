package org.zt25.converter.enums;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.zt25.converter.ConverterException;

import java.util.List;
import java.util.Map;

/**
 * 对象转换器
 *
 * <p>
 * 如果一个类实现了此接口，则其可以被转换为指定的类型。
 * </p>
 *
 * @author zhaotaiyu
 * @version 1.0
 * @since 2023/3/3 9:58
 **/
@Slf4j
public enum SupportBeanConverter {

    /**
     * 同结构对象(主要是各种o之间互转)
     */
    BEAN_TO_BEAN {
        @Override
        protected Object doConversion(Object source, Class<?> targetClass) {
            return JSON.parseObject(JSON.toJSONString(source), targetClass);
        }
    },
    /**
     * 同结构对象转换(自定义数据处理)
     */
    BEAN_TO_BEAN_HANDLER {
        @Override
        protected Object doConversion(Object source, Class<?> targetClass) {
            return JSON.parseObject(JSON.toJSONString(source,DataHandlerFilter.DEFAULT.filter()), targetClass);
        }
    },
    /**
     * 对象转map
     */
    BEAN_TO_MAP {
        @Override
        protected Map doConversion(Object source, Class<?> targetClass) {
            return JSON.parseObject(JSON.toJSONString(source));
        }
    },
    /**
     * map转对象
     */
    MAP_TO_BEAN {
        @Override
        protected Map doConversion(Object source, Class<?> targetClass) {
            return JSON.parseObject(JSON.toJSONString(source));
        }
    },
    /**
     * 同结构对象集合转换(主要是各种o之间互转)
     */
    TO_LIST {
        @Override
        protected List doConversion(Object source, Class<?> targetClass) {
            return JSON.parseArray(JSON.toJSONString(source),targetClass);
        }
    },
    /**
     * 同结构对象集合转换(自定义数据处理)
     */
    TO_LIST_HANDLER {
        @Override
        protected List doConversion(Object source, Class<?> targetClass) {
            return JSON.parseArray(JSON.toJSONString(source,DataHandlerFilter.DEFAULT.filter()),targetClass);
        }
    }
    ;

    public Object convert(Object source, Class<?> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            return doConversion(source, targetClass);
        } catch (ConverterException e) {
            throw e;
        } catch (Exception e) {
            throw new ConverterException(source.getClass().getSimpleName(), toString());
        }
    }

    protected abstract Object doConversion(Object source, Class<?> targetClass);
}

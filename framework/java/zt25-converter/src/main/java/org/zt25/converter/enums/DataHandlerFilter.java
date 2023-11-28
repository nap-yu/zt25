package org.zt25.converter.enums;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson2.filter.PropertyFilter;
import lombok.extern.slf4j.Slf4j;
import org.zt25.converter.annotation.ZDataHandler;
import org.zt25.converter.annotation.ZDataHandlers;
import org.zt25.converter.plugin.ZDataHandlerPlugin;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 自定义Fastjson2的过滤器
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @version 1.0
 * @since 2023/9/20 9:58
 **/
@Slf4j
public enum DataHandlerFilter {

    /**
     * 默认过滤器
     * 根据PDataHandler中的定义处理数据序列化
     */
    DEFAULT {
        @Override
        protected PropertyFilter filter() {
            return (source1, name, value) -> {
                try {
                    Field field = source1.getClass().getDeclaredField(name);

                    if(String.class.isAssignableFrom(field.getType())||Byte.class.isAssignableFrom(field.getType())){
                        Object v = value;
                        ZDataHandler handler = field.getAnnotation(ZDataHandler.class);
                        if(ObjectUtil.isNotNull(handler)){
                            if(handler.type()== DataHandlerType.CUSTOM){
                                ZDataHandlerPlugin h = ReflectUtil.newInstance(handler.handler());
                                v = h.invoke(source1,name,value,field.getType(),handler);
                            }else{
                                v = handler.type().invoke(source1,name,value,field.getType(),handler);
                            }
                            BeanUtil.setFieldValue(source1,name,SupportDataConverter.valueOf(field.getType().getSimpleName().toUpperCase()).convert(v));
                        }
                    }else if(Map.class.isAssignableFrom(field.getType())) {
                        ZDataHandlers handlers = field.getAnnotation(ZDataHandlers.class);
                        Map valueMap = (Map) value;
                        if (ArrayUtil.isNotEmpty(handlers)) {
                            for (int i = 0; i < handlers.value().length; i++) {
                                String k = handlers.value()[i].key();
                                DataHandlerType type = handlers.value()[i].type();
                                Object valueObject = valueMap.get(k);
                                if(type==DataHandlerType.CUSTOM){
                                    if(!CharSequenceUtil.equals(ZDataHandlerPlugin.class.getSimpleName(),handlers.value()[i].handler().getSimpleName())){
                                        ZDataHandlerPlugin h = ReflectUtil.newInstance(handlers.value()[i].handler());
                                        Object v = h.invoke(source1, k, valueObject, valueObject.getClass(), handlers.value()[i]);
                                        valueMap.put(k, SupportDataConverter.valueOf(valueObject.getClass().getSimpleName().toUpperCase()).convert(v));
                                    }
                                }else{
                                    Object v = type.invoke(source1, k, valueObject, valueObject.getClass(), handlers.value()[i]);
                                    valueMap.put(k, SupportDataConverter.valueOf(valueObject.getClass().getSimpleName().toUpperCase()).convert(v));
                                }
                            }
                        }else {
                            ZDataHandler handler = field.getAnnotation(ZDataHandler.class);
                            String k = handler.key();
                            DataHandlerType type = handler.type();
                            Object valueObject = valueMap.get(k);
                            if(type==DataHandlerType.CUSTOM){
                                if(!CharSequenceUtil.equals(ZDataHandlerPlugin.class.getSimpleName(),handler.handler().getSimpleName())){
                                    ZDataHandlerPlugin h = ReflectUtil.newInstance(handler.handler());
                                    Object v = h.invoke(source1, k, valueObject, valueObject.getClass(), handler);
                                    valueMap.put(k, SupportDataConverter.valueOf(valueObject.getClass().getSimpleName().toUpperCase()).convert(v));
                                }
                            }else{
                                Object v = type.invoke(source1, k, valueObject, valueObject.getClass(), handler);
                                valueMap.put(k, SupportDataConverter.valueOf(valueObject.getClass().getSimpleName().toUpperCase()).convert(v));
                            }
                        }
                    }
                } catch (NoSuchFieldException e) {
                    log.warn(e.getMessage());
                }

                return true;
            };
        }
    };

    protected abstract PropertyFilter filter();

}

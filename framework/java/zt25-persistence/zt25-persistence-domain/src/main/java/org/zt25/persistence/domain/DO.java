package org.zt25.persistence.domain;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.zt25.core.ZObject;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 持久化对象基类
 *
 * <p>
 * orm的持久化对象基类
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Data
@FieldNameConstants
public abstract class DO<T extends Serializable> implements ZObject {

    private T id;

    /**
     * 获取主键真实类型
     * @return 主键类型
     */
    public String pkClass(){
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType p = (ParameterizedType)type;
        return p.getActualTypeArguments()[0].getTypeName();
    }
}

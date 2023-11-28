package org.zt25.persistence.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.zt25.core.ZEnum;

/**
 * 持久化异常枚举
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Getter
@AllArgsConstructor
public enum PersistenceError implements ZEnum<Integer,String> {

    INTERFACE_NOT_IMPLEMENTED(8000,"%s接口暂未实现"),
    SAVE_FAILED(8001,"保存失败,失败原因%s"),
    DELETE_FAILED(8001,"删除失败,失败原因%s"),
    UNKNOWN_PK_TYPE(8002,"未知的主键类型%s"),
    PROPERTY_NOT_VISIBLE(8003,"%s无法访问"),
    NULL_VALUE_EXCEPTION(8004,"%s为Null"),
    GET_ENTITY_TYPE_EXCEPTION(8005,"获取实体类型失败"),
    ;
    private final Integer code;
    private final String desc;
}

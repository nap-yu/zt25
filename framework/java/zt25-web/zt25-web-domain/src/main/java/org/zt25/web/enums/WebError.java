package org.zt25.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.zt25.core.ZEnum;

/**
 * web异常枚举
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
public enum WebError implements ZEnum<Integer,String> {

    SYSTEM_EXCEPTION(9000,"系统错误") ,
    NULL_VALUE_EXCEPTION(9001,"%s为Null") ,
    CREATE_CUSTOM_INTERCEPTOR_INSTANCE_EXCEPTION(9100,"未知异常"),
    UNKNOWN_EXCEPTION(9999,"未知异常"),

    ;
    private final Integer code;
    private final String desc;
}

package org.zt25.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.zt25.core.ZEnum;

/**
 * 系统异常枚举
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
public enum SysError implements ZEnum<Integer,String> {

    SYSTEM_EXCEPTION(9000,"系统错误") ,
    NULL_VALUE_EXCEPTION(9001,"%s为Null") ,
    CONVERTER_EXCEPTION(9002,"类型转换错误，试图将%s转为%s类型的数据") ,
    UNKNOWN_EXCEPTION(9999,"未知异常")
    ;
    private final Integer code;
    private final String desc;
}

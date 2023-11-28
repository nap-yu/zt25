package org.zt25.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.zt25.core.ZEnum;

/**
 * 请求状态枚举类
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
public enum WebStatus implements ZEnum<Integer,String> {
    SUCCESS(200,"SUCCESS"),
    FAIL(500,"FAIL"),
    OTHER_EXCEPTION_CODE(9999,"未知异常"),
    ;

    private final Integer code;
    private final String desc;
}

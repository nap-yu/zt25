package org.zt25.core.enums.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.zt25.core.ZEnum;

/**
 * 有效状态举类
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
public enum Effective implements ZEnum<Boolean,String> {

    SUCCESS(true,"有效"),
    FAIL(false,"无效"),
    UNKNOWN(null,"未知"),
    ;

    private final Boolean code;
    private final String desc;
}

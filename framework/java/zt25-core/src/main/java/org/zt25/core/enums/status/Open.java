package org.zt25.core.enums.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.zt25.core.ZEnum;

/**
 * 开启状态举类
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
public enum Open implements ZEnum<Boolean,String> {

    OPEN(true,"成功"),
    CLOSE(false,"失败"),
    UNKNOWN(null,"未知"),
    ;

    private final Boolean code;
    private final String desc;
}
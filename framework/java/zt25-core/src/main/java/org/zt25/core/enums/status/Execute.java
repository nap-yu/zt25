package org.zt25.core.enums.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.zt25.core.ZEnum;

/**
 * 执行状态举类
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
public enum Execute implements ZEnum<Boolean,String> {

    SUCCESS(true,"成功"),
    FAIL(false,"失败"),
    ;

    private final Boolean code;
    private final String desc;

}
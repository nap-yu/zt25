package org.zt25.aop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName AdviceEnum
 * @Description  通知枚举类
 * @Author List
 * @Date 2022/7/8 9:58
 * @Version 1.0
 **/
@Getter
@AllArgsConstructor
public enum Advice {

    /**
     * 切点
     */
    BEFORE("before"),AFTER("after"),AROUND("around");

    public final String code;


}

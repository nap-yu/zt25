package org.zt25.core;

import lombok.Getter;
import org.zt25.core.enums.SysError;

/**
 * zt25框架异常基类
 *
 * <p>
 * 项目中的exception需要继承此类
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
public abstract class ZException extends RuntimeException {

    // 编码
    @Getter
    private String code;

    // 消息
    private String message;

    /**
     * 默认构造器
     */
    protected ZException() {
        super();
    }

    /**
     * 构造器
     * @param message 提示
     */
    protected ZException(String message) {
        super(message);
    }

    /**
     * 构造器
     * @param code 编码
     * @param message 提示
     */
    protected ZException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造器
     * @param message 提示信息
     * @param cause 异常父类
     */
    protected ZException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造器
     * @param cause 异常父类
     */
    protected ZException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造器
     * @param enumType 异常枚举(需继承org.mia.base.enumsBaseEnum)
     */
    protected ZException(ZEnum enumType){
        super(enumType.getDesc()!=null?String.valueOf(enumType.getDesc()): SysError.UNKNOWN_EXCEPTION.getDesc());
        this.code=enumType.getCode()!=null?String.valueOf(enumType.getCode()): SysError.UNKNOWN_EXCEPTION.getCode().toString();
    }

    /**
     * 构造器
     * @param enumType 异常枚举(需继承org.mia.base.enumsBaseEnum)
     * @param message 提示信息
     */
    protected ZException(ZEnum enumType, String message){
        super(message);
        this.code=enumType.getCode()!=null?String.valueOf(enumType.getCode()): SysError.UNKNOWN_EXCEPTION.getCode().toString();
    }

}

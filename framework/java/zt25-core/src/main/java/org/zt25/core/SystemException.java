package org.zt25.core;


import org.zt25.core.enums.SysError;

/**
 * 系统异常
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
public final class SystemException extends ZException {

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String code, String message) {
        super(code, message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(SysError enumType) {
        super(enumType);
    }

    public SystemException(SysError enumType, String message) {
        super(enumType, message);
    }

}

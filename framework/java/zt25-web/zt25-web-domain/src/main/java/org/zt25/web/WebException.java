package org.zt25.web;


import org.zt25.core.ZException;
import org.zt25.web.enums.WebError;

/**
 * web异常
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
public class WebException extends ZException {

    public WebException(String message) {
        super(message);
    }
    public WebException(String code, String message) {
        super(code, message);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebException(Throwable cause) {
        super(cause);
    }

    public WebException(WebError enumType) {
        super(enumType);
    }

    public WebException(WebError enumType, String message) {
        super(enumType, message);
    }

}

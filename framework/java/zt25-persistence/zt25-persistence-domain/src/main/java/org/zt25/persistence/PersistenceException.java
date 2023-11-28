package org.zt25.persistence;


import org.zt25.core.ZException;
import org.zt25.persistence.enums.PersistenceError;

/**
 * 持久化异常
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
public class PersistenceException extends ZException {

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String code, String message) {
        super(code, message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }

    public PersistenceException(PersistenceError enumType) {
        super(enumType);
    }

    public PersistenceException(PersistenceError enumType, String message) {
        super(enumType, message);
    }

}

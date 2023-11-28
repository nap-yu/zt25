package org.zt25.web.domain;

import com.alibaba.fastjson2.JSON;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.zt25.core.ZException;
import org.zt25.core.ZObject;
import org.zt25.web.enums.WebStatus;

import java.io.Serial;

/**
 * 响应对象
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@EqualsAndHashCode
@Accessors(chain = true)
@Data
public final class R<T> implements ZObject {

    @Serial
    private static final long serialVersionUID = 920707842497209155L;

    /**
     * 服务器返回业务编码 封装在 WebStatusEnum 枚举对象中
     */
    private boolean status;

    /**
     * 服务器返回业务编码 封装在 WebStatusEnum 枚举对象中
     */
    private Integer code;

    /**
     * 服务器返回提示信息(包括成功信息和错误信息)
     */
    private String message;

    /**
     * 服务器返回错误堆栈提示信息
     */
    private String error;

    /**
     * 服务器返回数据对象
     */
    private T data;

    /**
     * 返回值扩展对象
     */
    private String expObj;

    public static <T> R<T> success(){
        return new R<T>().setStatus(true).setCode(WebStatus.SUCCESS.getCode()).setMessage(WebStatus.SUCCESS.getDesc());
    }

    public static <T> R<T> success(T data){
        return new R<T>().setStatus(true).setCode(WebStatus.SUCCESS.getCode()).setMessage(WebStatus.SUCCESS.getDesc()).setData(data);
    }

    public static <T> R<T> success(String message, T data){
        return new R<T>().setStatus(true).setCode(WebStatus.SUCCESS.getCode()).setMessage(message).setData(data);
    }

    public static <T> R<T> fail(){
        return new R<T>().setStatus(false).setCode(WebStatus.FAIL.getCode()).setMessage(WebStatus.FAIL.getDesc());
    }

    public static <T> R<T> fail(String message){
        return new R<T>().setStatus(false).setCode(WebStatus.FAIL.getCode()).setMessage(message);
    }

    public static <T> R<T> fail(WebStatus webStatusEnum){
        return new R<T>().setStatus(false).setCode(webStatusEnum.getCode()).setMessage(webStatusEnum.getDesc());
    }

    public static <T> R<T> fail(WebStatus webStatusEnum, String message){
        return new R<T>().setStatus(false).setCode(webStatusEnum.getCode()).setMessage(message);
    }

    public static <T> R<T> fail(ZException exception){
        return new R<T>().setStatus(false).setCode(WebStatus.FAIL.getCode()).setMessage(exception.getMessage()).setError(JSON.toJSONString(exception.getStackTrace()));
    }

    public static <T> R<T> fail(WebStatus webStatusEnum, String message, ZException exception){
        return new R<T>().setStatus(false).setCode(webStatusEnum.getCode()).setMessage(message).setError(JSON.toJSONString(exception.getStackTrace()));
    }

}

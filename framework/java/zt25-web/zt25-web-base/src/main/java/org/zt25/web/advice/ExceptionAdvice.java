package org.zt25.web.advice;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.zt25.core.ZException;
import org.zt25.web.domain.R;
import org.zt25.web.enums.WebStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 统一异常处理
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 处理业务异常
     * @param e {@link ZException}}
     */
    @ExceptionHandler(value = ZException.class)
    public R businessExceptionHandler(ZException e){
        log.error("业务异常：{}", e.getMessage());
        return R.fail(WebStatus.FAIL,e.getCode()+":"+e.getMessage());
    }

    /**
     * 处理方法参数异常异常
     * @param argumentNotValidException {@link MethodArgumentNotValidException}}
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException argumentNotValidException){
        BindingResult result = argumentNotValidException.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            if (errors != null) {
                errors.forEach(p -> {
                    FieldError fieldError = (FieldError) p;
                    stringBuilder.append(fieldError.getObjectName()+"."+fieldError.getField()+":"+fieldError.getDefaultMessage());
                });
            }
        }

        return R.fail(WebStatus.FAIL,stringBuilder.toString());
    }

    /**
     * 空指针异常
     * @param nullPointerException  {@link NullPointerException}}
     */
    @ExceptionHandler(value = NullPointerException.class)
    public R nullPointerExceptionHandler(NullPointerException nullPointerException){
        log.error("空指针异常：{}", nullPointerException.getMessage());
        return R.fail(WebStatus.FAIL,WebStatus.FAIL.getDesc()).setError(JSON.toJSONString(nullPointerException.getStackTrace()));
    }

    /**
     * IO异常
     * @param ioException  {@link IOException}}
     */
    @ExceptionHandler(value = IOException.class)
    public R iOExceptionHandler(IOException ioException){
        log.error("IO异常：{}", ioException.getMessage());
        return R.fail(WebStatus.FAIL,ioException.getMessage()).setError(JSON.toJSONString(ioException.getStackTrace()));
    }

    /**
     * SQL异常
     * @param sqlException  {@link SQLException}}
     */
    @ExceptionHandler(value = SQLException.class)
    public R sqlExceptionExceptionHandler(SQLException sqlException){
        log.error("SQL针异常：{}", sqlException.getMessage());
        return R.fail(WebStatus.FAIL,sqlException.getMessage()).setError(JSON.toJSONString(sqlException.getStackTrace()));
    }

    /**
     * 参数异常
     * @param argumentException  {@link IllegalArgumentException}}
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public R illegalArgumentExceptionHandler(IllegalArgumentException argumentException){
        log.error("参数异常：{}", argumentException.getMessage());
        return R.fail(WebStatus.FAIL,argumentException.getMessage()).setError(JSON.toJSONString(argumentException.getStackTrace()));

    }

    /**
     * 处理其他异常
     * @param e {@link Exception}
     */
    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception e){
        Class<? extends Exception> aClass = e.getClass();
        Class<?> superclass = aClass.getSuperclass();
        if (null == superclass){
            return R.fail(WebStatus.FAIL,WebStatus.FAIL.getDesc()).setError(JSON.toJSONString(e.getStackTrace()));
        }
        try {
            if (superclass.newInstance() instanceof ZException){
                ZException be = (ZException) e;
                return R.fail(be.getCode()+":"+e.getMessage()).setError(JSON.toJSONString(e.getStackTrace()));
            }
        } catch (Exception e1) {
            log.error("commonExceptionHandler error:",e1);
        }
        return R.fail(WebStatus.FAIL,WebStatus.FAIL.getDesc()).setError(JSON.toJSONString(e.getStackTrace()));
    }
}

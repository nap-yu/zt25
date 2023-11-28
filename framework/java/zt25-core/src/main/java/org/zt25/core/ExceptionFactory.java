package org.zt25.core;

import org.zt25.core.enums.SysError;

import java.lang.reflect.Constructor;

/**
 * 异常工厂类
 *
 * <p>
 * 项目中的enum需要实现此接口
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
public final class ExceptionFactory {

    /**
     * 私有构造器
     */
    private ExceptionFactory() {throw new AssertionError("该类不期望被实例化,也不期望被继承");}

    /**
     * 创建异常实例
     * @param exception 异常类型
     * @return 异常实例
     */
    public static ZException instance(Class<? extends ZException> exception) {
        if (exception == null) {
            return new SystemException(SysError.NULL_VALUE_EXCEPTION,String.format(SysError.NULL_VALUE_EXCEPTION.getDesc(),"异常类型"));
        }else{
            try {
                Class<?> c = Class.forName(exception.getName());
                Constructor<?> cons = c.getConstructor();
                return (ZException) cons.newInstance();
            }catch (Exception e){
                return new SystemException(SysError.SYSTEM_EXCEPTION.getCode().toString(),e.getMessage());
            }
        }
    }

    /**
     * 创建异常实例
     * @param exception 异常类型
     * @param enumType 异常枚举
     * @return 异常
     */
    public static ZException instance(Class<? extends ZException> exception, ZEnum enumType) {
        if (exception == null||enumType==null) {
            return new SystemException(SysError.NULL_VALUE_EXCEPTION,String.format(SysError.NULL_VALUE_EXCEPTION.getDesc(),"异常类型"));
        }else{
            try {
                Class<?> c = Class.forName(exception.getName());
                Constructor<?> cons = c.getConstructor(ZEnum.class);
                return (ZException) cons.newInstance(enumType);
            }catch (Exception e){
                return new SystemException(SysError.SYSTEM_EXCEPTION.getCode().toString(),e.getMessage());
            }
        }
    }

    /**
     * 创建异常实例
     * @param exception 异常类型
     * @param enumType 异常枚举
     * @param message 异常信息
     * @return 异常
     */
    public static ZException instance(Class<? extends ZException> exception, ZEnum enumType, String message) {
        if (exception == null||enumType==null) {
            return new SystemException(SysError.NULL_VALUE_EXCEPTION,String.format(SysError.NULL_VALUE_EXCEPTION.getDesc(),"异常类型"));
        }else{
            try {
                Class<?> c = Class.forName(exception.getName());
                Constructor<?> cons = c.getConstructor(String.class,String.class);
                return (ZException) cons.newInstance(enumType.getCode().toString(),message);
            }catch (Exception e){
                return new SystemException(SysError.SYSTEM_EXCEPTION.getCode().toString(),e.getMessage());
            }
        }
    }
}

package org.zt25.converter;

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
public final class ConverterException extends IllegalArgumentException {

    public ConverterException(String value, String type) {
        super(String.format("类型转换错误，试图将%s转为%s类型的数据", value, type));
    }

    public ConverterException(String value, String type, Throwable cause) {
        super(String.format("类型转换错误，试图将%s转为%s类型的数据", value, type), cause);
    }

}

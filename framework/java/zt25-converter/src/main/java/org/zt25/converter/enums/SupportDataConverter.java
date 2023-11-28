package org.zt25.converter.enums;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.CharSequenceUtil;
import org.zt25.converter.ConverterException;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 基本类型转换器
 *
 * <p>
 * 如果一个类实现了此接口，则其可以被转换为指定的类型。
 * </p>
 *
 * @author zhaotaiyu
 * @version 1.0
 * @since 2023/3/3 9:58
 **/
public enum SupportDataConverter {
    BOOLEAN{
        @Override
        protected Boolean doConversion(Object value) {
            return Convert.toBool(value);
        }
        @Override
        public String toString() {
            return Boolean.class.getSimpleName();
        }
    },
    BYTE{
        @Override
        protected Byte doConversion(Object value) {
            return Convert.toByte(value);
        }
        @Override
        public String toString() {
            return Byte.class.getSimpleName();
        }
    },
    SHORT{
        @Override
        protected Short doConversion(Object value) {
            return Convert.toShort(value);
        }
        @Override
        public String toString() {
            return Short.class.getSimpleName();
        }
    },
    LONG{
        @Override
        protected Long doConversion(Object value) {
            return Convert.toLong(value);
        }
        @Override
        public String toString() {
            return Long.class.getSimpleName();
        }
    },
    BIGDECIMAL{
        @Override
        protected BigDecimal doConversion(Object value) {
            return Convert.toBigDecimal(value);
        }
        @Override
        public String toString() {
            return BigDecimal.class.getSimpleName();
        }
    },
    STRING{
        @Override
        protected String doConversion(Object value) {
            return Convert.toStr(value);
        }
        @Override
        public String toString() {
            return String.class.getSimpleName();
        }
    },
    DATE{
        @Override
        protected Date doConversion(Object value) {
            if (CharSequenceUtil.equalsIgnoreCase( "now", CharSequenceUtil.trim(Convert.toStr(value)))) {
                return new Date();
            } else {
                return Convert.toDate(value);
            }

        }
        @Override
        public String toString() {
            return Date.class.getSimpleName();
        }
    },
    INTEGER{
        @Override
        protected Integer doConversion(Object value) {
            return Convert.toInt(value);
        }
        @Override
        public String toString() {
            return Integer.class.getSimpleName();
        }
    },
    FLOAT{
        @Override
        protected Float doConversion(Object value) {
            return Convert.toFloat(value);
        }
        @Override
        public String toString() {
            return Float.class.getSimpleName();
        }
    },
    DOUBLE{
        @Override
        protected Double doConversion(Object value) {
            return Convert.toDouble(value);
        }
        @Override
        public String toString() {
            return Double.class.getSimpleName();
        }
    },
    CHARACTER{
        @Override
        protected Character doConversion(Object value) {
            return Convert.toChar(value);
        }
        @Override
        public String toString() {
            return Character.class.getSimpleName();
        }
    }
    ;

    public Object convert(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return doConversion(value);
        } catch (ConverterException e) {
            throw e;
        } catch (Exception e) {
            throw new ConverterException(value.toString(), toString());
        }
    }

    protected abstract Object doConversion(Object value);
}

package org.zt25.core;

import java.util.Arrays;
import java.util.List;

/**
 * zt25框架枚举接口
 *
 * <p>
 * 项目中的enum需要实现此接口
 * </p>
 *
 * @author zhaotaiyu
 * @version 1.0
 * @since 2023/3/3 9:58
 **/
public interface ZEnum<K, V> {

    K getCode();

    V getDesc();

    /**
     * 根据code获取枚举对象
     *
     * @param <T>      集成MiaEnum的子类
     * @param enumType 枚举类
     * @param code     code值
     * @return
     */
    default <T extends ZEnum<K, V>> T valueOf(Class<? extends ZEnum<K, V>> enumType, K code) {
        if (enumType == null || code == null) {
            return null;
        }
        T[] enumConstants = (T[]) enumType.getEnumConstants();
        if (enumConstants == null) {
            return null;
        }
        for (T enumConstant : enumConstants) {
            Object obj = enumConstant.getCode();
            if (code.equals(obj)) {
                return enumConstant;
            }
        }
        return null;
    }

    /**
     * 获取枚举code集合
     *
     * @param enumClass 枚举类
     * @return java.util.List<K>
     **/
    static <K, V, T extends Enum<?> & ZEnum<K, V>> List<K> getCodesByEnum(Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(ZEnum::getCode)
                .toList();
    }

    /**
     * 根据code获取枚举
     *
     * @param enumClass 枚举类
     * @param code      枚举code
     * @return T
     **/
    static <K, V, T extends Enum<?> & ZEnum<K, V>> T getEnumByCode(Class<T> enumClass, K code) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(t -> t.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("getEnumByCode error"));
    }

    /**
     * 根据desc获取枚举
     *
     * @param enumClass 枚举类
     * @param desc      枚举desc
     * @return T
     **/
    static <K, V, T extends Enum<?> & ZEnum<K, V>> T getEnumByDesc(Class<T> enumClass, V desc) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(t -> t.getDesc().equals(desc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("getEnumByDesc error"));
    }

    /**
     * 根据code获取desc
     *
     * @param enumClass 枚举类
     * @param code      枚举code
     * @return V
     **/
    static <K, V, T extends Enum<?> & ZEnum<K, V>> V getDescByCode(Class<T> enumClass, K code) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(t -> t.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("getEnumByDesc error"))
                .getDesc();
    }

    /**
     * 根据desc获取code
     *
     * @param enumClass 枚举类
     * @param desc      枚举desc
     * @return K
     **/
    static <K, V, T extends Enum<?> & ZEnum<K, V>> K getCodeByDesc(Class<T> enumClass, V desc) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(t -> t.getDesc().equals(desc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("getCodeByDesc error"))
                .getCode();
    }
}

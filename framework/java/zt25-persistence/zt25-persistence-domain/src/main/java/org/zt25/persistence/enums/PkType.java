package org.zt25.persistence.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 主键类型枚举类
 *
 * <p>
 * 添加到实体类上,在新增数据时拦截器根据当前标注的类型进行主键生成并填充
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Getter
@AllArgsConstructor
public enum PkType {

    CUSTOM("custom","自定义主键类型"),
    AUTO_INCR("autoIncr","自增主键类型(仅支持mysql)"),
    UUID("uuid","uuid"),
    SIMPLE_UUID("simpleUuid","SimpleUuid"),
    SNOW_FLAKE_STRING("snowFlakeString","雪花算法(String)"),
    SNOW_FLAKE("snowFlake","雪花算法(Long)"),
    OBJECT_ID("objectId","ObjectId"),
    NANO("nano","Nano算法"),
    DEFAULT("default","默认主键类型(根据主键类型自适应:String/Long=simpleUuid/snowFlake)")
    ;
    private final String code;
    private final String desc;

    /**
     * 获取主键类型
     *
     * @param pkType 主键类型字符串
     */
    public static PkType getPkType(String pkType) {
        for (PkType type : PkType.values()) {
            if (type.code.equalsIgnoreCase(pkType)) {
                return type;
            }
        }
        return DEFAULT;
    }

}

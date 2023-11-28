package org.zt25.persistence.annotation;

import org.zt25.persistence.PrimaryKeyGenerator;
import org.zt25.persistence.enums.PkType;

import java.lang.annotation.*;

/**
 * 实体类主键类型
 *
 * <p>
 * 根据定义的类型,在新建数据时自动调用相应的主键生成器填充主键数据.
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PrimaryKey {

    /**
     * 主键类型 {@link PkType}
     */
    PkType type() default PkType.DEFAULT;

    /**
     * 自定义主键生成类.通过此名称从spring环境中获取实例
     */
    Class<? extends PrimaryKeyGenerator> generator() default PrimaryKeyGenerator.class;
}

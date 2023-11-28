package org.zt25.persistence;

import java.io.Serializable;

/**
 * 主键生成器接口
 *
 * <p>
 * 自定义主键生成器需实现此接口,并使用@Component注入到spring环境中
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
public interface PrimaryKeyGenerator<T extends Serializable> {

    /**
     * 获取主键
     */
    T getPk();
}

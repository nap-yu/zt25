package org.zt25.persistence;

import java.io.Serializable;

/**
 * 自定义默认值填充策略
 *
 * <p>
 * 如果需要自定义默认值填充策略,需实现此接口.
 * </p>
 *
 * @author zhaotaiyu
 * @version 1.0
 * @since 2023/3/3 9:58
 **/
public interface DefaultValueFillStrategy<T extends Serializable> {

    public T value(Object o);
}

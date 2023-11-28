package org.zt25.converter.plugin;

import org.zt25.converter.annotation.ZDataHandler;

/**
 * 数据处理接口定义
 *
 * <p>
 *  PDataHandler中handler的自定实现需集成此接口
 * </p>
 *
 * @author zhaotaiyu
 * @version 1.0
 * @since 2023/9/20 9:58
 **/
public interface ZDataHandlerPlugin {

    <T> T invoke(Object source, Object name, Object value, T type, ZDataHandler annotation);
}

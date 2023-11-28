package org.zt25.web.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.zt25.core.ZObject;

import java.io.Serial;

/**
 * 客户端设备信息
 *
 * <p>
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Accessors(chain = true)
@Data
public final class DeviceInfo implements ZObject {

    @Serial
    private static final long serialVersionUID = -3337994727039085698L;

    /**
     * 浏览器名称
     */
    private String browser;

    /**
     * 浏览器版本
     */
    private String browserVersion;

    /**
     * 浏览器内核名称
     */
    private String engine;

    /**
     * 浏览器内核版本
     */
    private String engineVersion;

    /**
     * 客户端系统名称
     */
    private String os;

    /**
     * 客户端系统名称
     */
    private String osVersion;

    /**
     * 客户端系统类型
     */
    private String platform;
}

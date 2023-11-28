package org.zt25.web.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.zt25.core.ZObject;

import java.io.Serial;
import java.util.Map;

/**
 * HTTP请求对象
 *
 * <p>
 * 在默认拦截器中创建并保存到进程上下文对象中
 * </p>
 *
 * @author zhaotaiyu
 * @version 1.0
 * @since 2023/3/3 9:58
 **/
@Accessors(chain = true)
@Data
public final class HttpRequestInfo implements ZObject {

    @Serial
    private static final long serialVersionUID = -7860810598949653575L;

    /**
     * 得到请求的URL地址
     */
    private String requestUrl;

    /**
     * 得到请求的URI地址
     */
    private String requestUri;

    /**
     * url参数
     */
    private String queryString;

    /**
     * 得到来访者的IP地址
     */
    private String remoteAddr;

    /**
     * host
     */
    private String remoteHost;

    /**
     * 端口
     */
    private int remotePort;

    /**
     * 得到请求URL地址时使用的方法
     */
    private String method;

    /**
     * 获取WEB服务器的IP地址
     */
    private String localAddress;

    /**
     * 获取WEB服务器的主机名
     */
    private String localName;

    /**
     * 设备信息 {@link DeviceInfo}
     */
    private DeviceInfo deviceInfo;

    /**
     * 请求头
     */
    private Map<String, String> headers;

    /**
     * 通过key获取RequestHeader的值
     *
     * @param key 键
     * @return 值
     */
    public String getHeader(String key) {
        if (this.headers != null) {
            return this.getHeaders().get(key.toLowerCase());
        }
        return null;
    }
}

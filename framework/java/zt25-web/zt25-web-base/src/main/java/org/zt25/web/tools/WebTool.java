package org.zt25.web.tools;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.zt25.web.constants.Constant;
import org.zt25.web.domain.DeviceInfo;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * web工具类
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Slf4j
public final class WebTool {
    private WebTool(){}

    /**
     * 获取ip
     * @param request {@link HttpServletRequest}
     * @return ip
     */
    public static String getIp(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.isEmpty() || Constant.UNKNOWN_IP.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || Constant.UNKNOWN_IP.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || Constant.UNKNOWN_IP.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (Constant.LOCAL_IP.equals(ipAddress) || Constant.NULL_IP.equals(ipAddress)) {
                //根据网卡获取本机配置的IP地址
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("获取客户端ip异常,异常原因:"+e.getMessage());
                }
                ipAddress = inetAddress!=null?inetAddress.getHostAddress():"Not Obtained IP";
            }
        }

        // 对于通过多个代理的情况，第一个IP为客户端真实的IP地址，多个IP按照','分割
        if (null != ipAddress && ipAddress.length() > Constant.IP_LENGTH) {
            //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") >= 1) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }

        return ipAddress;
    }

    /**
     * 获取设备类型
     * @param request {@link HttpServletRequest}
     * @return {@link DeviceInfo}
     */
    public static DeviceInfo getDevice(HttpServletRequest request){
        String requestHeader = request.getHeader("user-agent");
        UserAgent userAgent = UserAgentUtil.parse(requestHeader);

        return new DeviceInfo()
                .setBrowser(userAgent.getBrowser().getName())
                .setBrowserVersion(userAgent.getVersion())
                .setEngine(userAgent.getEngine().getName())
                .setEngineVersion(userAgent.getEngineVersion())
                .setOs(userAgent.getOs().getName())
                .setPlatform(userAgent.getPlatform().getName())
                .setOsVersion(userAgent.getOsVersion());
    }

    /**
     * 获取request中的header信息(其中key与spring的@RequestHeader注解保持一致,统一为小写)
     * @param request {@link HttpServletRequest}
     * @return header数据
     */
    public static Map<String,String> getHeaders(HttpServletRequest request){
        Map<String,String> headers = new HashMap<>();

        // 获取header的key列表
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName.toLowerCase(),request.getHeader(headerName));
        }
        return headers;
    }
}

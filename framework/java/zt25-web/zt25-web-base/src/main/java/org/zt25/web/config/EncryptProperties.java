package org.zt25.web.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 请求出入参加解密配置
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Component
@ConfigurationProperties()
@Data
public class EncryptProperties {
    /**
     * 加密级别
     * 0/1
     */
    @Value("${zt25.web.encrypt.level:1}")
    public int level;
    @Value("${zt25.web.encrypt.algorithm.request:base64}")
    public String request;
    @Value("${zt25.web.encrypt.algorithm.response:base64}")
    public String response;

}

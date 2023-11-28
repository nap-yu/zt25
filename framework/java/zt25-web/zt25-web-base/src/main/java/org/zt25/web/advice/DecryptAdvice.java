package org.zt25.web.advice;

import cn.hutool.extra.spring.SpringUtil;
import org.zt25.web.annotation.Decrypt;
import org.zt25.web.config.EncryptProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import org.zt25.tools.encrypt.ZEncrypt;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * 请求解密适配器
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@EnableConfigurationProperties(EncryptProperties.class)
@RestControllerAdvice
@Order(1)
public class DecryptAdvice extends RequestBodyAdviceAdapter {

    /**
     * 请求出入参加解密配置
     */
    private final EncryptProperties encryptProperties;

    @Autowired
    public DecryptAdvice(EncryptProperties encryptProperties) {
        this.encryptProperties = encryptProperties;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasMethodAnnotation(Decrypt.class) || methodParameter.hasParameterAnnotation(Decrypt.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(final HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        byte[] body = new byte[inputMessage.getBody().available()];

        // 获取解密算法名称,用于获取解密处理类
        String algorithm = inputMessage.getHeaders().get("p-encrypt-algorithm")!=null?inputMessage.getHeaders().get("p-encrypt-algorithm").get(0).toString():encryptProperties.getRequest();

        // 获取解密算法名称,用于获取解密处理类
        //int level = inputMessage.getHeaders().get("p-encrypt-level")!=null?Integer.parseInt(inputMessage.getHeaders().get("p-encrypt-level").get(0).toString()):0;

        //if(level> encryptProperties.getLevel()){
        //    throw new IOException("加密级别过低");
        //}

        inputMessage.getBody().read(body);
        try {
            ZEncrypt apiEncrypt = (ZEncrypt) SpringUtil.getBean(algorithm+"Encrypt");
            return new HttpInputMessage() {
                @Override
                public InputStream getBody() throws IOException {
                    String s = apiEncrypt.decrypt(new String(body, StandardCharsets.UTF_8));
                    return new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
                }

                @Override
                public HttpHeaders getHeaders() {
                    return inputMessage.getHeaders();
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.beforeBodyRead(inputMessage, parameter, targetType, converterType);
    }

}

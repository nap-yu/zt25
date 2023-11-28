package org.zt25.web.advice;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.zt25.web.annotation.Encrypt;
import org.zt25.web.config.EncryptProperties;
import org.zt25.web.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.zt25.tools.encrypt.ZEncrypt;

/**
 * 响应加密适配器
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
public class EncryptAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 请求出入参加解密配置
     */
    private final EncryptProperties encryptProperties;

    @Autowired
    public EncryptAdvice(EncryptProperties encryptProperties) {
        this.encryptProperties = encryptProperties;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        int level = encryptProperties.getLevel();

        ZEncrypt encrypt = SpringUtil.getBean(encryptProperties.getResponse()+"Encrypt");

        response.getHeaders().set("p-encrypt-algorithm",encryptProperties.response);
        response.getHeaders().set("p-encrypt-level",String.valueOf(encryptProperties.level));

        String result=encrypt.encrypt(String.valueOf(body));

        if(level==0){
            return result;
        }else{
            String className = body.getClass().getName();

            if(CharSequenceUtil.equalsIgnoreCase(R.class.getName(),className)) {
                R resp = (R) body;
                resp.setData(encrypt.encrypt(resp.getData().toString()));
                return resp;
            }else{
                return result;
            }
        }

    }
}

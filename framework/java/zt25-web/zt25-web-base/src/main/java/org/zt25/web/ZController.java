package org.zt25.web;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.zt25.core.ZException;
import org.zt25.web.domain.DeviceInfo;
import org.zt25.web.domain.R;
import org.zt25.web.enums.WebStatus;
import org.zt25.web.tools.WebTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * controller基类。封装了一下常用方法
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @version 1.0
 * @since 2023/3/3 9:58
 **/
@Slf4j
@RestController
public abstract class ZController {

    @Value("${spring.application.name}")
    protected String appName;

    @Resource(type = HttpSession.class)
    private HttpSession session;

    @Resource(type = HttpServletRequest.class)
    private HttpServletRequest request;


    /**
     * 获取ip
     *
     * @return String
     */
    protected String getIp() {
        return WebTool.getIp(this.request);
    }

    /**
     * 获取设备类型
     *
     * @return String
     */
    protected DeviceInfo getDevice() {
        return WebTool.getDevice(this.request);
    }

    /**
     * 获取request中的header信息(其中key与spring的@RequestHeader注解保持一致,统一为小写)
     *
     * @return Map<String, String>
     */
    protected Map<String, String> getHeaders() {
        return WebTool.getHeaders(this.request);
    }

    protected <T> R<T> success() {
        return R.success();
    }

    protected <T> R<T> success(String message, T data) {
        return R.success(message, data);
    }

    protected <T> R<T> success(T data) {
        return R.success(data);
    }

    protected <T> R<T> fail() {
        return R.fail();
    }

    protected <T> R<T> fail(String message) {
        return R.fail(message);
    }

    protected <T> R<T> fail(WebStatus webStatusEnum) {
        return R.fail(webStatusEnum);
    }

    protected <T> R<T> fail(WebStatus webStatusEnum, String message) {
        return R.fail(webStatusEnum, message);
    }

    protected <T> R<T> fail(ZException exception) {
        return R.fail(exception);
    }

    protected <T> R<T> fail(WebStatus webStatusEnum, String message, ZException exception) {
        return R.fail(webStatusEnum, message, exception);
    }
}

package org.zt25.web.interceptor;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.zt25.web.domain.HttpRequestInfo;
import org.zt25.web.domain.ZContext;
import org.zt25.web.domain.State;
import org.zt25.web.tools.WebTool;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 默认请求拦截器处理白名单，用户请求上下文
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
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求信息
        HttpRequestInfo httpRequestInfo = this.getHttpRequestInfo(request);

        // 放到请求上下文对象中
        ZContext.addState(new State().setRequestContext(JSON.toJSONString(httpRequestInfo)));

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除当前线程中的上下文数据
        ZContext.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 从request请求中获取请求信息
     * @param request {@link HttpServletRequest}
     * @return {@link HttpRequestInfo}
     */
    private HttpRequestInfo getHttpRequestInfo(HttpServletRequest request){

        String requestUrl = request.getRequestURL().toString();//得到请求的URL地址
        String requestUri = request.getRequestURI();//得到请求的资源
        String queryString = request.getQueryString();//得到请求的URL地址中附带的参数
        String remoteAddr = WebTool.getIp(request);//得到来访者的IP地址
        String remoteHost = request.getRemoteHost();
        int remotePort = request.getRemotePort();
        String method = request.getMethod();//得到请求URL地址时使用的方法
        String localAddr = request.getLocalAddr();//获取WEB服务器的IP地址
        String localName = request.getLocalName();//获取WEB服务器的主机名


        HttpRequestInfo info = new HttpRequestInfo();
        info.setRequestUrl(requestUrl);
        info.setRequestUri(requestUri);
        info.setQueryString(queryString);
        info.setRemoteAddr(remoteAddr);
        info.setRemoteHost(remoteHost);
        info.setRemotePort(remotePort);
        info.setMethod(method);
        info.setLocalAddress(localAddr);
        info.setLocalName(localName);
        info.setDeviceInfo(WebTool.getDevice(request));
        info.setHeaders(WebTool.getHeaders(request));

        return info;
    }

}

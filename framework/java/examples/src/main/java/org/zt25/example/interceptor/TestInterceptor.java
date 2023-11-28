package org.zt25.example.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.zt25.web.annotation.CustomInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;


@CustomInterceptor(addPath = {"/**"},order = 202)
public class TestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        this.test();


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private void test(){
        System.out.println("触发了自定义拦截器[TestInterceptor]");
    }
}

package com.cy.ruoyi.common.core.interceptor;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.utils.util.IpUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * springMvc拦截器
 */
@Component
public class UrlInterceptor extends HandlerInterceptorAdapter {

    private static final Log log = LogFactory.get();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            //如果是OPTIONS的请求，不要打印日志
            if (RequestMethod.OPTIONS.toString().equals(request.getMethod())) {
                return true;
            }
            log.info("请求地址:{}，请求方式:{},请求的IP:{},User-Agent:{}", request.getRequestURL(), request.getMethod(), IpUtils.getIpAddr(request), request.getHeader("User-Agent"));
            return true;
        } catch (Exception e) {
            log.error("请求拦截异常：{}", e);
            return false;
        }
    }
}

package com.leaning.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*", filterName = "DemoFilter")//拦截所有请求
public class DemoFilter implements Filter {
    //初始化方法，只运行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("DemoFilter初始化.......");
        Filter.super.init(filterConfig);
    }

    //过滤方法，每次请求都会运行
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("DemoFilter拦截到了请求.......");
        //放行请求
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //销毁方法，只运行一次
    @Override
    public void destroy() {
        log.info("DemoFilter销毁.......");
        Filter.super.destroy();
    }
}

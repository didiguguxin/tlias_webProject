package com.leaning.filter;

import com.leaning.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter("/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1. 获取请求uri。
        String uri = request.getRequestURI();

        //2. 判断请求url中是否包含login，如果包含，说明是登录操作，放行。
        if (uri.contains("login")) {
            log.info("登录请求，放行");
            filterChain.doFilter(request, response);
            return;
        }

        //3. 获取请求头中的令牌（token）。
        String token = request.getHeader("token");
        log.info("请求头中的令牌：{}", token);

        //4. 判断令牌是否存在，如果不存在，返回错误结果（未登录）。
        if (token == null || token.isEmpty()) {
            log.info("令牌不存在，响应401未登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //5. 校验token，如果解析失败，返回错误结果（未登录）。
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            log.info("令牌解析失败，响应401未登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //6. 如果解析成功，说明是登录状态，放行。
        log.info("令牌解析成功，放行");
        filterChain.doFilter(request, response);
    }
}

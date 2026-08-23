package com.leaning.interceptor;

import com.leaning.utils.CurrentHolder;
import com.leaning.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取请求头中的令牌（token）。
        String token = request.getHeader("token");
        log.info("请求头中的令牌：{}", token);

        //判断令牌是否存在，如果不存在，返回错误结果（未登录）。
        if (token == null || token.isEmpty()) {
            log.info("令牌不存在，响应401未登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 校验token，如果解析失败，返回错误结果（未登录）。
        try {
            Claims claims = JwtUtils.parseJwt (token);
            Integer userId = claims.get ("id", Integer.class);
            CurrentHolder.setCurrentId (userId);
            log.info("当前线程的用户 ID：{}", userId);
        } catch (Exception e) {
            log.info("令牌解析失败，响应 401 未登录");
            response.setStatus (HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //如果解析成功，说明是登录状态，放行。
        log.info("令牌解析成功，放行");
        return true;


    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        //清除 ThreadLocal，防止内存泄漏
        CurrentHolder.remove();
        log.debug("已清除当前线程的用户信息");
    }
    }





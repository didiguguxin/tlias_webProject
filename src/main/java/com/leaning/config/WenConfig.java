package com.leaning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 配置类，用于配置拦截器
@Configuration
public class WenConfig implements WebMvcConfigurer {

   @Autowired
    private com.leaning.interceptor.TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }


}

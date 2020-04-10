package com.example.demo.org.config;

import com.example.demo.org.interceptor.interce;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class interce_config implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径''
        registry.addInterceptor(new interce()).addPathPatterns("/**").excludePathPatterns();
    }
}

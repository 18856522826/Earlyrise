package com.example.demo.org.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class staticconfig implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/earlyriseu/**").addResourceLocations("file:d://earlyriseu/");
            //registry.addResourceHandler("/earlyriseu/**").addResourceLocations("file:/earlyriseu/");//线上
 }
}

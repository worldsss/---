package com.example.bcyimitation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * axios的跨域问题的解决
 */
@Configuration
public class CrosConfig implements WebMvcConfigurer {

   /* @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");

    }*/


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");

    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取文件的真实路径
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\img\\";
        //       /images/**是对应resource下工程目录
        registry.addResourceHandler("/static/img/**").addResourceLocations("file:"+path);
    }

/*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // 映射图片保存地址
    registry
        .addResourceHandler("/static/img/**")
        .addResourceLocations(
            "D:\\All_Project\\IDEA_Project\\bcy-imitation\\src\\main\\resources\\static\\img");
    }*/
}

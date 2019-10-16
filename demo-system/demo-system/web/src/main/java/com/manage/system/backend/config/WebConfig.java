package com.manage.system.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wucc
 * @date 2019/8/29 8:48
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload.file.dir}")
    String filePath;
    @Value("${upload.pattern.img}")
    String imgPattern;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(imgPattern + "/**")
                .addResourceLocations("file:" + filePath);
    }
}

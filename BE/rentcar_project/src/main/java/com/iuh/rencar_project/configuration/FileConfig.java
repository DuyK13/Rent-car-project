package com.iuh.rencar_project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/23/2021 4:03 PM
 */
@Configuration
public class FileConfig {
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        return multipartResolver;
    }
}

package com.onlineShop;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/photos/**").addResourceLocations("file://" + System.getProperty("user.dir") + "/src/main/photos/");
        registry.addResourceHandler("/images/**").addResourceLocations("file://" + System.getProperty("user.dir") + "/src/main/resources/images/");
    }
}

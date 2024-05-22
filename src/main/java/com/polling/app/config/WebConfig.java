package com.polling.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class WebConfig {

    @Bean
    @RequestScope
    public HttpServletRequest httpServletRequest(HttpServletRequest request) {
        return request;
    }
}


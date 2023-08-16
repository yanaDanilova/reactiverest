package com.sprinboot.webflux.reactiverest.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprinboot.webflux.reactiverest.exceptions.CustomGlobalErrorHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorHandlingConfig {
    @Bean
    public ErrorWebExceptionHandler errorWebExceptionHandler(ErrorAttributes errorAttributes) {
        return new CustomGlobalErrorHandler();
    }
}

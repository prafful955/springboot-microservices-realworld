package com.example.employeeservice.config;

import feign.RequestInterceptor;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignCorrelationInterceptor {

    @Bean
    public RequestInterceptor correlationIdInterceptor() {
        return template -> {
            String correlationId = MDC.get("cid");
            if (correlationId != null && !correlationId.isBlank()) {
                template.header("X-Correlation-Id", correlationId);
            }
        };
    }
}
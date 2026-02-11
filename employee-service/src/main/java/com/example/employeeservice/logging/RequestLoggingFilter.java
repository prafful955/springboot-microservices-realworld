

package com.example.employeeservice.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private static final String CORRELATION_ID_HEADER = "X-Correlation-Id";
    private static final String MDC_KEY = "cid";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        try {
            // 1. Get or generate correlation ID
            String correlationId = request.getHeader(CORRELATION_ID_HEADER);
            if (correlationId == null || correlationId.isBlank()) {
                correlationId = UUID.randomUUID().toString();
            }

            // 2. Put into MDC
            MDC.put(MDC_KEY, correlationId);

            // 3. Log request
            log.info("[REQ] method={} uri={}", request.getMethod(), request.getRequestURI());

            // 4. Continue filter chain
            filterChain.doFilter(request, response);

            long duration = System.currentTimeMillis() - startTime;

            // 5. Log response
            log.info("[RES] status={} time={}ms", response.getStatus(), duration);

        } finally {
            // 6. VERY IMPORTANT: clear MDC to avoid memory leaks
            MDC.clear();
        }
    }
}

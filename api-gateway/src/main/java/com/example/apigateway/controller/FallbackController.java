package com.example.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController
{

    @GetMapping("/employee")
    public Mono<ResponseEntity<Map<String, Object>>> employeeFallback()
    {

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employee Service is currently unavailable");
        response.put("status", "GATEWAY_FALLBACK");

        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(response)
        );
    }
}

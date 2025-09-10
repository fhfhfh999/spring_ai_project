package com.sustech.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Value("${quotes.api.key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}

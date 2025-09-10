package com.sustech.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiClient {

    private final RestTemplate restTemplate;

    public ApiClient() {
        this.restTemplate = new RestTemplate();
    }

    public String get(String apiUrl) {
        return restTemplate.getForObject(apiUrl, String.class);
    }
}


package com.sustech.client;

import com.sustech.utils.ApiUtils;
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

    public String getRandomImageUrl() {
        String apiUrl = "https://t.alcy.cc/ycy?json";// 看着像是json实际上是一个图片网站
        return get(apiUrl);
    }

    public String getApiResponse(String apiUrl, String fieldName) {
        String response = get(apiUrl);
        return ApiUtils.parseResponse(response, fieldName);
    }
}


package com.sustech.service;

import com.sustech.config.ApiConfig;
import com.sustech.utils.apiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    private final ApiConfig apiConfig;
    private final apiUtils apiUtils;

    // 通过构造函数注入依赖
    @Autowired
    public QuoteService(ApiConfig apiConfig, apiUtils apiUtils) {
        this.apiConfig = apiConfig;
        this.apiUtils = apiUtils;
    }

    public String getAncientQuote() {
        String ancientQuoteApiUrl = "https://apis.tianapi.com/gjmj/index?key=" + apiConfig.getApiKey();
        return apiUtils.getApiResponse(ancientQuoteApiUrl, "quote");
    }

    public String getMoodPoetry() {
        String moodPoetryApiUrl = "https://apis.tianapi.com/moodpoetry/index?key=" + apiConfig.getApiKey();
        return apiUtils.getApiResponse(moodPoetryApiUrl, "Poetry");
    }

    public String getRandomImageUrl() {
        return apiUtils.getRandomImageUrl();
    }
}
package com.sustech.service;

import com.sustech.client.ApiClient;
import com.sustech.config.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TimelyApiService {

    private final ApiClient apiClient;
    private final ApiConfig apiConfig;

    @Autowired
    public TimelyApiService(ApiClient apiClient, ApiConfig apiConfig) {
        this.apiClient = apiClient;
        this.apiConfig = apiConfig;
    }

    @Cacheable(value = "bgImage", key = "'random'")
    public String getBackgroundImageUrl() {
        // 只有缓存失效时才会真的调用外部 API
        return apiClient.getRandomImageUrl();
    }

    @Cacheable(value = "quote", key = "'ancient'")
    public String getAncientQuote() {
        String ancientQuoteApiUrl = "https://apis.tianapi.com/gjmj/index?key=" + apiConfig.getApiKey();
        return apiClient.getApiResponse(ancientQuoteApiUrl, "quote");
    }

    @Cacheable(value = "quote", key = "'mood'")
    public String getMoodPoetry() {
        String moodPoetryApiUrl = "https://apis.tianapi.com/moodpoetry/index?key=" + apiConfig.getApiKey();
        return apiClient.getApiResponse(moodPoetryApiUrl, "Poetry");
    }

//    // 每30分钟刷新一次缓存
//    @Scheduled(fixedRate = 30 * 60 * 1000)
//    @CacheEvict(value = "bgImage", key = "'random'")
//    public void refreshBackgroundImageCache() {
//        // 这个方法用于定期清除缓存，下次请求时会获取新的背景图片
//    }
}

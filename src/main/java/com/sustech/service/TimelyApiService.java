package com.sustech.service;

import com.sustech.config.ApiConfig;
import com.sustech.utils.apiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TimelyApiService {

    private final apiUtils apiUtils;
    private final ApiConfig apiConfig;

    @Autowired
    public TimelyApiService(apiUtils apiUtils, ApiConfig apiConfig) {
        this.apiUtils = apiUtils;
        this.apiConfig = apiConfig;
    }

    @Cacheable(value = "bgImage", key = "'random'")
    public String getBackgroundImageUrl() {
        // 只有缓存失效时才会真的调用外部 API
        return apiUtils.getRandomImageUrl();
    }

    @Cacheable(value = "quote", key = "'ancient'")
    public String getAncientQuote() {
        String ancientQuoteApiUrl = "https://apis.tianapi.com/gjmj/index?key=" + apiConfig.getApiKey();
        return apiUtils.getApiResponse(ancientQuoteApiUrl, "quote");
    }

    @Cacheable(value = "quote", key = "'mood'")
    public String getMoodPoetry() {
        String moodPoetryApiUrl = "https://apis.tianapi.com/moodpoetry/index?key=" + apiConfig.getApiKey();
        return apiUtils.getApiResponse(moodPoetryApiUrl, "Poetry");
    }

//    // 每30分钟刷新一次缓存
//    @Scheduled(fixedRate = 30 * 60 * 1000)
//    @CacheEvict(value = "bgImage", key = "'random'")
//    public void refreshBackgroundImageCache() {
//        // 这个方法用于定期清除缓存，下次请求时会获取新的背景图片
//    }
}

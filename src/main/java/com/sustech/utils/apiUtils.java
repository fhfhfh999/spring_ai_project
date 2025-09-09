package com.sustech.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

public class apiUtils {
    // 使用 ObjectMapper 来解析 API 返回的 JSON 数据
    public static String getApiResponse(String apiUrl, String fieldName) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl, String.class);

        // 使用 Jackson ObjectMapper 解析 JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode resultNode = rootNode.path("result");
            String content = resultNode.path("content").asText();
            if (fieldName.equals("quote")) {
                String source = resultNode.path("source").asText();
                return content + " —— " + source;  // 返回古籍名句
            }else {
                String title = resultNode.path("title").asText();
                String author = resultNode.path("author").asText();
                return content + " —— " + title + " " + author;
            }
             // 格式化名句和来源
        } catch (Exception e) {
            e.printStackTrace();
            return "无法获取名句";  // 错误处理
        }
    }

    // 获取随机背景图片的 URL
    public static String getRandomImageUrl() {
        String apiUrl = "https://t.alcy.cc/ycy?json";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl, String.class);

        // 解析返回的 JSON，提取图片 URL
        String imageUrl = null;  // 获取图片 URL
        if (response != null) {
            imageUrl = response.substring(response.indexOf("url") + 5, response.indexOf("webp") + 4);
        }
        if (imageUrl != null && imageUrl.startsWith("s://")) {
            imageUrl = "https" + imageUrl.substring(1);  // 修正 URL 前缀
        }
        return imageUrl;
    }
}

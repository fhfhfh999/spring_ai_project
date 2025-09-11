package com.sustech.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ApiUtils {

    public static String parseResponse(String response, String fieldName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode resultNode = rootNode.path("result");
            String content = resultNode.path("content").asText();
            if (fieldName.equals("quote")) {
                String source = resultNode.path("source").asText();
                return content + " —— " + source;
            } else {
                String title = resultNode.path("title").asText();
                String author = resultNode.path("author").asText();
                return content + " —— " + title + " " + author;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "无法获取数据";
        }
    }

}
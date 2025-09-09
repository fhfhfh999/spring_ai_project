package com.sustech.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//通过 @Bean 注解将 ChatClient 实例交由 Spring 容器管理,传入预设的系统消息字符串
//后续使用该 ChatClient 进行对话时，无需再额外添加该系统消息，AI 模型会自动按照预设的风格响应。
@Configuration
class Config {
    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("你是石晗峰的智能助手，主要回答IT问题")
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }


}

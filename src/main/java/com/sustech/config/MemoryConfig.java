package com.sustech.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemoryConfig {

    @Value("${spring.ai.memory.redis.host}")
    private String redisHost;
    @Value("${spring.ai.memory.redis.port}")
    private int redisPort;
    @Value("${spring.ai.memory.redis.password}")
    private String redisPassword;
    @Value("${spring.ai.memory.redis.timeout}")
    private int redisTimeout;

    public String getRedisHost() {
        return redisHost;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public String getRedisPassword() {
        return redisPassword;
    }

    public int getRedisTimeout() {
        return redisTimeout;
    }

//    @Bean
//    public RedisChatMemoryRepository redisChatMemoryRepository() {
//        return RedisChatMemoryRepository.builder()
//                .host(redisHost)
//                .port(redisPort)
//                .password(redisPassword)
//                .timeout(redisTimeout)
//                .build();
//    }
}

package com.sustech.repository;

import com.alibaba.cloud.ai.memory.redis.RedisChatMemoryRepository;
import com.sustech.config.MemoryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public class RedisChatRepository {

    private final MemoryConfig memoryConfig;

    public RedisChatRepository(MemoryConfig memoryConfig){
        this.memoryConfig = memoryConfig;
    }

    @Bean
    public RedisChatMemoryRepository redisMemoryRepository() {
        return RedisChatMemoryRepository.builder()
                .host(memoryConfig.getRedisHost())
                .port(memoryConfig.getRedisPort())
                .password(memoryConfig.getRedisPassword())
                .timeout(memoryConfig.getRedisTimeout())
                .build();
    }
}

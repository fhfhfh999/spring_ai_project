package com.sustech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {
    private static final int MAX_ATTEMPTS = 5;
    private static final long LOCK_TIME_DURATION = 30 * 60 * 1000L; // 30分钟


    private final RedisTemplate<String, Integer> redisTemplate; // 使用 Redis 存储失败次数

    @Autowired
    public LoginAttemptService(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isBlocked(String username) {
        String key = "loginAttempts:" + username;
        Integer attempts = redisTemplate.opsForValue().get(key);
        return attempts != null && attempts >= MAX_ATTEMPTS;
    }

    public void loginFailed(String username) {
        String key = "loginAttempts:" + username;
        Integer attempts = redisTemplate.opsForValue().get(key);
        if (attempts == null) {
            redisTemplate.opsForValue().set(key, 1, LOCK_TIME_DURATION, TimeUnit.MILLISECONDS);
        } else {
            redisTemplate.opsForValue().increment(key);
        }
    }

    public void loginSucceeded(String username) {
        String key = "loginAttempts:" + username;
        redisTemplate.delete(key);
    }
}


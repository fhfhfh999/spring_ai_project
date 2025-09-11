package com.sustech.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class sqlConfig {
    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    public String getJdbcUrl() {
        return jdbcUrl;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}

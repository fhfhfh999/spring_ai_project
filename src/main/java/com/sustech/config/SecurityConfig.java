package com.sustech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // 允许访问静态资源
                        .requestMatchers("/styles/**", "/scripts/**", "/webjars/**").permitAll()
                        // 允许访问登录页面和主页，无需认证
                        .requestMatchers(
                                "/",
                                "/login",
                                "/login/**",
                                "/register",
                                "/register/**").permitAll()
                        // 其他请求需要认证
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")  // 自定义登录页面
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()  // 允许用户注销
                );
        return http.build();
    }
}

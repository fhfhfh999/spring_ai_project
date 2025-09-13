package com.sustech.config;

import com.sustech.service.UserService;
import com.sustech.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userService.loadUserByUsername(username);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return SecurityUtils.encode(rawPassword.toString());
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return SecurityUtils.verifyPassword(rawPassword.toString(), encodedPassword);
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
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
                .csrf(csrf -> csrf
                        .csrfTokenRepository((CookieCsrfTokenRepository.withHttpOnlyFalse()))
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/Home", true)      // 登录成功后跳转的URL
                        .failureUrl("/login?error=true")       // 登录失败后跳转的URL
                )
                .logout(logout -> logout
                        .permitAll()  // 允许用户注销
                );
        return http.build();
    }
}

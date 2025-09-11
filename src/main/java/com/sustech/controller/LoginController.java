package com.sustech.controller;

import com.sustech.response.LoginResponse;
import com.sustech.service.LoginAttemptService;
import com.sustech.service.TimelyApiService;
import com.sustech.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final String HOME_PAGE = "/home";

    private final TimelyApiService timelyApiService;
    private final UserService userService;
    private final LoginAttemptService loginAttemptService;

    public LoginController(TimelyApiService timelyApiService, UserService UserService, LoginAttemptService loginAttemptService) {
        this.timelyApiService = timelyApiService;
        this.userService = UserService;
        this.loginAttemptService = loginAttemptService;
    }

    // 登录页面 GET 请求处理
    @GetMapping
    public String loginPage(Model model) {
        String randomImageUrl = timelyApiService.getBackgroundImageUrl();

        model.addAttribute("randomImageUrl", randomImageUrl);
        return "login";  // 返回登录页面视图
    }

    // 登录表单提交 POST 请求处理
    @PostMapping
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // 判断是否超过最大尝试次数
        if (loginAttemptService.isBlocked(username)) {
            model.addAttribute("error", "Your account is temporarily locked due to too many failed login attempts. Please try again later.");
            return "login";
        }

        // 验证用户
        LoginResponse loginResponse = userService.validateUser(username, password);

        if (loginResponse.isSuccess()) {
            loginAttemptService.loginSucceeded(username);
            return "redirect:" + HOME_PAGE;
        } else {
            loginAttemptService.loginFailed(username);
            // 登录失败，返回登录页面并显示错误信息
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }
}


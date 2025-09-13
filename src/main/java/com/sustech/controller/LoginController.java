package com.sustech.controller;

import com.sustech.service.TimelyApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {


    private final TimelyApiService timelyApiService;

    public LoginController(TimelyApiService timelyApiService) {
        this.timelyApiService = timelyApiService;
    }

    // 登录页面 GET 请求处理
    @GetMapping
    public String loginPage(Model model) {
        String randomImageUrl = timelyApiService.getBackgroundImageUrl();

        model.addAttribute("randomImageUrl", randomImageUrl);
        return "login";  // 返回登录页面视图
    }
}

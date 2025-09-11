package com.sustech.controller;

import com.sustech.service.TimelyApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private TimelyApiService timelyApiService;

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
        // 验证用户
        boolean isValidUser = true;

        if (isValidUser) {
            // 登录成功，重定向到主页或其他页面
            return "redirect:/Home"; // 重定向到首页
        } else {
            // 登录失败，返回登录页面并显示错误信息
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }
}


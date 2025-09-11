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
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private TimelyApiService timelyApiService;

    @GetMapping
    public String showRegisterPage(Model model) {
        // 假设你有一个方法获取随机背景图片的 URL
        String randomImageUrl = timelyApiService.getBackgroundImageUrl();
        model.addAttribute("randomImageUrl", randomImageUrl); // 将背景图片 URL 传递给模板
        return "register";
    }

    // 处理注册表单提交
    @PostMapping
    public String handleRegisterForm(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String email,
            @RequestParam String invitationCode){
        if (!password.equals(confirmPassword)) {
            // 如果密码不一致，返回注册页面并显示错误信息
            return "redirect:/register?error=passwordsDoNotMatch";
        }

        // TODO:注册逻辑

        return "redirect:/login"; // 注册成功后跳转到登录页面
    }

}

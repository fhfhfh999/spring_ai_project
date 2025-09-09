package com.sustech.controller;

import com.sustech.utils.apiUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

    @Value("${quotes.api.key}")  // 从 application.yml 读取 API Key
    private String apiKey;

    @GetMapping("/")
    public String home(Model model) {
        // 调用古籍名句 API
        String ancientQuoteApiUrl = "https://apis.tianapi.com/gjmj/index?key=" + apiKey;
        String ancientQuote = apiUtils.getApiResponse(ancientQuoteApiUrl, "quote");

        // 调用情绪诗句 API
        String moodPoetryApiUrl = "https://apis.tianapi.com/moodpoetry/index?key=" + apiKey;
        String moodPoetry = apiUtils.getApiResponse(moodPoetryApiUrl, "Poetry");

        // 获取随机背景图片的 URL
        String randomImageUrl = apiUtils.getRandomImageUrl();

        model.addAttribute("randomImageUrl", randomImageUrl);
        model.addAttribute("ancientQuote", ancientQuote);
        model.addAttribute("moodPoetry", moodPoetry);
        return "Home";  // 返回Home页面
    }



    // 跳转到登录页面
    @GetMapping("/login")
    public String login() {
        return "login";  // 返回登录页面视图
    }

    // 跳转到注册页面
    @GetMapping("/register")
    public String register() {
        return "register";  // 返回注册页面视图
    }
}

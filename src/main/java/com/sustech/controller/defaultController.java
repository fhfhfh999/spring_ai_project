package com.sustech.controller;

import com.sustech.service.TimelyApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class defaultController {

//    @Autowired
//    private QuoteService quoteService;
//
//    @Autowired
//    private BackgroundImageService backgroundImageService;
    @Autowired
    private TimelyApiService timelyApiService;

    @GetMapping("/")
    public String default_page(Model model) {
//        String ancientQuote = quoteService.getAncientQuote();
//        String moodPoetry = quoteService.getMoodPoetry();
//        String randomImageUrl = quoteService.getRandomImageUrl();
//        String randomImageUrl = backgroundImageService.getBackgroundImageUrl();
        String ancientQuote = timelyApiService.getAncientQuote();
        String moodPoetry = timelyApiService.getMoodPoetry();
        String randomImageUrl = timelyApiService.getBackgroundImageUrl();

        model.addAttribute("randomImageUrl", randomImageUrl);
        model.addAttribute("ancientQuote", ancientQuote);
        model.addAttribute("moodPoetry", moodPoetry);
        return "default_page";
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

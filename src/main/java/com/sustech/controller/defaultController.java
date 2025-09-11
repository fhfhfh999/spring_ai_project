package com.sustech.controller;

import com.sustech.service.TimelyApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class defaultController {

    @Autowired
    private TimelyApiService timelyApiService;

    @GetMapping("/")
    public String default_page(Model model) {
        String ancientQuote = timelyApiService.getAncientQuote();
        String moodPoetry = timelyApiService.getMoodPoetry();
        String randomImageUrl = timelyApiService.getBackgroundImageUrl();

        model.addAttribute("randomImageUrl", randomImageUrl);
        model.addAttribute("ancientQuote", ancientQuote);
        model.addAttribute("moodPoetry", moodPoetry);
        return "default_page";
    }
}

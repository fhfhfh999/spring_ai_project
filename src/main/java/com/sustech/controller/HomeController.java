package com.sustech.controller;

import com.sustech.service.TimelyApiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Home")
public class HomeController {

    private final TimelyApiService timelyApiService;

    public HomeController(TimelyApiService timelyApiService) {
        this.timelyApiService = timelyApiService;
    }

    @GetMapping
    public String HomePage() {
        return "Home";
    }
}

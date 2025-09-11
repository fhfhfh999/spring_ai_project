package com.sustech.advice;

import com.sustech.service.TimelyApiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

//@ControllerAdvice(annotations = Controller.class)
//public class GlobalModelAdvice {
//    private final BackgroundImageService imageService;
//    private final QuoteService quoteService;
//
//    public GlobalModelAdvice(BackgroundImageService backgroundImageService, QuoteService quoteService) {
//        this.imageService = backgroundImageService;
//        this.quoteService = quoteService;
//    }
//
//    @ModelAttribute
//    public void addBackgroundImageAttributes(Model model) {
//        model.addAttribute("randomImageUrl", imageService.getBackgroundImageUrl());
//    }
//
//    @ModelAttribute
//    public void addQuoteAttributes(Model model) {
//        model.addAttribute("quote", quoteService.getAncientQuote());
//    }
//}

@ControllerAdvice(annotations = Controller.class)
public class GlobalModelAdvice {
    private final TimelyApiService timelyApiService;

    public GlobalModelAdvice(TimelyApiService timelyApiService) {
        this.timelyApiService = timelyApiService;
    }

    @ModelAttribute
    public void addBackgroundImageAttributes(Model model) {
        model.addAttribute("randomImageUrl", timelyApiService.getBackgroundImageUrl());
    }

    @ModelAttribute
    public void addQuoteAttributes(Model model) {
        model.addAttribute("quote", timelyApiService.getAncientQuote());
    }
}

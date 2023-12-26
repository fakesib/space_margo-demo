package com.fakesib.SpaceOfPsychologyAndSpeech.config.web;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("").setViewName("error");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/feedback").setViewName("feedback");
        registry.addViewController("/telegram").setViewName("telegram");
        registry.addViewController("/curse").setViewName("curse");
        registry.addViewController("/speech").setViewName("sections/speech");
        registry.addViewController("/psychology").setViewName("sections/psychology");
        registry.addViewController("/neuropsychology").setViewName("sections/neuro");
        registry.addViewController("/helptg").setViewName("helptelegram");
        registry.addViewController("/account/records").setViewName("account/records/records");
        registry.addViewController("/account/help").setViewName("account/help");
        registry.addViewController("/account").setViewName("account/interface");
    }
}
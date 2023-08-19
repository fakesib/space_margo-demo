package com.fakesib.SpaceOfPsychologyAndSpeech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class InterfaceController {
    @GetMapping("")
    public String interfacePage() {
        return "account/interface";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "account/profile";
    }

    @GetMapping("/records")
    public String recordsPage() {
        return "account/records/records";
    }
    
    @GetMapping("/settings")
    public String settingsPage() {
        return "account/settings";
    }

    @GetMapping("/help")
    public String logoutPage() {
        return "account/help";
    }
}

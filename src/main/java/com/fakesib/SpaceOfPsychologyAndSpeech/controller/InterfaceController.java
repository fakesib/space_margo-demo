package com.fakesib.SpaceOfPsychologyAndSpeech.controller;

import com.fakesib.SpaceOfPsychologyAndSpeech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class InterfaceController {
    @Autowired private UserRepository userRepository;


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

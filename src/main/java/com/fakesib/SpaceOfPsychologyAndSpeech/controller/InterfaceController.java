package com.fakesib.SpaceOfPsychologyAndSpeech.controller;

import com.fakesib.SpaceOfPsychologyAndSpeech.config.web.UserUserDetails;
import com.fakesib.SpaceOfPsychologyAndSpeech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class InterfaceController {
    @Autowired private UserService userService;

    @GetMapping("")
    public String interfacePage() {
        return "account/interface";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Authentication authentication) {
        UserUserDetails userDetails = (UserUserDetails) authentication.getPrincipal();
        model.addAttribute("email", userDetails.getUsername());
        model.addAttribute("password", userDetails.getPassword());
        return "account/profile/profile.html";
    }

    @PostMapping("/profile")
    public String profileEdit(){
        return "redirect:/account";
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
    public String helpPage() {
        return "account/help";
    }
}

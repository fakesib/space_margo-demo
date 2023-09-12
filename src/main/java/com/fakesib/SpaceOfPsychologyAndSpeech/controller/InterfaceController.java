package com.fakesib.SpaceOfPsychologyAndSpeech.controller;

import com.fakesib.SpaceOfPsychologyAndSpeech.config.UserUserDetails;
import com.fakesib.SpaceOfPsychologyAndSpeech.model.User;
import com.fakesib.SpaceOfPsychologyAndSpeech.repository.UserRepository;
import com.fakesib.SpaceOfPsychologyAndSpeech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

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
    public String profileEdit(Authentication authentication,
                              @RequestParam("email") String email,
                              @RequestParam("password") String password,
                              @RequestParam("name") String name,
                              @RequestParam("surname") String surname){
        UserUserDetails userDetails = (UserUserDetails) authentication.getPrincipal();
        userService.editUser(userDetails, email, password, name, surname);
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

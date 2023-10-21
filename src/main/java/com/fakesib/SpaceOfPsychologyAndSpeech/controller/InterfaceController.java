package com.fakesib.SpaceOfPsychologyAndSpeech.controller;

import com.fakesib.SpaceOfPsychologyAndSpeech.config.web.UserUserDetails;
import com.fakesib.SpaceOfPsychologyAndSpeech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class InterfaceController {

    @Autowired private UserService userService;

    @GetMapping("/profile")
    public String getProfile(Model model, Authentication authentication) {
        UserUserDetails userDetails = (UserUserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        model.addAttribute("email", username);
        model.addAttribute("password", userDetails.getPassword());
        model.addAttribute("childName", "null");
        model.addAttribute("childSurname", "null");
        model.addAttribute("childAge", "null");
        model.addAttribute("name", userService.getName(username));
        model.addAttribute("surname", userService.getSurname(username));
        return "account/profile/profile.html";
    }

//    @GetMapping("/profile/edit/")

}

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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class InterfaceController {

    @Autowired private UserService userService;

    private String changeModel;

    public String getChangeModel() {
        return changeModel;
    }

    public void setChangeModel(String changeModel) {
        this.changeModel = changeModel;
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Authentication authentication) {
        UserUserDetails userDetails = (UserUserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        model.addAttribute("email", username);
        model.addAttribute("password", userDetails.getPassword());
        model.addAttribute("childName", "null");
        model.addAttribute("childAge", "null");
        model.addAttribute("name", userService.getName(username));
        model.addAttribute("surname", userService.getSurname(username));
        return "account/profile/profile.html";
    }

    @PostMapping("/profile")
    public String postProfile(@RequestParam("changeButton") String button){
        setChangeModel(button);
        return null;
    }

    @GetMapping("/profile/edit")
    public String getEditProfilePage(){
        return "error.html";
    }

}

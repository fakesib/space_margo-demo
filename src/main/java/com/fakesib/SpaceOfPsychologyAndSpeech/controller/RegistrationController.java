package com.fakesib.SpaceOfPsychologyAndSpeech.controller;

import com.fakesib.SpaceOfPsychologyAndSpeech.model.User;
import com.fakesib.SpaceOfPsychologyAndSpeech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String username,
                               @RequestParam String password){

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles("USER");
        userRepository.save(user);
        return "redirect:/login";
    }

}

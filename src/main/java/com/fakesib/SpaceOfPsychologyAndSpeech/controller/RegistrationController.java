package com.fakesib.SpaceOfPsychologyAndSpeech.controller;

import com.fakesib.SpaceOfPsychologyAndSpeech.model.User;
import com.fakesib.SpaceOfPsychologyAndSpeech.repository.UserRepository;
import com.fakesib.SpaceOfPsychologyAndSpeech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired private UserService userService;

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String username,
                               @RequestParam String password, Model model){

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles("USER");
        if (userService.addUser(user) == false){
            model.addAttribute("exist", "Такой e-mail уже используется");
            return "redirect:/registration";
        }else{
            return "redirect:/login";
        }
    }

    @GetMapping("/activation/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        if(isActivated){
            model.addAttribute("message", "User activated");
        } else {
            model.addAttribute("message", "User not activated");
        }
        return "activation";
    }

}

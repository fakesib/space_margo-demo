package com.fakesib.SpaceOfPsychologyAndSpeech.service;

import com.fakesib.SpaceOfPsychologyAndSpeech.config.UserUserDetails;
import com.fakesib.SpaceOfPsychologyAndSpeech.model.User;
import com.fakesib.SpaceOfPsychologyAndSpeech.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.swing.text.html.HTML;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    @Autowired
    private MailSender mailSender;
    @Autowired
    private UserRepository userRepository;

    public boolean addUser(User user){
        user.setPassword(user.getPassword());
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if(userFromDb != null) {
            return false;
        } else {}
        user.setActivationCode(UUID.randomUUID().toString());

        String message = String.format("Привет! \n" + "Добро пожаловать на сайт ПространствоМаргариты.рф, пожалуйста для дальнейшей " +
                        "регистрации подтвердите e-mail по ссылке: \n" +
                        "\"http://localhost:8080/activation/%s\"",
                user.getActivationCode());
        mailSender.send(user.getUsername(), "Подтвердите электронную почту", message);
        userRepository.save(user);
        return true;
    }

    public boolean activateUser(String code) {
        User user =  userRepository.findByActivationCode(code);
        if (user == null){
            return false;
        }
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }
}
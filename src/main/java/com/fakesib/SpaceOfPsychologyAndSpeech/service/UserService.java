package com.fakesib.SpaceOfPsychologyAndSpeech.service;

import com.fakesib.SpaceOfPsychologyAndSpeech.model.User;
import com.fakesib.SpaceOfPsychologyAndSpeech.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

        String s = "Если возникнут какие-то " + "<a href=\"http://localhost:8080/accont/help\">вопросы</a>";
        String message = String.format("Привет! <br> </br><br> </br>" + "Добро пожаловать на сайт ПространствоМаргариты.рф, пожалуйста для дальнейшей " +
                        "регистрации подтвердите e-mail по ссылке: \n" +
                        "<a href=\"http://localhost:8080/activation/%s\">тык</a>" + "<br> </br><br> </br>ПространствоМаргариты.рф<br> </br>" + s,
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
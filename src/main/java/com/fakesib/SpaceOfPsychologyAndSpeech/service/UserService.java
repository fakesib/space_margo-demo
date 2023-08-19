package com.fakesib.SpaceOfPsychologyAndSpeech.service;

import com.fakesib.SpaceOfPsychologyAndSpeech.model.User;
import com.fakesib.SpaceOfPsychologyAndSpeech.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public String addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User added to system";
    }
}
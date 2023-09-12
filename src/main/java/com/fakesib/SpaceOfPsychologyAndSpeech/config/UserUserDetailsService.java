package com.fakesib.SpaceOfPsychologyAndSpeech.config;


import com.fakesib.SpaceOfPsychologyAndSpeech.model.User;
import com.fakesib.SpaceOfPsychologyAndSpeech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;



@Component
public class UserUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, AuthenticationException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        User userObj = user.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Проверка пустого значения поля authenticationCode
        if (!StringUtils.isEmpty(userObj.getActivationCode())) {
            throw new UsernameNotFoundException("user not activeted");
        }
        return user.map(UserUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found" + username));
    }
}
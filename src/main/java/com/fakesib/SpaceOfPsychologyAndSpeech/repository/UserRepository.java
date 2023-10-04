package com.fakesib.SpaceOfPsychologyAndSpeech.repository;

import com.fakesib.SpaceOfPsychologyAndSpeech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    List<User> findAll();
    User findByActivationCode(String code);

    User findActivationCodeByUsername(String username);

    @Query(value = "UPDATE users SET records = ?1 WHERE username = ?2", nativeQuery = true)
    String updateRecordsByUsername(String message, String username);

    @Query(value = "SELECT records FROM users WHERE username = ?1", nativeQuery = true)
    String findRecordsByUsername(String username);


    long findTelegramIdByUsername(String email);
}

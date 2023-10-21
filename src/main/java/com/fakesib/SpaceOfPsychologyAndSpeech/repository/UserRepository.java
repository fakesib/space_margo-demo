package com.fakesib.SpaceOfPsychologyAndSpeech.repository;

import com.fakesib.SpaceOfPsychologyAndSpeech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    List<User> findAll();

    User findByActivationCode(String code);

    User findActivationCodeByUsername(String username);

    @Query(value = "SELECT name FROM users WHERE username=?1", nativeQuery = true)
    String findNameByUsername(String username);

    @Query(value = "SELECT surname FROM users WHERE username=?1", nativeQuery = true)
    String findSurnameByUsername(String username);

    @Modifying
    @Query(value = "UPDATE users SET name = ?1 WHERE username = ?2", nativeQuery = true)
    void updateNameByUsername(String name, String username);

    @Modifying
    @Query(value = "UPDATE users SET surname = ?1 WHERE username = ?2", nativeQuery = true)
    void updateSurnameByUsername(String surname, String username);
}

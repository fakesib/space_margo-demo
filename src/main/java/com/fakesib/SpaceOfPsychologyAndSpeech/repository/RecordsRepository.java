package com.fakesib.SpaceOfPsychologyAndSpeech.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.fakesib.SpaceOfPsychologyAndSpeech.model.Record;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RecordsRepository extends JpaRepository<Record, Integer>{
    List<Record> findAll();

    @Query(value = "SELECT date FROM records", nativeQuery = true)
    List<String> getDatesList();
    
    @Query(value = "SELECT CASE WHEN time_1 IS NULL THEN 'time_1' END AS empty_column FROM records WHERE date = ?1", nativeQuery = true)
    String time_1(String date);

    @Query(value = "SELECT CASE WHEN time_2 IS NULL THEN 'time_2' END AS NULL FROM records WHERE date = ?1", nativeQuery = true)
    String time_2(String date);

    @Query(value = "SELECT CASE WHEN time_3 IS NULL THEN 'time_3' END AS NULL FROM records WHERE date = ?1", nativeQuery = true)
    String time_3(String date);

    @Query(value = "SELECT CASE WHEN time_4 IS NULL THEN 'time_4' END AS NULL FROM records WHERE date = ?1", nativeQuery = true)
    String time_4(String date);

    @Modifying
    @Query(value = "UPDATE records SET time_1 = ?1 where date = ?2", nativeQuery = true)
    void insertRecordTime1(String message, String date);

    @Modifying
    @Query(value = "UPDATE records SET time_2 = ?1 where date = ?2", nativeQuery = true)
    void insertRecordTime2(String message, String date);

    @Modifying
    @Query(value = "UPDATE records SET time_3 = ?1 where date = ?2", nativeQuery = true)
    void insertRecordTime3(String message, String date);

    @Modifying
    @Query(value = "UPDATE records SET time_4 = ?1 where date = ?2", nativeQuery = true)
    void insertRecordTime4(String message, String date);

    @Query(value = "SELECT date FROM records WHERE id = ?1 AND time_1 = ?2", nativeQuery = true)
    String findDateByIdAndTime_1(int id, String user);

    @Query(value = "SELECT date FROM records WHERE id = ?1 AND time_2 = ?2", nativeQuery = true)
    String findDateByIdAndTime_2(int id, String user);

    @Query(value = "SELECT date FROM records WHERE id = ?1 AND time_3 = ?2", nativeQuery = true)
    String findDateByIdAndTime_3(int id, String user);

    @Query(value = "SELECT date FROM records WHERE id = ?1 AND time_4 = ?2", nativeQuery = true)
    String findDateByIdAndTime_4(int id, String user);

}
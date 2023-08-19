package com.fakesib.SpaceOfPsychologyAndSpeech.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.fakesib.SpaceOfPsychologyAndSpeech.model.Record;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Transactional(readOnly = true)
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

}

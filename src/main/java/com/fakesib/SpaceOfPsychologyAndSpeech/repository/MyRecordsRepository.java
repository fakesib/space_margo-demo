package com.fakesib.SpaceOfPsychologyAndSpeech.repository;

import com.fakesib.SpaceOfPsychologyAndSpeech.model.MyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyRecordsRepository extends JpaRepository<MyRecord, Integer> {

    @Modifying
    @Query(value = "INSERT INTO my_records(username, date, time, format) values(?1, ?2, ?3, ?4)", nativeQuery = true)
    void addNewRecord(String username, String date, String time, String format);

    @Query(value = "SELECT date FROM my_records WHERE username=?1", nativeQuery = true)
    List<String> findDateByUsername(String username);

    @Query(value = "SELECT time FROM my_records WHERE username=?1", nativeQuery = true)
    List<String> findTimeByUsername(String username);

    List<MyRecord> findByUsername(String username);

    List<MyRecord> findAll();
}

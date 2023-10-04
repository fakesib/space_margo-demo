package com.fakesib.SpaceOfPsychologyAndSpeech.service;

import com.fakesib.SpaceOfPsychologyAndSpeech.repository.MyRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MyRecordService {
    @Autowired
    private MyRecordsRepository myRecordsRepository;

    public void addNewRecord(String username, String date, String time){
        myRecordsRepository.addNewRecord(username, date, time);
    }

}

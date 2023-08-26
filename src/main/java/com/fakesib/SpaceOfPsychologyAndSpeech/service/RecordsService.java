package com.fakesib.SpaceOfPsychologyAndSpeech.service;

import com.fakesib.SpaceOfPsychologyAndSpeech.repository.RecordsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RecordsService {
    @Autowired private RecordsRepository recordsRepository;

    public List<String> timeList(String date){
        List<String> timesList = new ArrayList<String>();
        timesList.add("11:30");
        timesList.add("13:00");
        timesList.add("15:00");
        timesList.add("20:00");
        List<String> readyList = new ArrayList<String>();
        List<String> timesFromDBList = new ArrayList<String>();
        timesFromDBList.add(recordsRepository.time_1(date));
        timesFromDBList.add(recordsRepository.time_2(date));
        timesFromDBList.add(recordsRepository.time_3(date));
        timesFromDBList.add(recordsRepository.time_4(date));
        for (int i = 0; i < timesFromDBList.size(); i++) {
            if (timesFromDBList.get(i) == null) {
                System.out.println("null");
            }else{
                readyList.add(timesList.get(i));
            }
        }
        return readyList;
    }
}

package com.fakesib.SpaceOfPsychologyAndSpeech.controller;

import java.util.ArrayList;
import java.util.List;

import com.fakesib.SpaceOfPsychologyAndSpeech.service.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fakesib.SpaceOfPsychologyAndSpeech.repository.RecordsRepository;

@Controller
@RequestMapping("/account/records")
public class RecordsController {
    @Autowired private RecordsRepository recordsRepository;

    @Autowired private RecordsService recordsService;

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    // две кнопки - 1. Записаться 2. Мои Записи

    @GetMapping("/date")
    public String getDatePage(Model model) {
        model.addAttribute("dates", recordsRepository.getDatesList());
        return "account/records/date";
    }

    @PostMapping("/date")
    public String getDatePagePost(@RequestParam("dateButton") String date){
        setDate(date);
        return "redirect:/account/records/date/time";
    }

    @GetMapping("/date/time")
    public String getTimePage(Model model) {
        model.addAttribute("readyList", recordsService.timeList(getDate()));
        return "account/records/time";
    }

    @PostMapping("/date/time")
    public String getTime(@RequestParam("timeButton") String time){
        System.out.println(getDate() + " " + time);
        
        return "redirect:/main";
    }
}

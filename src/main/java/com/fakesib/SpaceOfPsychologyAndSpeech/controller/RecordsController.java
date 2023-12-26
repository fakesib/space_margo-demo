package com.fakesib.SpaceOfPsychologyAndSpeech.controller;

import java.util.ArrayList;
import java.util.List;

import com.fakesib.SpaceOfPsychologyAndSpeech.config.web.UserUserDetails;
import com.fakesib.SpaceOfPsychologyAndSpeech.model.User;
import com.fakesib.SpaceOfPsychologyAndSpeech.repository.MyRecordsRepository;
import com.fakesib.SpaceOfPsychologyAndSpeech.repository.UserRepository;
import com.fakesib.SpaceOfPsychologyAndSpeech.service.MyRecordService;
import com.fakesib.SpaceOfPsychologyAndSpeech.service.RecordsService;
import com.fakesib.SpaceOfPsychologyAndSpeech.service.UserService;

import jakarta.inject.Scope;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.util.Pair;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fakesib.SpaceOfPsychologyAndSpeech.repository.RecordsRepository;

@Controller
@RequestMapping("/account/records")
@EnableTransactionManagement
public class RecordsController {
    @Autowired private RecordsRepository recordsRepository;

    @Autowired private RecordsService recordsService;

    @Autowired private MyRecordsRepository myRecordsRepository;

    private String date;
    private String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @GetMapping("/format")
    public String getFormatPage(Model model){
        List<String> formatList = new ArrayList<>();
        formatList.add("Онлайн💻");
        formatList.add("Оффлайн🚶");   
        model.addAttribute("formats", formatList);
        return "account/records/format";
    }

    @PostMapping("/format")
    public String setFormatPage(@RequestParam("formatButton") String button){

        if (button.equals("Оффлайн🚶")){
            setFormat("Оффлайн🚶");
        }else {
            setFormat("Онлайн💻");
        }
        return "redirect:/account/records/date";
    }

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
    public String getTime(@RequestParam("timeButton") String time,
                          Authentication authentication) {

        UserUserDetails userDetails = (UserUserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        recordsService.updateRecordAndNotification(time, email, getDate(), getFormat());
        return "redirect:/account/records/date/time/submit";
    }

    @GetMapping("/date/time/submit")
    public String getSubmit(){
        return "account/records/submit";
    }

    @GetMapping("/my")
    public String getMyRecords(Model model, Authentication authentication){
        UserUserDetails userDetails = (UserUserDetails) authentication.getPrincipal();
        model.addAttribute("recordList", myRecordsRepository.findByUsername(userDetails.getUsername()));
        return "account/records/myrecords";
    }
}

package com.fakesib.SpaceOfPsychologyAndSpeech.service;

import com.fakesib.SpaceOfPsychologyAndSpeech.repository.MyRecordsRepository;
import com.fakesib.SpaceOfPsychologyAndSpeech.repository.RecordsRepository;
import com.fakesib.SpaceOfPsychologyAndSpeech.repository.UserRepository;
import jakarta.mail.internet.MimeMessage;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RecordsService {

    @Autowired
    private RecordsRepository recordsRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private MyRecordsRepository myRecordsRepository;


    public List<String> timeList(String date) {
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
            } else {
                readyList.add(timesList.get(i));
            }
        }
        return readyList;
    }

    public void updateRecordAndNotification(String time, String email, String date, String format) {
        TelegramBot bot = new TelegramBot();

        // Notification Admin Telegram
        long adminId = 1033942837;
        String messageToSendToAdmin = "Новая запись:" + "\n" + date + " время: " + time
                + "\n" + "Электронная почта пользователя: " + email
                + "\n" + "Формат консультации: " + format;
        bot.sendMessage(adminId, messageToSendToAdmin);

        // Send mail message
        String s = "Если возникнут какие-то " + "<a href=\"http://fakesib.site/account/help\">вопросы</a>";

        if(format.equals("Онлайн💻")) {
            String emailMessage = "Ваша запись была успешно добавлена <br> </br><br> </br>" + "\n" + "Дата консультации: " + date + " время: " + time
                    + "<br> </br>За час до занятия вам на почту (и телеграмм если записан) придёт адрес для проведения консультации. <br>" +
                    "Сделано это для личной безопасности, пока вам известна станция метро: Физтех🟢"
                    + "<br> </br><br> </br>ПространствоМаргариты.рф<br> </br>" + s;
            mailSender.send(email, "Вы успешно записались!", emailMessage);
        } else {
            String emailMessage = "Ваша запись была успешно добавлена <br> </br><br> </br>" + "\n" + "Дата консультации: " + date + " время: " + time
                    + "<br>За час до занятия вам на почту (и телеграмм если подключен) придёт ссылка для подключения в zoom"
                    + "<br> </br><br> </br>ПространствоМаргариты.рф<br> </br>" + s;
            mailSender.send(email, "Вы успешно записались!", emailMessage);
        }

        // adding myrecords
        myRecordsRepository.addNewRecord(email, date, time, format);
        // Updating record
        if (time.equals("11:30")) {
            recordsRepository.insertRecordTime1(email, date);
        }
        else if (time.equals("13:00")) {
            recordsRepository.insertRecordTime2(email, date);
        }
        else if (time.equals("15:00")) {
            recordsRepository.insertRecordTime3(email, date);
        }
        else if (time.equals("20:00")) {
            recordsRepository.insertRecordTime4(email, date);
        }
    }
}

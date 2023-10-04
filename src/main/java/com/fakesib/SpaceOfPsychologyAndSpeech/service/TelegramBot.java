package com.fakesib.SpaceOfPsychologyAndSpeech.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotToken() {
        return "6366185651:AAGPT7xyRZBEqVBWHrY5irzGaTD8b0AGcZI";
    }

    @Override
    public String getBotUsername() {
        return "spacemargo_bot";
    }

    @Override
    public void onUpdateReceived(Update update) {
    }

    public void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }
}

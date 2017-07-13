package com.alekseysavin.voda;

import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;

/**
 * Created by User on 11.07.2017.
 */
public class voda extends TelegramLongPollingBot {
    public voda() {
        super();
    }

    public String getBotToken() {
        return "380086304:AAGS86JUVGuHNlY43IIYiitGC6ascrHhoyg";
    }

    public String getBotUsername() {
        return "voda_bot";
    }

    public voda(DefaultBotOptions options) {
        super(options);
    }

    public void onUpdateReceived(Update update) {

        ArrayList<String> messages = new ArrayList<String>();
        if (update.hasMessage() && update.getMessage().hasText()) {
            messages.add(update.getMessage().getText());
        }

        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                long ChatId = update.getMessage().getChatId(); //ChatId
                String CurrentInMessage = update.getMessage().getText(); // CurrentInMessage
                SendMessage message = new SendMessage();
                message.setChatId(ChatId);
  
                if (CurrentInMessage.equals("/status")) {
                    message.setChatId(ChatId);
                    message.setText("Ok, is is status");
                    sendMessage(message);
                } else
                    for (String str : messages) {
                        message.setText(str);
                        sendMessage(message);
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

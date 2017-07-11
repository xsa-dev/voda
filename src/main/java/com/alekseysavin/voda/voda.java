package com.alekseysavin.voda;

import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by User on 11.07.2017.
 */
public class voda extends TelegramLongPollingBot {
    public voda() {
        super();
    }

    public String getBotToken() {
        return "380086304:AAFlk69S3b1wNSyVHvrIPBCVfLI8zzhaaLg";
    }

    public void onUpdateReceived(Update update) {

        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                SendMessage message = new SendMessage();
                message.setChatId((update.getMessage().getChatId())).setText("hello, Alex, i am voda; popej plz");
                sendMessage(message);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }

    public String getBotUsername() {
        return "voda_bot";
    }

    public voda(DefaultBotOptions options) {
        super(options);
    }
}

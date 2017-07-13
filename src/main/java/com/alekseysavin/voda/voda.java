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
        return "380086304:AAFlk69S3b1wNSyVHvrIPBCVfLI8zzhaaLg";
    }


    public void onUpdateReceived(Update update) {

        ArrayList<String> messages = new ArrayList<String>();
        if (update.hasMessage() && update.getMessage().hasText()) {
            messages.add(update.getMessage().getText());
        }

        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                long id = update.getMessage().getChatId(); // id - идентификатор чата (обязательное)
                String instr = update.getMessage().getText(); // instr - входящая строка
                SendMessage message = new SendMessage(); // message - исходящая инструкция
                StringBuilder status = new StringBuilder(); // status - отдаём статус
                String[] arr = (update.getMessage().getText().split(":")); // список item'ов
                String items;
                message.setChatId(id);

                if (instr.equals("status")) { // отправка статуса
                    message.setChatId(id);
                    ArrayList<String> list = new ArrayList<String>();
                    for (String str: list ) {
                        status.append(str + "\n");
                    }
                    sendMessage(message.setText(status.toString())); // набираем в статус
                } else if (instr.contains("add")) {
                    for (String str: arr ) {

                    }
                }

            }
        } catch (Exception e) {
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

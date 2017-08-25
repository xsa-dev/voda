package com.alekseysavin.voda;


import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TimerTask;

/**
 * Created by User on 11.07.2017.
 * Updated by User on 19.07.2017.
 * Updated by iko in 25.08.2017
 */
public class voda extends TelegramLongPollingBot {

    public class zamer {

    }

    int bSum = 0; // белок
    int kSum = 0; // калории
    int pSum = 0; // клетчатка
    int vSum = 0; // вода
    int aSum = 0; // активность

    String[] vStringArray;
    String[] eStringArray;
    String[] aStringArray;


    public void onUpdateReceived(Update update) {


        long ChatId = 0;



        try {

            if (update.hasMessage() && update.getMessage().hasText()) {

                ChatId = update.getMessage().getChatId(); //ChatId
                String CurrentInMessage = update.getMessage().getText(); // CurrentInMessage
                SendMessage message = new SendMessage();
                message.setChatId(ChatId);

                if (CurrentInMessage.equals("/status")) {

                    message.setChatId(ChatId);

                    String outMessage = "Дневник: " + "\n";

                    message.setText(outMessage + "\n"
                            + "Белка за день: " + bSum + "\n"
                            + "Калории: " + kSum + "\n"
                            + "П.В.: " + pSum + "\n"
                            + "Воды: " + vSum + "\n"
                            + "Активность: " + aSum + "\n"
                    );

                    sendMessage(message);

                } else if (CurrentInMessage.startsWith("*")) {

                    message.setChatId(ChatId);
                    System.out.println(update.getMessage().getText());
                    vStringArray = update.getMessage().getText().split(" ");
                    vSum += Integer.parseInt(vStringArray[1]); // добавляем воду

                } else if (CurrentInMessage.startsWith("+")) {

                    message.setChatId(ChatId);
                    eStringArray = update.getMessage().getText().split(" ");

                    for (int i = 2; i < eStringArray.length; i++) {
                        if (eStringArray[i].endsWith("б")) {
                            String string = eStringArray[i].toString();
                            bSum = Integer.parseInt(string.replace("б", ""));
                        }
                        if (eStringArray[i].contains("к")) {
                            String string = eStringArray[i].toString();
                            kSum += Integer.parseInt(string.replace("к", ""));
                        }
                        if (eStringArray[i].endsWith("п")) {
                            String string = eStringArray[i].toString();
                            pSum = Integer.parseInt(string.replace("п", ""));
                        }
                    }
                } else if (CurrentInMessage.startsWith("@")) {

                    message.setChatId(ChatId);
                    System.out.println(update.getMessage().getText());
                    aStringArray = update.getMessage().getText().split(" ");
                    aSum += Integer.parseInt(aStringArray[1]); // добавляем воду
                }
            }
        } catch (Exception e) {

            SendMessage message = new SendMessage();
            message.setChatId(ChatId);
            message.setText(e.toString());
            e.printStackTrace();
        }
    }

    public voda() {
        super();
    }

    public String getBotToken() {
        return "380086304:AAFf6KjuyStoDG2UavmeuoX6TZjClEZsCqo";
    }

    public String getBotUsername() {
        return "voda_bot";
    }

    public voda(DefaultBotOptions options) {
        super(options);
    }
}

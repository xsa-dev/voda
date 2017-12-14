package com;

import com.draft.fitlife24;
import com.draft.voda;
import com.timer.TicTackApp;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by User on 11.07.2017.
 */

public class AppMainClass {

    public static void main(String[] args) {

        // todo multilanguage support


        //
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Запускаем тестовый планировщик
        TicTackApp shedulerApp = new TicTackApp();
        shedulerApp.createShedulledTimer(1000, 6000000);

        // todo Запускаем опросник по перезамерам


        try {
            botsApi.registerBot(new voda());
            botsApi.registerBot(new fitlife24());
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }



    }


}

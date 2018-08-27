package com;

import com.Controller.Sheduler.TicTackApp;

import com.Controller.RezultDiary.voda;
import com.Controller.xermes.xermes;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by User on 11.07.2017.
 */

public class AppMainClass {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        TicTackApp shedulerApp = new TicTackApp();

        shedulerApp.createEveryDaySheduleForWaterDiary();
        shedulerApp.createEveryDaySheduleForUsersWaterDiary();
        shedulerApp.statisticaEveryDaySheduleForAndser();

        try {
            botsApi.registerBot(new voda());
            botsApi.registerBot(new xermes());
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

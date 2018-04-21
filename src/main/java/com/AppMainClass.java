package com;

import com.Controller.Sheduler.TicTackApp;
import com.Controller.Plank.plank30days;
import com.Controller.Popejbot.popejbot;
import com.Controller.RezultDiary.voda;
import com.Controller.Statistica.statistica;
import com.Controller.fitlife24.fitlife24;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by User on 11.07.2017.
 */

public class AppMainClass {

    public static void main(String[] args) {


        // derby java db implementation region

        // todo multilanguage support android strings kotlyn :D

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Запускаем тестовый планировщик
        TicTackApp shedulerApp = new TicTackApp();
        // shedulerApp.createShedulledTimer(1000, 3600000);
        shedulerApp.createEveryDaySheduleForWaterDiary();
        shedulerApp.createEveryDaySheduleForUsersWaterDiary();
        shedulerApp.plankEveryDaySheduleForAnswer();
        shedulerApp.statisticaEveryDaySheduleForAndser();

        // todo Запускаем опросник по перезамерам
        try {
            botsApi.registerBot(new voda());
            botsApi.registerBot(new fitlife24());
            botsApi.registerBot(new plank30days());
            botsApi.registerBot(new popejbot());
            botsApi.registerBot(new statistica());
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

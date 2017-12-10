package com;

import com.draft.voda;
import com.model.dbmodel    ;
import com.timer.RunMeShedule;
import com.timer.TicTackApp;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.SQLException;

/**
 * Created by User on 11.07.2017.
 */

public class AppMainClass {

    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();




//        RunMeShedule shedule = new RunMeShedule();
//        shedule.run();

        TicTackApp shedulerApp = new TicTackApp();
        shedulerApp.createTestSendSheduleTask();

        try {
            botsApi.registerBot(new voda());

        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}

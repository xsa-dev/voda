package com.alekseysavin.voda;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import static com.alekseysavin.voda.dbmodel.*;

/**
 * Created by User on 11.07.2017.
 */

public class main {
    public static void main(String[] args) {
        // MysqlCon conn = new MysqlCon();
        // conn.main(null);
        // System.out.println(conn.toString());


        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new voda());
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}

package com.alekseysavin.voda;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.SQLException;

import static com.alekseysavin.voda.dbmodel.*;

/**
 * Created by User on 11.07.2017.
 */
public class main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // DB MODEL INIT
        MysqlCon conn = new MysqlCon();
        conn.testConnection();
        System.out.println(conn.toString());

        // BOT API INIT
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

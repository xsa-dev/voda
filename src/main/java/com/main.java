package com;

import com.draft.voda;
import com.model.dbmodel    ;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.SQLException;

/**
 * Created by User on 11.07.2017.
 */

public class main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        dbmodel.MysqlCon conn = new dbmodel.MysqlCon();

        conn.testMsqlConnection();
        conn.addProduct("testo", 1,1,1);

        // System.out.println(conn.toString());

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

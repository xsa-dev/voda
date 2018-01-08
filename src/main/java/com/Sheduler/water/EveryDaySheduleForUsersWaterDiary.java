package com.sheduler.water;

import com.Model.dbmodel;
import com.draft.RezultDiary.voda;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by xsd on 21.07.2017 with love.
 * :)
 *
 * @author xsd
 * @author alekseysavin.com
 * @version 0.1
 */
public class EveryDaySheduleForUsersWaterDiary extends TimerTask {
    @Override
    public void run() {

        //send message about eating and sleeping

        try {
            LocalDateTime now = LocalDateTime.now();
            voda bot = new voda();

            List<Integer> notifyTime = Arrays.asList(7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
            int currentHour = now.getHour();

            ArrayList<Integer> consulted = dbmodel.MysqlCon.getFollowers();

            for (Integer ntime : notifyTime) {
                if (ntime == currentHour) {
                    for (Integer consultedUser : consulted) {
                        bot.sendTextToIdMessage(consultedUser, dbmodel.MysqlCon.getEveryDayWaterUserWaterCountView(consultedUser));
                    }
                }
            }


        } catch (Exception e) {
            System.out.println();
        }
    }
}
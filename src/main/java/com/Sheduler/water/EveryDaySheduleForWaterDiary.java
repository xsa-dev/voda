package com.Sheduler.water;

import com.Model.dbmodel;
import com.draft.RezultDiary.voda;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Created by xsd on 21.07.2017 with love.
 * :)
 *
 * @author xsd
 * @author alekseysavin.com
 * @version 0.1
 */
public class EveryDaySheduleForWaterDiary extends TimerTask {
    @Override
    public void run() {

        //send message about eating and sleeping

        try {
            LocalDateTime now = LocalDateTime.now();
            int timeOfShedule = 23;
            int currentHour = now.getHour();
            voda bot = new voda();

            if (currentHour == timeOfShedule) {
                System.out.println("CurrentHour: " + timeOfShedule + " : " + currentHour);

                ArrayList<Integer> consultants = dbmodel.MysqlCon.getConsultans();

                for (Integer tid : consultants) {
                    bot.sendTextToIdMessage(voda.getOwnerId(), "@@@messageTo: " + tid + " ready:" + "\n" +
                            // todo this place for get list of water to consultant name
                            dbmodel.MysqlCon.getEveryDayWaterDiaryReportView(tid) + "\n");
                }

                bot.sendTextToIdMessage(voda.getOwnerId(), "Это оповещение настроено на " + timeOfShedule);
            }
        } catch (Exception e) {
            System.out.println();
        }
    }
}
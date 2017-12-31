package com.Sheduler.statistica;

import com.draft.Statistica.statistica;

import java.time.LocalDateTime;
import java.util.TimerTask;

/**
 * Created by xsd on 21.07.2017 with love.
 * :)
 *
 * @author xsd
 * @author alekseysavin.com
 * @version 0.1
 */
public class StatisticaShedule extends TimerTask {
    @Override
    public void run() {

        //send message about eating and sleeping

        try {
            LocalDateTime now = LocalDateTime.now();
            int timeOfShedule = 15;
            int currentHour = now.getHour();
            statistica bot = new statistica();
            int[] notifyHours = {7, 9, 11, 13, 15, 17, 19, 21, 23};

            for (int nH : notifyHours) {
                if (currentHour == nH) {
                    bot.sendMessageToThisGroupId();
                } else {
                }
                bot.sendTextToIdMessage(bot.getOwnerId(), "Это оповещение настроено на " + timeOfShedule + ", а сейчас " + currentHour);
                // bot.sendTextToIdMessage(105600955, "Это оповещение настроено на " + timeOfShedule);
            }

        } catch (Exception e) {
            System.out.println();
        }
    }
}
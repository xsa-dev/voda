package com.sheduler.plank30days;

import com.draft.Plank.plank30days;

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
public class PlankShedule extends TimerTask {
    @Override
    public void run() {

        //send message about eating and sleeping

        try {
            LocalDateTime now = LocalDateTime.now();
            int timeOfShedule = 13;
            int currentHour = now.getHour();
            plank30days bot = new plank30days();

            if (currentHour == timeOfShedule) {
                bot.sendMessageToPlankGroupId();
            } else {
            }
            bot.sendTextToIdMessage(plank30days.getOwnerId(), "Это оповещение настроено на " + timeOfShedule);
            // bot.sendTextToIdMessage(105600955, "Это оповещение настроено на " + timeOfShedule);

        } catch (Exception e) {
            System.out.println();
        }
    }
}
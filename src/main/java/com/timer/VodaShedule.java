package com.timer;

import com.draft.voda;
import java.util.ArrayList;
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
public class VodaShedule extends TimerTask {
    @Override
    public void run() {
        voda bot = new voda();

        // todo send message about worked sheduler
        List<Integer> subscribers = new ArrayList<Integer>();

        // todo query for subscribers for water notification
        try {
            for (Integer subscriber : subscribers) {
                // todo get time setting (UTC,
                bot.sendTextToIdMessage(subscriber, "Voda sheduled message");
                System.out.println("Voda Message Send for " + subscriber);
            }
         } catch (Exception e) {
            System.out.println();
        }
    }
}

package com.sheduler.test;

import com.draft.RezultDiary.voda;

import java.util.TimerTask;

/**
 * Created by xsd on 21.07.2017 with love.
 * :)
 *
 * @author xsd
 * @author alekseysavin.com
 * @version 0.1
 */
public class RunMeShedule extends TimerTask {
    @Override
    public void run() {
        //send message about eating and sleeping
        try {
            voda bot = new voda();
            bot.sendTextToIdMessage(1, "");
            System.out.println("sendMessageTest");
        } catch (Exception e) {
            System.out.println();
        }

    }
}

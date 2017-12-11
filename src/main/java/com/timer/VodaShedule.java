package com.timer;

import com.draft.voda;

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
        //send message about voda dialy commited sheduler
        try {
            voda bot = new voda();
            bot.sendTextToIdMessage(voda.getOwnerId(), "Test sheduled message");
            System.out.println("sendMessageTest");
        } catch (Exception e) {
            System.out.println();
        }
    }
}

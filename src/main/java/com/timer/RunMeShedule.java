package com.timer;

import java.util.TimerTask;
import org.telegram.telegrambots.*;


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
        System.out.println("I am ~RunMe and me not free...");
    }
}

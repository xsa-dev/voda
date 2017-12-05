package com.timer;

import com.timer.RunMeShedule;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Link for this class: http://www.mkyong.com/java/jdk-timer-scheduler-example/
 *
 * JDK Timer is a simple scheduler for a specified task for repeated fixed-delay execution. To use this, you have to extends the TimerTask abstract class, override the run() method with your scheduler function.
 *
 *
 */

public class TicTackApp
{
    public static void main( String[] args )
    {
        TimerTask task = new RunMeShedule();
        Timer timer = new Timer();
        //In this example, the timer will print the “Run Me ~” message every 60 seconds, with a 1 second delay for the first time of execution.
        timer.schedule(task, 1000,60000);
    }
}
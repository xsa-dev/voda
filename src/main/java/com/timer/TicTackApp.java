package com.timer;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Link for this class: http://www.mkyong.com/java/jdk-timer-scheduler-example/
 *
 * JDK Timer is a simple scheduler for a specified task for repeated fixed-delay execution. To use this, you have to extends the TimerTask abstract class, override the run() method with your scheduler function.
 *
 * This is Tamer Tasker
 */

// todo refactor tictackapp -> Tasker
public class TicTackApp
{
    public static void main( String[] args )
    {
        TimerTask task = new RunMeShedule();
        Timer timer = new Timer();
        //In this example, the timer will print the “Run Me ~” message every 60 seconds, with a 1 second delay for the first time of execution.
        timer.schedule(task, 1000,60000);
    }

    public void createShedulledTimer(int delay, int period) {
        TimerTask task = new TestShedule();
        Timer timer = new Timer();
        //In this example, the timer will print the “Run Me ~” message every 60 seconds, with a 1 second delay for the first time of execution.
        timer.schedule(task, delay, period);
    }

    @Deprecated
    public void createShedulledTimerForWaterDiarySubscriber (Integer subscriber) {
        int delay = 1000;
        int period = 60000; // in mil

        TimerTask task = new TestShedule();
        Timer timer = new Timer();
        // Write get personal subscriber setting and start timer
        timer.schedule(task, delay, period);
    }

    public void createEveryDaySheduleForWaterDiary() {
        int delay = 1000;
        int period = 3600000; // every hour

        TimerTask task = new EveryDaySheduleForWaterDiary();
        Timer timer = new Timer();
        timer.schedule(task, delay, period);
    }
}
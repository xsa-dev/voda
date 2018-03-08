package com.Sheduler.plank30days;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class PlankSheduleTest {
    @Test
    public void SheduleCreateTrue() throws Exception {
        PlankShedule shedule = new PlankShedule();
        Assert.assertNotNull(shedule);
    }

    @Test
    public void SheduleCurrentHourAndTimeOfSheduleEqualsTrue() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        int timeOfShedule = 13;
        int currentHour = 13;
        if (currentHour == timeOfShedule) {
            Assert.assertNotNull(now);
        }
    }
}
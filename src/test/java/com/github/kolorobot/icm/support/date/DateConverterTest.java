package com.github.kolorobot.icm.support.date;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class DateConverterTest {

    @Test
    public void toDateStringAndToDateReturnsTheSameResult() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2014);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 10);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 14);
        calendar.set(Calendar.SECOND, 22);
        Date given = calendar.getTime();

        String dateString = DateConverter.toDateString(DateConverter.toDate(DateConverter.toDateString(given)));
        Assert.assertEquals("2014-01-10 23:14:22", dateString);
    }
}

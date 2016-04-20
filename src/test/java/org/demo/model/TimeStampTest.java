package org.demo.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by Robin_2 on 13/04/2016.
 */
public class TimeStampTest {

    @Test
    public void testToString() throws Exception {

    }

    @Test
    public void testGetId() throws Exception {
        //Given
        Calendar date = Calendar.getInstance();
        TimeStamp test = new TimeStamp(date, true, new RfidKey("1"));
        int given = 0;
        //when
        int got = test.getId();
        //then
        Assert.assertEquals(given,got);
    }

    @Test
    public void testSetId() throws Exception {
        //Given
        Calendar date = Calendar.getInstance();
        TimeStamp test = new TimeStamp(date, true, new RfidKey("1"));
        int given = 6;
        //when
        test.setId(given);
        int got = test.getId();
        //then
        Assert.assertEquals(given,got);
    }

    @Test
    public void testGetRfidkey() throws Exception {
        //Given
        Calendar date = Calendar.getInstance();
        TimeStamp test = new TimeStamp(date, true, new RfidKey("1"));
        RfidKey given = new RfidKey("1");
        //when
        RfidKey got = test.getRfidkey();
        //then
        Assert.assertEquals(given,got);
    }

    @Test
    public void testSetRfidkey() throws Exception {
        //Given
        Calendar date = Calendar.getInstance();
        TimeStamp test = new TimeStamp(date, true, new RfidKey("1"));
        RfidKey given = new RfidKey("6");
        //when
        test.setRfidkey(given);
        RfidKey got = test.getRfidkey();
        //then
        Assert.assertEquals(given,got);
    }

    @Test
    public void testGetDate() throws Exception {
        //Given
        Calendar date = Calendar.getInstance();
        TimeStamp test = new TimeStamp(date, true, new RfidKey("1"));
        //when
        Calendar got = test.getDate();
        //then
        Assert.assertEquals(date,got);
    }

    @Test
    public void testSetDate() throws Exception {
        //Given
        Calendar date = Calendar.getInstance();
        TimeStamp test = new TimeStamp(date, true, new RfidKey("1"));
        Calendar given = Calendar.getInstance();
        //when
        test.setDate(given);
        Calendar got = test.getDate();
        //then
        Assert.assertEquals(given,got);
    }

    @Test
    public void testGetCheckIn() throws Exception {
        //Given
        Calendar date = Calendar.getInstance();
        TimeStamp test = new TimeStamp(date, true, new RfidKey("1"));
        //when
        Boolean got = test.getCheckIn();
        //then
        Assert.assertEquals(true,got);
    }

    @Test
    public void testSetCheckIn() throws Exception {
        //Given
        Calendar date = Calendar.getInstance();
        TimeStamp test = new TimeStamp(date, true, new RfidKey("1"));
        Boolean given = false;
        //when
        test.setCheckIn(given);
        Boolean got = test.getCheckIn();
        //then
        Assert.assertEquals(given,got);
    }
}
//package org.demo.model;
//
//import com.sun.org.apache.xpath.internal.operations.Bool;
//import org.junit.Assert;
//import org.junit.Test;
//import org.mockito.internal.matchers.And;
//
//import java.util.Calendar;
//
//import static org.junit.Assert.*;
//
///**
// * Created by Robin_2 on 13/04/2016.
// */
//public class AndroidStampTest {
//
//    @Test
//    public void testSetDate() throws Exception {
//        //given
//        Calendar date = Calendar.getInstance();
//        AndroidStamp test = new AndroidStamp(date, true);
//        Calendar given = Calendar.getInstance();
//        //when
//        test.setDate(given);
//        Calendar got = test.getDate();
//        //then
//        Assert.assertEquals(given, got);
//    }
//
//    @Test
//    public void testGetDate() throws Exception {
//        //given
//        Calendar date = Calendar.getInstance();
//        AndroidStamp test = new AndroidStamp(date, true);
//        Calendar given  =date;
//        //when
//        Calendar got = test.getDate();
//        //then
//        Assert.assertEquals(given, got);
//    }
//
//    @Test
//    public void testSetCheckIn() throws Exception {
//
//        //given
//        Calendar date = Calendar.getInstance();
//        AndroidStamp test = new AndroidStamp(date, true);
//        Boolean given = false;
//        //when
//        test.setCheckIn(given);
//        Boolean got = test.getCheckIn();
//        //then
//        Assert.assertEquals(given, got);
//    }
//
//    @Test
//    public void testGetCheckIn() throws Exception {
//
//        //given
//        Calendar date = Calendar.getInstance();
//        AndroidStamp test = new AndroidStamp(date, true);
//        Boolean given = true;
//        //when
//        Boolean got = test.getCheckIn();
//        //then
//        Assert.assertEquals(given, got);
//    }
//}
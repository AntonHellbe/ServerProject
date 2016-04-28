//package org.demo.model;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.Calendar;
//
//import static org.junit.Assert.*;
//
///**
// * Created by Robin_2 on 13/04/2016.
// */
//public class StampTest {
//
//    @Test
//    public void testGetDate() throws Exception {
//        //given
//        Calendar date = Calendar.getInstance();
//        Stamp test = new Stamp(date,true);
//        Calendar given = date;
//        //when
//        Calendar got = test.getDate();
//        //then
//        Assert.assertEquals(given, got);
//    }
//
//    @Test
//    public void testSetDate() throws Exception {
//        //given
//        Calendar date = Calendar.getInstance();
//        Stamp test = new Stamp(date,true);
//        Calendar given = Calendar.getInstance();
//        //when
//        test.setDate(given);
//        Calendar got = test.getDate();
//        //then
//        Assert.assertEquals(given, got);
//    }
//
//    @Test
//    public void testIsCheckIn() throws Exception {
//        //given
//        Calendar date = Calendar.getInstance();
//        Stamp test = new Stamp(date,true);
//        Boolean given = true;
//        //when
//        Boolean got = test.isCheckIn();
//        //then
//        Assert.assertEquals(given, got);
//    }
//
//    @Test
//    public void testSetCheckIn() throws Exception {
//
//        //given
//        Calendar date = Calendar.getInstance();
//        Stamp test = new Stamp(date,true);
//        Boolean given = false;
//        //when
//        test.setCheckIn(given);
//        Boolean got = test.isCheckIn();
//        //then
//        Assert.assertEquals(given, got);
//    }
//
//    @Test
//    public void testToString() throws Exception {
//
//
//    }
//}
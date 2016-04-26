//package org.demo.model;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
///**
// * Created by Robin_2 on 13/04/2016.
// */
//public class UserTest {
//
//    @Test
//    public void testGetSecretId() throws Exception {
//        //given
//        User test = new User();
//        //when
//        int got = test.getSecretId();
//        //then
//        Assert.assertEquals(7,got);
//    }
//
//    @Test
//    public void testSetSecretId() throws Exception {
//        //given
//        User test = new User();
//        int given = 6;
//        //when
//        test.setSecretId(given);
//        int got = test.getSecretId();
//        //then
//        Assert.assertEquals(given, got);
//    }
//
//    @Test
//    public void testCheckIdentity() throws Exception {
//        //given
//        User test = new User("Kaka", "Bok",new RfidKey("2"),"2" );
//        String given = "Kaka";
//        //when
//        Boolean got = test.checkIdentity(given);
//        //then
//        Assert.assertEquals(true,got);
//    }
//
//    @Test
//    public void testCheckRfid() throws Exception {
//        //given
//        User test = new User("Kaka", "Bok",new RfidKey("2"),"2" );
//        RfidKey given = new RfidKey("2");
//        //when
//        Boolean got = test.checkRfid(given);
//        //then
//        Assert.assertEquals(true, got);
//    }
//
//    @Test
//    public void testGetFirstName() throws Exception {
//        //given
//        User test = new User("Kaka", "Bok",new RfidKey("2"),"2" );
//        String given = "Kaka";
//        //when
//        String got = test.getFirstName();
//        //then
//        Assert.assertEquals(given, got);
//    }
//
//    @Test
//    public void testGetLastName() throws Exception {
//        //given
//        User test = new User("Kaka", "Bok",new RfidKey("2"),"2" );
//        String given = "Bok";
//        //when
//        String got = test.getLastName();
//        //then
//        Assert.assertEquals(given, got);
//    }
//
//    @Test
//    public void testGetId() throws Exception {
//        //given
//        User test = new User("Kaka", "Bok",new RfidKey("2"),"2" );
//        String given = "2";
//        //when
//        String got = test.getId();
//        //then
//        Assert.assertEquals(given,got);
//    }
//
//    @Test
//    public void testGetRfid() throws Exception {
//        //given
//        User test = new User("Kaka", "Bok",new RfidKey("2"),"2" );
//        RfidKey given = new RfidKey("2");
//        //when
//        RfidKey got = test.getRfid();
//        //then
//        Assert.assertEquals(given, got);
//    }
//
//    @Test
//    public void testSetFirstName() throws Exception {
//        //given
//        User test = new User("Kaka", "Bok",new RfidKey("2"),"2" );
//        String given = "Anna";
//        //when
//        test.setFirstName(given);
//        String got = test.getFirstName();
//        //then
//        Assert.assertEquals(given, got);
//    }
//
//    @Test
//    public void testSetLastName() throws Exception {
//        //given
//        User test = new User("Kaka", "Bok",new RfidKey("2"),"2" );
//        String given = "Löv";
//        //when
//        test.setLastName(given);
//        String got = test.getLastName();
//        //then
//        Assert.assertEquals(given, got);
//    }
//
//    @Test
//    public void testSetRfid() throws Exception {
//        //given
//        User test = new User("Kaka", "Bok",new RfidKey("2"),"2" );
//        RfidKey given = new RfidKey("5");
//        //when
//        test.setRfid(given);
//        RfidKey got = test.getRfid();
//        //then
//        Assert.assertEquals(given, got);
//    }
//
//    @Test
//    public void testSetId() throws Exception {
//        //given
//        User test = new User("Kaka", "Bok",new RfidKey("2"),"2" );
//        String given = "5";
//        //when
//        test.setId(given);
//        String got = test.getId();
//        //then
//        Assert.assertEquals(given, got);
//    }
//
//    @Test
//    public void testToString() throws Exception {
//        //given
//        //when
//        //then
//    }
//}
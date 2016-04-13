//package org.demo.service;
//
//import org.demo.model.AndroidStamp;
//import org.demo.model.RfidKey;
//import org.demo.model.TimeStamp;
//import org.demo.model.User;
//import org.demo.repository.ListRepository;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashMap;
//
//import static org.junit.Assert.*;
//
///**
// * Created by Robin_2 on 13/04/2016.
// */
//public class AndroidServiceImplTest {
//
//
//    private ListRepository listRepository;
//
//    @Test
//    public void testGetUser() throws Exception {
//        //given
//        listRepository = new ListRepository();
//        AndroidServiceImpl test = new AndroidServiceImpl();
//        test.setRepo(listRepository);
//        User given = listRepository.getUserMap().get(new RfidKey("C48659EC"));
//        //when
//        ResponseEntity<User> got = test.getUser("C48659EC");
//
//        //then
//        Assert.assertEquals(given,got.getBody());
//    }
//
//    @Test
//    public void testGetAll() throws Exception {
//        //given
//        listRepository = new ListRepository();
//        AndroidServiceImpl test = new AndroidServiceImpl();
//        test.setRepo(listRepository);
//        RfidKey test2 = new RfidKey("C48659EC");
//        User giv = listRepository.getUserMap().get(test2);
//        ArrayList<AndroidStamp> allTimes = new ArrayList<>();
//        ArrayList<TimeStamp> userStamps = listRepository.getTimeStampMap().get(test2);
//        if(userStamps == null) {
//            userStamps = new ArrayList<>();
//            userStamps.add(new TimeStamp(Calendar.getInstance(), true, giv.getRfid()));
//            userStamps.add(new TimeStamp(Calendar.getInstance(), true, giv.getRfid()));
//            userStamps.add(new TimeStamp(Calendar.getInstance(), true, giv.getRfid()));
//            userStamps.add(new TimeStamp(Calendar.getInstance(), true, giv.getRfid()));
//            listRepository.getTimeStampMap().put(test2, userStamps);
//        }
//
//        userStamps.forEach(timeStamp -> {
//            allTimes.add(new AndroidStamp(timeStamp.getDate(), timeStamp.getCheckIn()));
//
//        });
//        //when
//        HashMap<String, Object> gott = new HashMap<>();
//        gott.put("id",test2);
//        ResponseEntity<ArrayList<AndroidStamp>> got = test.getAll(gott);
//
//        //then
//        System.out.println(got.getBody());
//        Assert.assertEquals(allTimes.size(), got.getBody().size());
//    }
//
//    @Test
//    public void testGetBetween() throws Exception {
//        //given
//        long to = 0;
//        long from = Calendar.getInstance().getTimeInMillis();;
//        Thread.sleep(10);
//        listRepository = new ListRepository();
//        AndroidServiceImpl test = new AndroidServiceImpl();
//        test.setRepo(listRepository);
//        RfidKey test2 = new RfidKey("C48659EC");
//        User giv = listRepository.getUserMap().get(test2);
//        ArrayList<AndroidStamp> allTimes = new ArrayList<>();
//        ArrayList<TimeStamp> userStamps = listRepository.getTimeStampMap().get(test2);
//        if(userStamps == null) {
//            userStamps = new ArrayList<>();
//            userStamps.add(new TimeStamp(Calendar.getInstance(), true, giv.getRfid()));
//            Thread.sleep(10);
//            userStamps.add(new TimeStamp(Calendar.getInstance(), true, giv.getRfid()));
//            Thread.sleep(10);
//            userStamps.add(new TimeStamp(Calendar.getInstance(), true, giv.getRfid()));
//            Thread.sleep(10);
//            userStamps.add(new TimeStamp(Calendar.getInstance(), true, giv.getRfid()));
//            Thread.sleep(10);
//            to = Calendar.getInstance().getTimeInMillis();
//            Thread.sleep(10);
//            userStamps.add(new TimeStamp(Calendar.getInstance(), true, giv.getRfid()));
//            Thread.sleep(10);
//            userStamps.add(new TimeStamp(Calendar.getInstance(), true, giv.getRfid()));
//            Thread.sleep(10);
//            listRepository.getTimeStampMap().put(test2, userStamps);
//        }
//
////        for (int i = 0; i < 100; i++) {
////            System.out.println("JUST FILLER!!!!");
////        }
//
//
//        for(TimeStamp timeStamp : userStamps){
//            if(timeStamp.getDate().getTimeInMillis()>=from && timeStamp.getDate().getTimeInMillis()<=to) {
//                allTimes.add(new AndroidStamp(timeStamp.getDate(), timeStamp.getCheckIn()));
//                System.out.println(allTimes.size());
//            }
//        }
//        //when
//        HashMap<String, Object> gott = new HashMap<>();
//        gott.put("id","C48659EC");
//        gott.put("from",from);
//        gott.put("to", to);
//        ResponseEntity<ArrayList<AndroidStamp>> got = test.getBetween(gott);
//
//        //then
//        System.out.println("allTimes size: "+allTimes.size() + "\ngot size: " + got.getBody().size());
//        Assert.assertEquals(allTimes.size(), got.getBody().size());
//    }
//}
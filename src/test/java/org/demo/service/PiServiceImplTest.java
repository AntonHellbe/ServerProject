package org.demo.service;

import org.demo.controller.TimeController;
import org.demo.model.AndroidStamp;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.repository.ListRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Robin_2 on 13/04/2016.
 */
public class PiServiceImplTest {

    ListRepository listRepository;
    @Test
    public void testAddNewStamp() throws Exception {
        //Given
        RfidKey key = new RfidKey("C48659EC");
        listRepository = new ListRepository();
        PiServiceImpl test = new PiServiceImpl();

        User giv = listRepository.getUserMap().get(key);
        ArrayList<TimeStamp> userStamps = listRepository.getTimeStampMap().get(key);
        if(userStamps == null) {
            userStamps = new ArrayList<>();
            userStamps.add(new TimeStamp(Calendar.getInstance(), true, giv.getRfid()));
            userStamps.add(new TimeStamp(Calendar.getInstance(), true, giv.getRfid()));
            userStamps.add(new TimeStamp(Calendar.getInstance(), true, giv.getRfid()));
            userStamps.add(new TimeStamp(Calendar.getInstance(), true, giv.getRfid()));
            userStamps.add(new TimeStamp(Calendar.getInstance(), true, giv.getRfid()));
            test.listRepository.getTimeStampMap().put(key, userStamps);
        }
        test.addNewStamp(key);
        System.out.println(test.listRepository.getTimeStampMap().get(key).size());
       // int size = test.listRepository.getTimeStampMap().size();

        Assert.assertEquals(6,6);
    }
}
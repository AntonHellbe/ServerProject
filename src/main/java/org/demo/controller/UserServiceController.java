package org.demo.controller;

import org.demo.model.AndroidStamp;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Anton on 2016-04-07.
 */

@RestController
@RequestMapping("/time")
public class UserServiceController {

    private HashMap<RfidKey, User> userMap = new HashMap<>();
    private HashMap<String, ArrayList<TimeStamp>> timeStampMap = new HashMap<>();
    public UserServiceController() {
        MemberService test = new MemberService();
        try {
            userMap = test.loadMember();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/")
    public ArrayList<TimeStamp> getAllTimeStamps(@PathVariable("id") String id) {
        RfidKey rfidKey = new RfidKey(id);
        ArrayList<TimeStamp> allTimes = new ArrayList<>();
        ArrayList<TimeStamp> userStamps = timeStampMap.get(rfidKey);
        userStamps.forEach(timeStamp -> {
            allTimes.add(new TimeStamp(timeStamp.getDate(), timeStamp.getCheckIn(), timeStamp.getRfidkey()));

        });
        allTimes.forEach(timeStamp -> {
            System.out.println(timeStamp);
        });
        return allTimes;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/{stampId}")
    public TimeStamp getTimeStamps(@PathVariable("id") String id, @PathVariable("stampId") int stampID) {
        RfidKey rfidKey = new RfidKey(id);
        ArrayList<TimeStamp> allTimes = new ArrayList<>();
        ArrayList<TimeStamp> userStamps = timeStampMap.get(rfidKey);
        System.out.println(userStamps.get(stampID));
        return userStamps.get(stampID);
    }






}

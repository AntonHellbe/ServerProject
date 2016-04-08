package org.demo.controller;

import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * Created by Robin_2 on 07/04/2016.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/time")
public class TimeServiceController {

    private ArrayList<TimeStamp> timeStamps = new ArrayList<>();
    //stringrfid key , User value
    private HashMap<RfidKey, User> userMap = new HashMap<>();
    private HashMap<RfidKey, ArrayList<TimeStamp>> timeStampMap = new HashMap<>();

    public TimeServiceController() {
        MemberService test = new MemberService();
        try {
            userMap = test.loadMember();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/cT")
    public TimeStamp createTime(@PathVariable("id")String id){
        timeStamps.add(new TimeStamp(Calendar.getInstance(),(timeStamps.size() % 2 == 0),new RfidKey(id)));
        timeStampMap.put(new RfidKey(id), timeStamps);
        System.out.println("\n timeStampMap size : " + timeStamps.size());
        //System.out.println(timeStamps.get(timeStamps.size()-1));
        System.out.println("\ntimeStampMap :\n " + timeStamps.toString());
        return timeStamps.get(timeStamps.size()-1);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/{stampId}/dT")
    public TimeStamp deleteTime(@PathVariable("id")String id, @PathVariable("stampId")int stampId){
        RfidKey rfidKey = new RfidKey(id);
        ArrayList<TimeStamp> userStamps = timeStampMap.get(rfidKey);
        if(userStamps.size()-1 < Integer.parseInt(id)){return null;}
        System.out.println(userStamps.get(stampId));
        return userStamps.remove(stampId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/{stampId}")
    public TimeStamp updateTime(@PathVariable("id")String id,@PathVariable("stampId")int stampId,
                                @RequestBody Map<String, Object> updatedUserJSON){
        TimeStamp currentTime = getTime(id, stampId);
        User currentUser = userMap.get(id);
        if (updatedUserJSON.get("updatedTime") != null) {
            System.out.println("update time");
            currentTime.setDate((Calendar)updatedUserJSON.get("updatedTime"));
        }
        timeStampMap.replace(currentUser.getRfid(),timeStamps);
        TimeStamp updatedTime = getTime(id, stampId);
        return updatedTime;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/{stampId}")
    public TimeStamp getTime(@PathVariable("id") String id, @PathVariable("stampId") int stampId){
        RfidKey rfidKey = new RfidKey(id);
        ArrayList<TimeStamp> userStamps = timeStampMap.get(rfidKey);
        if(userStamps.size() < stampId){return null;}
        System.out.println(userStamps.get(stampId));
        return userStamps.get(stampId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/")
    public ArrayList<TimeStamp> getAll(@PathVariable("id") String id){
        RfidKey rfidKey = new RfidKey(id);
        ArrayList<TimeStamp> allTimes = new ArrayList<>();
        ArrayList<TimeStamp> userStamps = timeStampMap.get(rfidKey);
        if(userStamps==null){return new ArrayList<>();}
        userStamps.forEach(timeStamp -> {
            allTimes.add(new TimeStamp(timeStamp.getDate(), timeStamp.getCheckIn(), timeStamp.getRfidkey()));

        });
        allTimes.forEach(timeStamp -> {
            System.out.println(timeStamp);
        });
        //if (allTimes.isEmpty()){return null;}
        return allTimes;
    }
}

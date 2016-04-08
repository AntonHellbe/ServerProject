package org.demo.controller;

import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Time;
import java.util.*;

/**
 * Created by Robin_2 on 07/04/2016.
 */

/**
 * Class that creates and handles the TimeStamps that are assigned to users
 **/
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/time")
public class TimeServiceController {

    private HashMap<RfidKey, User> userMap = new HashMap<>();
    private HashMap<RfidKey, ArrayList<TimeStamp>> timeStampMap = new HashMap<>();

    /**
     * Controller that loads users from a Text file, just used until we get the database working
     **/
    public TimeServiceController() {
        MemberService test = new MemberService();
        try {
            userMap = test.loadMember();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Assigns a new TimeStamp to the user
     *
     * @param id the user
     * @return the time we searched added
     **/
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/cT")
    public TimeStamp createTime(@PathVariable("id") String id) {
        User currentUser = userMap.get(new RfidKey(id));
        System.out.println(currentUser.toString());
        ArrayList<TimeStamp> temp = timeStampMap.get(currentUser.getRfid());
        boolean state = true;
        TimeStamp newStamp = new TimeStamp(Calendar.getInstance(), state, currentUser.getRfid());
        if(temp == null) {
            newStamp.setCheckIn(state);
            temp = new ArrayList<TimeStamp>();
            temp.add(newStamp);
            timeStampMap.put(currentUser.getRfid(), temp);
        }else {
            state = timeStampMap.get(currentUser.getRfid()).size() % 2 == 0;
            newStamp.setCheckIn(state);
            timeStampMap.get(currentUser.getRfid()).add(newStamp);
        }
        return newStamp;
    }

    /**
     * Removes the given time of a specific user
     *
     * @param id      the user
     * @param stampId the id of the time to be deleted
     * @return the time we deleted
     **/
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/{stampId}/dT")
    public TimeStamp deleteTime(@PathVariable("id") String id, @PathVariable("stampId") int stampId) {
        RfidKey rfidKey = new RfidKey(id);
        ArrayList<TimeStamp> userStamps = timeStampMap.get(rfidKey);
        TimeStamp removedTime = userStamps.remove(stampId);
        return removedTime;
    }

    /**
     * Updates the given time of a specific user
     *
     * @param id              the user
     * @param stampId         the id of the time to be changed
     * @param updatedTimeJSON JSON of the updated object
     * @return updated time
     **/
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/{stampId}/uT")
    public TimeStamp updateTime(@PathVariable("id") String id, @PathVariable("stampId") int stampId,
                                @RequestBody Map<String, Object> updatedTimeJSON) {
        //TimeStamp currentTime = getTime(id, stampId);
        User currentUser = userMap.get(new RfidKey(id));
        ArrayList<TimeStamp> temp = timeStampMap.get(currentUser.getRfid());
        TimeStamp dildo = temp.get(stampId);
        Calendar cal = new GregorianCalendar();
        if (updatedTimeJSON.get("date") != null) {
            long date = Long.parseLong(updatedTimeJSON.get("date").toString());
            cal.setTimeInMillis(date);
            dildo.setDate(cal);
        }
        if (updatedTimeJSON.get("checkIn") != null) {
            dildo.setCheckIn(updatedTimeJSON.get("checkIn").toString() == "true");
        }
        if (updatedTimeJSON.get("rfid") != null) {
            dildo.setRfidkey(new RfidKey(updatedTimeJSON.get("rfid").toString()));
        }
        //User updatedUser = userMap.replace(new RfidKey(id), currentUser);
        //timeStampMap.replace(currentUser.getRfid(),timeStamps);
        //TimeStamp updatedTime = timeStampMap.replace(currentUser.getRfid(),timeStamps).get(stampId);
        return dildo;
    }

    /**
     * Fetches the given time of a specific user
     *
     * @param id      the user
     * @param stampId the id of the time to be fetched
     * @return the time we searched for
     **/
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/{stampId}")
    public TimeStamp getTime(@PathVariable("id") String id, @PathVariable("stampId") int stampId) {
        RfidKey rfidKey = new RfidKey(id);
        ArrayList<TimeStamp> userStamps = timeStampMap.get(rfidKey);
        if (userStamps.size() < stampId) {
            return null;
        }
        System.out.println(userStamps.get(stampId));
        return userStamps.get(stampId);
    }

    /**
     * Fetches all the times assigned to the user
     *
     * @param id the user
     * @return all the times for the user
     **/
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/")
    public ArrayList<TimeStamp> getAll(@PathVariable("id") String id) {
        RfidKey rfidKey = new RfidKey(id);
        ArrayList<TimeStamp> allTimes = new ArrayList<>();
        ArrayList<TimeStamp> userStamps = timeStampMap.get(rfidKey);
        if (userStamps == null) {
            return new ArrayList<>();
        }
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

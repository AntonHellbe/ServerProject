package org.demo.service;

import org.bson.types.ObjectId;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.repository.ListRepository;
import org.demo.repository.TimeRepository;
import org.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * Created by Robin_2 on 11/04/2016.
 */
/**
 * Contains methods that enables the use and handling of times in the server
 **/
@Service
public class TimeServiceImpl implements TimeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimeRepository timeRepository;

    public TimeServiceImpl(){}


    @Override
    public ResponseEntity<TimeStamp> addTime(String id) {
        User currentUser = userRepository.findOne(id);
        System.out.println(currentUser.toString());
        boolean state = timeRepository.getAllByRfid(currentUser.getRfid()).size() % 2 == 0;

        TimeStamp newStamp = new TimeStamp(Calendar.getInstance(), state, currentUser.getRfid());

        if(!state) {
            newStamp.setCheckIn(state);
            timeRepository.save(newStamp);
            System.out.println(timeRepository.getAllByRfid(currentUser.getRfid()).size());

        }else {
            newStamp.setCheckIn(state);
            timeRepository.save(newStamp);
            System.out.println(timeRepository.getAllByRfid(currentUser.getRfid()).size());

        }
        return new ResponseEntity<>(newStamp, HttpStatus.OK);
    }


    public ResponseEntity<TimeStamp> deleteTime(String stampId, String id) {
        User currentUser = userRepository.findOne(id);
        System.out.println("Removing time from following user " + currentUser.toString());
        TimeStamp removedTime = timeRepository.findOne(stampId);
        System.out.println(removedTime.toString());
        if(removedTime != null) {
            timeRepository.delete(stampId);
            return new ResponseEntity<TimeStamp>(removedTime, HttpStatus.OK);
        }
        System.out.println("Deleted time: " + removedTime);
        return new ResponseEntity<TimeStamp>(HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<ArrayList<TimeStamp>> getAll(String id) {
        User currentUser = userRepository.findOne(id);
        ArrayList<TimeStamp> allTimes = new ArrayList<>();
        ArrayList<TimeStamp> userStamps = new ArrayList<>(timeRepository.getAllByRfid(currentUser.getRfid()));
        if (userStamps == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
        allTimes.forEach(timeStamp -> {
            System.out.println(timeStamp);
        });
        return new ResponseEntity<>(userStamps, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TimeStamp> getTime(String id, String stampId) {
        User currentUser = userRepository.findOne(id);
        TimeStamp certainTime = timeRepository.findOne(stampId);
        if(certainTime != null) {

            System.out.println("User = " + currentUser.toString() + " Fetched time for: " + certainTime);
            return new ResponseEntity<TimeStamp>(certainTime, HttpStatus.OK);
        }
        System.out.println("Hittade ingen tid med ID: " + stampId.toString());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity<TimeStamp> updateTime(String id, String stampId, Map<String, Object> updatedTimeJSON) {
        User currentUser = userRepository.findOne(id);
        ArrayList<TimeStamp> temp = new ArrayList<>(timeRepository.getAllByRfid(currentUser.getRfid()));
        TimeStamp timeToUpdate = timeRepository.findOne(stampId);
        Calendar cal = new GregorianCalendar();
        if (updatedTimeJSON.get("date") != null) {
            long date = Long.parseLong(updatedTimeJSON.get("date").toString());
            cal.setTimeInMillis(date);
            timeToUpdate.setDate(cal);
        }
        if (updatedTimeJSON.get("checkIn") != null) {
            timeToUpdate.setCheckIn(updatedTimeJSON.get("checkIn").toString() == "true");
        }
//        if (updatedTimeJSON.get("rfid") != null) {
//            timeToUpdate.setRfidkey(new RfidKey(updatedTimeJSON.get("rfid").toString()));
//        }
        System.out.println("Updated time: " + timeToUpdate);
        return new ResponseEntity<>(timeToUpdate, HttpStatus.OK);
    }

}

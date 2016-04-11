package org.demo.service;

import org.demo.model.AndroidStamp;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * Created by Anton on 2016-04-11.
 */

@Service
public class AndroidServiceImpl implements AndroidService {

    @Autowired
    ListRepository listRepository;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        System.out.println("lokking ofr rfid "+id);
        User wantedUser = listRepository.getUserMap().get(new RfidKey(id));
        System.out.println("found user: "+wantedUser);
        if(wantedUser != null){
            return new ResponseEntity<User>(wantedUser, HttpStatus.OK);
        }else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/all",method = RequestMethod.POST)
    public ResponseEntity<ArrayList<AndroidStamp>> getAll(@RequestBody Map<String, Object> rfidkeyJSON) {
        rfidkeyJSON.forEach((s, o) -> {
            System.out.println("[KEY]"+s+" [VALUE]"+o);
        });

        RfidKey rfidKey = new RfidKey(rfidkeyJSON.get("id").toString());
        System.out.println("get all stamps for id "+rfidKey);

        ArrayList<AndroidStamp> allTimes = new ArrayList<>();
//        User currentUser = userMap.get(rfidKey);
        ArrayList<TimeStamp> userStamps = listRepository.getTimeStampMap().get(rfidKey);
        userStamps.forEach(timeStamp -> {
            allTimes.add(new AndroidStamp(timeStamp.getDate(), timeStamp.getCheckIn()));

        });

        System.out.println("sending to client");
        allTimes.forEach(item -> System.out.println(item));

        if(allTimes != null) {
            return new ResponseEntity<ArrayList<AndroidStamp>>(allTimes, HttpStatus.OK);
        }else {
            return new ResponseEntity<ArrayList<AndroidStamp>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/between", method = RequestMethod.POST)
    public ResponseEntity<ArrayList<AndroidStamp>> getBetween(@RequestBody Map<String, Object> betweenJSON) {
        betweenJSON.forEach((s, o) -> {
            System.out.println("[KEY]"+s+" [VALUE]"+o);
        });

        String key = betweenJSON.get("id").toString();

//	    Date dt = new Date(betweenJSON.get("from").toString());

        Calendar fromDate = new GregorianCalendar();
        fromDate.setTimeInMillis(Long.parseLong(betweenJSON.get("from").toString()));
        Calendar toDate = new GregorianCalendar();
        toDate.setTimeInMillis(Long.parseLong(betweenJSON.get("to").toString()));

        RfidKey rfidKey = new RfidKey(key);

        ArrayList<TimeStamp> userStamps = listRepository.getTimeStampMap().get(rfidKey);
        ArrayList<AndroidStamp> betweenTimes = new ArrayList<>();

        //1460384544658 -----1460384551260  &&(timeStamp.getDate().before(toDate))==true
        userStamps.forEach(timeStamp -> {
            System.out.println(timeStamp.getDate().getTimeInMillis());
            //System.out.println("From: " + fromDate + " To: " + toDate);
            // TODO Fix missing timestamp date check
            if((timeStamp.getDate().after(fromDate))==true &&(timeStamp.getDate().before(toDate))==true) {
                System.out.println("1");
                betweenTimes.add(new AndroidStamp(timeStamp.getDate(), timeStamp.getCheckIn()));
            }
        });

        if(betweenTimes != null) {
            return new ResponseEntity<ArrayList<AndroidStamp>>(betweenTimes, HttpStatus.OK);
        }else {
            return new ResponseEntity<ArrayList<AndroidStamp>>(HttpStatus.NOT_FOUND);
        }
    }
}

package org.demo.controller;

import org.demo.model.TimeSamples;
import org.demo.model.User;
import org.demo.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 *
 */

/**
 * Created by Anton on 2016-04-05.
 */

/*
* Endpoints: localhost:8080/api/time
*             localhost:8080/api/time/{id}
*             localhost:8080/api/time/{id}/times
**/
@RestController
@RequestMapping("/api/time")
public class TimeController {
    private ArrayList<TimeSamples> timeStamps = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();
    //stringrfid key , User value
    private HashMap<String, User> userMap = new HashMap<>();

    public TimeController() {
        MemberService test = new MemberService();
        try {
            userMap = test.loadMember();
            userList = test.getUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }


        User temp = userMap.get("1");
        temp.newSample();
        temp.newSample();

    }

    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> timeStamps(@RequestBody User usertest){

        TimeSamples timestamp = new TimeSamples();
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("message", "TimeStamp created succesfully");
        response.put("Timestamp", timeStamps.add(timestamp));

        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Map<String, String> deleteTimeStamp(@PathVariable("id") String id) {
        timeStamps.remove(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Timestamp deleted successfully");

        return response;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> getAllTimeStamps() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("totalTimestamps", timeStamps.size());
        response.put("Users", this.userList);
        return response;
    }

    @RequestMapping("/{id}/times")
    public ResponseEntity<ArrayList> getUsersTimeStamps(@PathVariable("id")  String id) {

        User current = this.userMap.get(id);
        ArrayList<TimeSamples> listOfStamps = current.getSamples();
        System.out.println(listOfStamps.toString());

        return new ResponseEntity<ArrayList>(listOfStamps, HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<User> getTimeStamp(@PathVariable("id")  String id) {

        User current = this.userMap.get(id);

        return new ResponseEntity<User>(current, HttpStatus.OK);
    }





}

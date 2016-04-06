package org.demo.controller;

import org.demo.model.TimeSamples;
import org.demo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Anton on 2016-04-05.
 */
@RestController
@RequestMapping("/api/time")
public class TimeController {
    private ArrayList<TimeSamples> timeStamps = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();
    //stringrfid key , User value
    private HashMap<String, User> userMap = new HashMap<>();

    public TimeController() {
        userList.add(new User("jonas","kkk","1","1"));

        userList.add(new User("robin","kkk","2", "2"));
        userList.add(new User("anton","kkk","3", "3"));

        userMap.put("1",userList.get(0));
        userMap.put("2",userList.get(1));
        userMap.put("3",userList.get(2));
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
    public ResponseEntity<TimeSamples[]> getUsersTimeStamps(@PathVariable("id")  String id) {
        System.out.println("hello from times");
//        for(int i=0; i<timeStamps.size(); i++){
//            if(timeStamps.get(i).getUserId().equals(id)){
//
//            }
//        }
        //TimeSamples timestamp = timeStamps.get(Integer.parseInt(id));
        User current = this.userMap.get(id);
        TimeSamples[] listOfStamps = current.getSamples();

//        if(timestamp == null) {
//            return new ResponseEntity<TimeSamples>(HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity<TimeSamples[]>(listOfStamps, HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<User> getTimeStamp(@PathVariable("id")  String id) {
//        for(int i=0; i<timeStamps.size(); i++){
//            if(timeStamps.get(i).getUserId().equals(id)){
//
//            }
//        }
        //TimeSamples timestamp = timeStamps.get(Integer.parseInt(id));
        User current = this.userMap.get(id);

//        if(timestamp == null) {
//            return new ResponseEntity<TimeSamples>(HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity<User>(current, HttpStatus.OK);
    }





}

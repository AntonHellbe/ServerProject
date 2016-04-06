package org.demo.controller;

import org.demo.model.TimeSamples;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.util.*;

/**
 * Created by Anton on 2016-04-05.
 */
@RestController
@RequestMapping("/api/time")
public class TimeController {
    private ArrayList<TimeSamples> timeStamps;

    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> timeStamps(@RequestBody Map <String, Object> timeMap){

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
        response.put("UserID", timeStamps);
        return response;
    }
    @RequestMapping("/time&{id}")
    public ResponseEntity<TimeSamples> getTimeStamp(@PathVariable("id")  String id) {
//        for(int i=0; i<timeStamps.size(); i++){
//            if(timeStamps.get(i).getUserId().equals(id)){
//
//            }
//        }
        TimeSamples timestamp = timeStamps.get(Integer.parseInt(id));

        if(timestamp == null) {
            return new ResponseEntity<TimeSamples>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TimeSamples>(timestamp, HttpStatus.OK);
    }





}

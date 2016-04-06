package org.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Anton on 2016-04-05.
 */
@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeRepository timeRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> timeStamps(@RequestBody Map <String, Object> timeMap){
        TimeStamps timestamp = new TimeStamps(Integer.parseInt(timeMap.get("day").toString()), Integer.parseInt(timeMap.get("hour").toString()), Integer.parseInt(timeMap.get("minute").toString()));
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("message", "TimeStamp created succesfully");

        response.put("Timestamp", timeRepository.save(timestamp));

        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Map<String, String> deleteTimeStamp(@PathVariable("id") String id) {
        timeRepository.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Timestamp deleted successfully");

        return response;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> getAllTimeStamps() {
        List<TimeStamps> timestamps = timeRepository.findAll();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("totalTimestamps", timestamps.size());
        response.put("UserID", timestamps);
        return response;
    }
    @RequestMapping("/time")
    public ResponseEntity<TimeStamps> getTimeStamp(@PathVariable("id")  String id) {
        Timestamp timestamp = timeRepository.findOne(id);

        if(timestamp == null) {
            return new ResponseEntity<TimeStamps>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TimeStamps>(timestamp, HttpStatus.OK);



    }





}

package org.demo.controller;


import org.demo.model.AndroidBetweenQuery;
import org.demo.model.RfidKey;
import org.demo.model.ScheduleStamp;
import org.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by Anton on 2016-05-09.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @RequestMapping(value = "/between?from={from}&to={to}&id={id}", method = RequestMethod.GET)
    public ResponseEntity<List<ScheduleStamp>> getSchedule(@PathVariable("from") long from, @PathVariable ("to") long to, @PathVariable("id") String id) {
        return scheduleService.getBetweenSchedule(from, to, id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ScheduleStamp> addSchedule(@RequestBody ScheduleStamp scheduleStamp) {
        return scheduleService.addSchedule(scheduleStamp);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ScheduleStamp>> getAll() {
        return scheduleService.getAll();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<List<ScheduleStamp>> updateSchedule(@RequestBody List<ScheduleStamp> updatedStamps) {
        return scheduleService.updateSchedule(updatedStamps);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<List<ScheduleStamp>> removeSchedule(@RequestBody List<ScheduleStamp> stampsToRemove) {
        return scheduleService.removeSchedule(stampsToRemove);
    }
}

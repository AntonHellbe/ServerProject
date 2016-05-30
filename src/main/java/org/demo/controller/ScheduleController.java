package org.demo.controller;


import org.demo.model.ScheduleStamp;
import org.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Anton Hellbe
 */
@CrossOrigin(maxAge = 1)
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    /**
     * Between method to fetch all ScheduleStamps between two times for a specific ID (Account)
     * @param from "from" date
     * @param to "to" date
     * @param id of specific Account
     * @return List of ScheduleStamps between the dates provided for the specific ID.
     */

    @RequestMapping(value = "/between?from={from}&to={to}&id={id}", method = RequestMethod.GET)
    public List<ScheduleStamp> getSchedule(@PathVariable("from") long from, @PathVariable ("to") long to, @PathVariable("id") String id) {
        return scheduleService.getBetweenSchedule(from, to, id);
    }

    /**
     * Add ScheduleStamp
     * @param scheduleStamp ScheduleStamp to add
     * @return new ScheduleStamp that is added into the database
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ScheduleStamp> addSchedule(@RequestBody ScheduleStamp scheduleStamp) {
        return scheduleService.addSchedule(scheduleStamp);
    }

    /**
     * Gets all scheduleStamps for a specific Account
     * @param id of the Account
     * @return list of all the ScheduleStamps
     */

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<List<ScheduleStamp>> getAll(@PathVariable ("id") String id) {
        return scheduleService.getAll(id);
    }

    /**
     * Method to update a list of ScheduleStamps
     * @param updatedStamps list of updated ScheduleStamps
     * @return list of successfully updated ScheduleStamps
     */

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<List<ScheduleStamp>> updateSchedule(@RequestBody List<ScheduleStamp> updatedStamps) {
        return scheduleService.updateSchedule(updatedStamps);
    }

    /**
     * Method to delete a list of ScheduleStamps
     * @param stampsToRemove list of ScheduleStamps to remove
     * @return list of successfully removed ScheduleStamps
     */

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<List<ScheduleStamp>> removeSchedule(@RequestBody List<ScheduleStamp> stampsToRemove) {
        return scheduleService.removeSchedule(stampsToRemove);
    }
}

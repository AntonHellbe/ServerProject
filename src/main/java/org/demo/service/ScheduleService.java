package org.demo.service;


import org.demo.model.ScheduleStamp;
import org.springframework.http.ResponseEntity;
import java.util.List;

/**
 @Author Anton Hellbe
 Service Class for ScheduleStamps
 */
public interface ScheduleService {

    /**
     * Method to fetch ScheduleStamps between two dates for a specific userID
     * @param from "from" date to fetch for
     * @param to "to" date to fetch for
     * @param id of the specific user
     * @return a list of ScheduleStamps between two dates for a specific userID
     */

    List<ScheduleStamp> getBetweenSchedule(long from, long to, String id);

    /**
     * getAll ScheduleStamps for a specific userID
     * @param id of a specific user
     * @return list of the ScheduleStamps for the Account
     */

    ResponseEntity<List<ScheduleStamp>> getAll(String id);

    /**
     * Adds a new ScheduleStamp
     * @param scheduleStamp to add into the database
     * @return succesfully added scheduleStamp
     */

    ResponseEntity<ScheduleStamp> addSchedule(ScheduleStamp scheduleStamp);

    /**
     * Updates a list of ScheduleStamps
     * @param scheduleStamps list of updated ScheduleStamps
     * @return list of succesfully updated ScheduleStamps
     */

    ResponseEntity<List<ScheduleStamp>> updateSchedule(List<ScheduleStamp> scheduleStamps);

    /**
     * Removes a list of ScheduleStamps
     * @param scheduleStamps list of schedulestamps to remove
     * @return list of successfully removed ScheduleStamps
     */

    ResponseEntity<List<ScheduleStamp>> removeSchedule (List<ScheduleStamp> scheduleStamps);

}

package org.demo.service;


import org.demo.model.ScheduleStamp;
import org.springframework.http.ResponseEntity;
import java.util.List;

/**
 * Created by Anton on 2016-05-09.
 */
public interface ScheduleService {

    List<ScheduleStamp> getBetweenSchedule(long from, long to, String id);

    ResponseEntity<List<ScheduleStamp>> getAll();

    ResponseEntity<ScheduleStamp> addSchedule(ScheduleStamp scheduleStamp);

    ResponseEntity<List<ScheduleStamp>> updateSchedule(List<ScheduleStamp> scheduleStamps);

    ResponseEntity<List<ScheduleStamp>> removeSchedule (List<ScheduleStamp> scheduleStamps);

}

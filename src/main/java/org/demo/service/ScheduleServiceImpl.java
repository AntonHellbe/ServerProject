package org.demo.service;

import org.demo.errorHandler.ScheduleErrorHandler;
import org.demo.model.ScheduleStamp;
import org.demo.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Anton on 2016-05-09.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleErrorHandler errorHandler;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public List<ScheduleStamp> getBetweenSchedule(long from, long to, String id) {
        List<ScheduleStamp> userList = scheduleRepository.getBetweenQuery(from, to, id);
        return userList;
//        if(userList != null) {
//            return new ResponseEntity<List<ScheduleStamp>>(userList, HttpStatus.OK);
//        }
//        return new ResponseEntity<List<ScheduleStamp>>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<ScheduleStamp>> getAll() {
        List<ScheduleStamp> stampList = scheduleRepository.findAll();
        return new ResponseEntity<>(stampList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ScheduleStamp> addSchedule(ScheduleStamp scheduleStamp) {
        HttpStatus status = errorHandler.addScheduleHandler(scheduleStamp);
        if(status !=HttpStatus.OK) {
            return new ResponseEntity<>(status);
        }
        scheduleRepository.save(scheduleStamp);
        return new ResponseEntity<ScheduleStamp>(scheduleStamp, status);

    }

    @Override
    public ResponseEntity<List<ScheduleStamp>> updateSchedule(List<ScheduleStamp> scheduleStamps) {
        HttpStatus status = errorHandler.updateScheduleHandler(scheduleStamps);
        if(status != HttpStatus.OK) {
            return new ResponseEntity<>(status);
        }
        for (int i = 0; i < scheduleStamps.size() ; i++) {
            scheduleRepository.save(scheduleStamps);
        }
        return new ResponseEntity<List<ScheduleStamp>>(scheduleStamps, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ScheduleStamp>> removeSchedule(List<ScheduleStamp> scheduleStamps) {
        if(scheduleStamps != null) {
            for (int i = 0; i < scheduleStamps.size() ; i++) {
                scheduleRepository.delete(scheduleStamps.get(i).getId());
            }

            return new ResponseEntity<List<ScheduleStamp>>(scheduleStamps, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

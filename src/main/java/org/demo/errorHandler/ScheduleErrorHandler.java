package org.demo.errorHandler;

import org.demo.model.ScheduleStamp;
import org.demo.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Robin_2 on 20/05/2016.
 * @author Robin Johnsson
 */
@Component
public class ScheduleErrorHandler {
    @Autowired
    ScheduleRepository scheduleRepository;

    public HttpStatus addScheduleHandler(ScheduleStamp scheduleStamp){
        if (scheduleStamp!=null)return HttpStatus.OK;
        System.out.println("Heja");
        return HttpStatus.METHOD_FAILURE;
    }

    public HttpStatus updateScheduleHandler(List<ScheduleStamp> scheduleStamps){
        if(scheduleStamps!=null) return HttpStatus.OK;
        return HttpStatus.METHOD_FAILURE;
    }
}

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

    /**
     * Checks is the stamp is null
     * @param scheduleStamp the stamp to check
     * @return Status-message
     **/
    public HttpStatus addScheduleHandler(ScheduleStamp scheduleStamp){
        if (scheduleStamp!=null)return HttpStatus.OK;
        System.out.println("Heja");
        return HttpStatus.METHOD_FAILURE;
    }

    /**
     * Checks is the list is null
     * @param scheduleStamps the list to check
     * @return Status-message
     **/
    public HttpStatus updateScheduleHandler(List<ScheduleStamp> scheduleStamps){
        if(scheduleStamps!=null) return HttpStatus.OK;
        return HttpStatus.METHOD_FAILURE;
    }
}

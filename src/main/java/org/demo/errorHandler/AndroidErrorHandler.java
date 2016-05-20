package org.demo.errorHandler;

import org.demo.model.RfidKey;
import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.demo.repository.ScheduleRepository;
import org.demo.repository.TimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Robin_2 on 20/05/2016.
 */
@Component
public class AndroidErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(AndroidErrorHandler.class);

    @Autowired
    TimeRepository timeRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    public HttpStatus getUserHandler(String id){
        if(accountRepository.findOne(id)==null){
            log.info("No user found");
            return HttpStatus.METHOD_FAILURE;
        }
        return HttpStatus.OK;
    }

    public HttpStatus logInUserHandler(Map<String, Object> getSpecificUserJSON){
        Account loginUser = accountRepository.findByName(getSpecificUserJSON.get("firstName").toString(), getSpecificUserJSON.get("lastName").toString());
        log.info("Logging in from Android with user: " + loginUser);
        if (loginUser!=null)return HttpStatus.OK;
        return HttpStatus.METHOD_FAILURE;
    }
}

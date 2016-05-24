package org.demo.errorHandler;

import org.demo.model.TimeStamp;
import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.demo.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Created by Anton on 2016-05-19.
 */

// BLYAT

@Component
public class TimeErrorHandler {

    @Autowired
    TimeRepository timeRepository;

    @Autowired
    AccountRepository accountRepository;

    public TimeErrorHandler() {

    }

    public HttpStatus updateTimeHandler(TimeStamp timeStamp) {
        TimeStamp tempStamp = timeRepository.findOne(timeStamp.getId());
        if(timeStamp != null && tempStamp != null) {
            Long temp = timeStamp.getDate();
            if(temp.toString().length() < 12) {
                return HttpStatus.LENGTH_REQUIRED;
            }

            if(timeRepository.findCertainTime(timeStamp) != null) {
                return HttpStatus.CONFLICT;
            }

            if(tempStamp.getDate() == timeStamp.getDate()) {
                if(tempStamp.getRfidkey() != timeStamp.getRfidkey() && accountRepository.findUserByRfid(timeStamp.getRfidkey()) != null) {
                    return HttpStatus.OK;
                }
            }
            if (tempStamp.getRfidkey().equals(timeStamp.getRfidkey())) {
                if(tempStamp.getDate() != timeStamp.getDate()) {
                    return HttpStatus.OK;
                }
            }
        }

        return HttpStatus.METHOD_FAILURE;

    }

    public HttpStatus newTimeHandler(TimeStamp timeStamp) {
        if(timeStamp != null) {
            if(timeRepository.findCertainTime(timeStamp) != null) {
                return HttpStatus.CONFLICT;
            }

            if(accountRepository.findUserByRfid(timeStamp.getRfidkey()) != null) {
                return HttpStatus.OK;
            }
        }

        return HttpStatus.METHOD_FAILURE;
    }

    public HttpStatus getAllHandler(String id) {

        if(timeRepository.getByRfid(accountRepository.findOne(id).getRfidKey()) != null ) {
            return HttpStatus.OK;
        }
        return HttpStatus.METHOD_FAILURE;
    }

    public HttpStatus getHandler(String id, String stampId) {
        if(timeRepository.findOne(stampId) != null) {
            return HttpStatus.OK;
        }

        return HttpStatus.I_AM_A_TEAPOT;
    }

    public HttpStatus deleteTimeHandler(String id, String stampId) {
        if(timeRepository.findOne(id) != null) {
            return HttpStatus.OK;
        }
        //asdasdasdas
        //asdadasdasd
        System.out.println("dildo");
        return HttpStatus.NOT_FOUND;
    }

    public HttpStatus addTime(String id, TimeStamp newStamp) {
        Account temp = accountRepository.findOne(id);
        if(newStamp != null) {
            if(accountRepository.findUserByRfid(temp.getRfidKey()) != null && timeRepository.findCertainTime(newStamp) == null) {
                return HttpStatus.OK;
            }
        }

        return HttpStatus.CONFLICT;
    }






}

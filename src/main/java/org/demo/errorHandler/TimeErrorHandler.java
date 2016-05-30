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
 * @Author Anton Hellbe
 */

@Component
public class TimeErrorHandler {

    @Autowired
    TimeRepository timeRepository;

    @Autowired
    AccountRepository accountRepository;

    public TimeErrorHandler() {

    }

    /**
     * Method takes care of any errors when updating a TimeStamp
     * @param timeStamp that is being updated
     * @return httpstatus if updated is OK or not
     */

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

    /**
     * Takes care if there is any conflict when adding a new timeStamp
     * @param timeStamp to check if there is any errors
     * @return HttpStatus if request is OK or not
     */

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

    /**
     * Method takes care of any errors when getting all timeStamps for a specific userID
     * @param id of the Account
     * @return HttpStatus if request is OK or not
     */
    public HttpStatus getAllHandler(String id) {

        if(timeRepository.getByRfid(accountRepository.findOne(id).getRfidKey()) != null ) {
            return HttpStatus.OK;
        }
        return HttpStatus.METHOD_FAILURE;
    }

    /**
     * Method checks if there is any errors when getting a specific TimeStamps
     * @param id of the Account
     * @param stampId of the specific TimeStamp
     * @return HttpStatus if request is OK or not
     */
    public HttpStatus getHandler(String id, String stampId) {
        if(timeRepository.findOne(stampId) != null) {
            return HttpStatus.OK;
        }

        return HttpStatus.I_AM_A_TEAPOT;
    }

    /**
     * Method checks if there is any errors when removing a TimeStamp
     * @param id of the Account
     * @param stampId of the Specific TimeStamp
     * @return HttpStatus if request is OK or not
     */

    public HttpStatus deleteTimeHandler(String id, String stampId) {
        if(timeRepository.findOne(stampId) != null) {
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    /**
     * Method checks if there is any conflict when adding a new TimeStamp
     * @param id of the Account
     * @param newStamp to add into the database
     * @return HttpStatus if request is OK or not
     */
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

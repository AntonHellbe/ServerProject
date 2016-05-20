package org.demo.service;

import org.demo.errorHandler.PiErrorHandler;
import org.demo.model.PiStamp;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.demo.repository.TimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Created by seb on 2016-04-11.
 */

/**
 * Class that handles methods used by the RaspberryPi
 **/
@Service
public class PiServiceImpl implements PiService {

    private static final Logger log = LoggerFactory.getLogger(Account.class);

    @Autowired
    private PiErrorHandler piErrorHandler;

    /**
     * Get the repository
     */
    @Autowired
    TimeRepository timeRepository;

    @Autowired
    AccountRepository accountRepository;

    /**
     * Handle the request from the controller
     *
     * @param rfidKey the new RFID of the user
     * @return a ResponseEntity of PiStamp with with userinfo and timestamp-info
     */
    @Override
    public ResponseEntity<PiStamp> addNewStamp(RfidKey rfidKey) {

        HttpStatus status = piErrorHandler.addNewStampHandler(rfidKey);

        Account currentAccount = accountRepository.findUserByRfid(rfidKey);


        if (status!=HttpStatus.OK) {
            return new ResponseEntity<>(status);
        }
        boolean state;
        TimeStamp got = timeRepository.stateCheck(rfidKey);
        if (got == null) {
            state = true;
        } else {
            state = !(got.getCheckIn());
        }
        TimeStamp newStamp = new TimeStamp(Calendar.getInstance().getTimeInMillis(), state, currentAccount.getRfidKey());
        timeRepository.save(newStamp);
        return new ResponseEntity<>(new PiStamp(newStamp.getCheckIn(), currentAccount), status);
    }

    @Override
    public ResponseEntity<PiStamp> getTime() {
        PiStamp currentTime = new PiStamp();

        currentTime.setDate(Calendar.getInstance().getTimeInMillis());

        return new ResponseEntity<PiStamp>(currentTime, HttpStatus.OK);
    }
}

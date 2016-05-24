package org.demo.errorHandler;

import org.demo.model.RfidKey;
import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.demo.repository.TimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Created by Robin_2 on 20/05/2016.
 * @author Robin Johnsson
 */
@Component
public class PiErrorHandler {
    private static final Logger log = LoggerFactory.getLogger(PiErrorHandler.class);

    @Autowired
    TimeRepository timeRepository;

    @Autowired
    AccountRepository accountRepository;

    public HttpStatus addNewStampHandler(RfidKey rfidKey){
        log.info("Entering addNewStamphandler");
        Account currentAccount = accountRepository.findUserByRfid(rfidKey);
        if (currentAccount.isEnabled()==false){
            log.info("Wrong: " + HttpStatus.LOCKED);
            return HttpStatus.LOCKED;
        }
        if (currentAccount.getRfidKey().isEnabled()==false){
            log.info("Wrong: " + HttpStatus.FORBIDDEN);
            return HttpStatus.FORBIDDEN;
        }
        return HttpStatus.OK;
    }
}

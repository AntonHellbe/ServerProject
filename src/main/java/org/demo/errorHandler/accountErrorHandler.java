package org.demo.errorHandler;

import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Robin_2 on 19/05/2016.
 */
@Component
public class accountErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(accountErrorHandler.class);

    @Autowired
    private AccountRepository accountRepository;

    public HttpStatus getAllHandler(List<Account> accountList){
        if (accountList != null) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    public HttpStatus getUserHandler(Account gotAccount){
        if (gotAccount != null) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    public HttpStatus updateHandler(Account updatedAccount){
        if (updatedAccount == null){
            return HttpStatus.NO_CONTENT;
        }
        //Checks the status of the RFID
        HttpStatus rfidStatus = rfidHandler(updatedAccount);
        //Checks the status of the username
        HttpStatus usernameStatus= usernameHandler(updatedAccount);
        //Checks the status of the password
        HttpStatus passwordStatus = passwordUpdateHandler(updatedAccount);
        //Puts them all in an array for easy handling
        HttpStatus[] statuses = {rfidStatus, usernameStatus, passwordStatus};
        //If there are any wrongs in the statuses, return that status!
        for (int i= 1; i<statuses.length;i++) {
            if (statuses[i]!= HttpStatus.OK)return statuses[i];
        }
        return HttpStatus.OK;
    }

    public HttpStatus rfidHandler(Account updatedAccount){
        Account temp = accountRepository.findOne(updatedAccount.getId());
        int rfidLength = updatedAccount.getRfidKey().toString().length();
        if(accountRepository.findUserByRfid(updatedAccount.getRfidKey())!=null &&
                rfidLength != 0 &&
                !temp.getRfidKey().equals(updatedAccount.getRfidKey())){
            log.info("RFID is already in use!");
            return HttpStatus.CONFLICT;
        }if ((rfidLength != 0 && rfidLength <7) || rfidLength >8 ){
            log.info("The rfid is to long/short!");
            return HttpStatus.LENGTH_REQUIRED;
        }
        return HttpStatus.OK;
    }

    public HttpStatus usernameHandler(Account updatedAccount){
        Account temp = accountRepository.findOne(updatedAccount.getId());
        //Checks if the username is already in use. If it is in use, we check if it is the user to be updated who has that username
        if(accountRepository.findByUserName(updatedAccount.getUsername()) !=null &&
                !temp.getUsername().equals(updatedAccount.getUsername())){
            log.info("Username is already in use!");
            return HttpStatus.IM_USED;
        }if (updatedAccount.getUsername().length()<4){
            log.info("Username to short!");
            return HttpStatus.LENGTH_REQUIRED;
        }
        return HttpStatus.OK;
    }

    public HttpStatus passwordUpdateHandler(Account updatedAccount){
        Account temp = accountRepository.findOne(updatedAccount.getId());
        //Checks if the password is uppdated, and if it is, it has to be 4 characters or more
        if(!temp.getPassword().equals(updatedAccount.getPassword()) && updatedAccount.getPassword().length()<4){
            log.info("Wrong with the password");
            return HttpStatus.LENGTH_REQUIRED;
        }
        return HttpStatus.OK;
    }

    public HttpStatus deleteHandler(Account accountToRemove){
        if (accountToRemove != null) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }

    }

    public HttpStatus addHandler(Account accountToAdd){
        if (accountToAdd.getUsername()==null &&accountToAdd.getFirstName()==null && accountToAdd.getLastName()==null){
            log.info("no username, firstname and lastname!");
            return HttpStatus.NO_CONTENT;
        }
        return passwordAddHandler(accountToAdd);
    }

    public HttpStatus passwordAddHandler(Account accountToAdd){
        System.out.println(accountToAdd.getPassword());
        if (accountToAdd.getPassword()==null){
            log.info("No password");
            return HttpStatus.NO_CONTENT;
        }
        return HttpStatus.OK;
    }
}

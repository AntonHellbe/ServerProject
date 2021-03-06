package org.demo.errorHandler;

import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Robin_2 on 19/05/2016.
 * @author Robin Johnsson
 */
@Component
public class AccountErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(AccountErrorHandler.class);

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Checks is the list is null or has Accounts in it
     * @param accountList the list to check
     * @return Status-message
     **/
    public HttpStatus getAllHandler(List<Account> accountList){
        if (accountList != null) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    /**
     * Checks is the Account is null or not
     * @param gotAccount the account to check
     * @return Status-message
     **/
    public HttpStatus getUserHandler(Account gotAccount){
        if (gotAccount != null) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    /**
     * Checks if the sent in account-data is usable or if it contains errors
     * @param updatedAccount the account to check
     * @return Status-message
     **/
    public HttpStatus updateHandler(Account updatedAccount){
        if (updatedAccount == null){
            return HttpStatus.METHOD_FAILURE;
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
        for (int i= 0; i<statuses.length;i++) {
            System.out.println("Status for " + i + " " + statuses[i]);
            if (statuses[i]!= HttpStatus.OK)return statuses[i];
        }
        return HttpStatus.OK;
    }

    /**
     * Checks is the rfid is null, empty, already in use or to long/short
     * @param updatedAccount the account to check
     * @return Status-message
     **/
    public HttpStatus rfidHandler(Account updatedAccount){
        Account temp = accountRepository.findOne(updatedAccount.getId());
        int rfidLength = updatedAccount.getRfidKey().toString().length();
        if(accountRepository.findUserByRfid(updatedAccount.getRfidKey())!=null){
            if (rfidLength>0){
                System.out.println(!temp.getRfidKey().equals(updatedAccount.getRfidKey()));
                if (!temp.getRfidKey().equals(updatedAccount.getRfidKey())){
                    log.info("RFID is already in use!");
                    return HttpStatus.CONFLICT;
                }
            }
        }
        if ((rfidLength != 0 && rfidLength <7) || rfidLength >8 ){
            log.info("The rfid is to long/short!");
            return HttpStatus.I_AM_A_TEAPOT;
        }
        return HttpStatus.OK;
    }

    /**
     * Checks is the username is null, empty, already in use or to short
     * @param updatedAccount the account to check
     * @return Status-message
     **/
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

    /**
     * Checks is the password is null or below 4 characters
     * @param updatedAccount the account to check
     * @return Status-message
     **/
    public HttpStatus passwordUpdateHandler(Account updatedAccount){
        Account temp = accountRepository.findOne(updatedAccount.getId());
        //Checks if the password is uppdated, and if it is, it has to be 4 characters or more
        if(!temp.getPassword().equals(updatedAccount.getPassword()) && updatedAccount.getPassword().length()<4){
            log.info("Wrong with the password");
            return HttpStatus.LENGTH_REQUIRED;
        }
        return HttpStatus.OK;
    }

    /**
     * Checks is the account to delete is null
     * @param accountToRemove the account to check
     * @return Status-message
     **/
    public HttpStatus deleteHandler(Account accountToRemove){
        if (accountToRemove != null) {
            return HttpStatus.OK;
        } else {
            log.info("No user found");
            return HttpStatus.NOT_FOUND;
        }

    }

    /**
     * Checks is the account contains usable data or if it contains errors
     * @param accountToAdd the account to check
     * @return Status-message
     **/
    public HttpStatus addHandler(Account accountToAdd){
        System.out.println(accountToAdd);
        if (accountToAdd.getUsername()==null &&accountToAdd.getFirstName()==null && accountToAdd.getLastName()==null){
            log.info("no username, firstname and lastname!");
            return HttpStatus.NOT_ACCEPTABLE;
        }if (accountToAdd.getUsername()==null &&accountToAdd.getFirstName()=="" && accountToAdd.getLastName()==""){
            log.info("no username, firstname and lastname!");
            return HttpStatus.NOT_ACCEPTABLE;
        }if (accountRepository.findByUserName(accountToAdd.getUsername())!=null){
            log.info("username taken already");
            return HttpStatus.IM_USED;
        }

        if (accountRepository.findUserByRfid(accountToAdd.getRfidKey())!=null){
	        if (accountToAdd.getRfidKey().getId() != "") {
		        log.info("RFID is already in use!");
		        return HttpStatus.CONFLICT;
	        }
        }
        return passwordAddHandler(accountToAdd);
    }

    /**
     * Checks is the account-password is null or fewer than 4 characters
     * @param accountToAdd the account to check
     * @return Status-message
     **/
    public HttpStatus passwordAddHandler(Account accountToAdd){
        System.out.println(accountToAdd.getPassword());
        if (accountToAdd.getPassword()==null||accountToAdd.getPassword().length()<4){
            log.info("No password");
            return HttpStatus.METHOD_FAILURE;
        }
        return HttpStatus.OK;
    }

    /**
     * Checks is the list is null or has Accounts in it
     * @param newPassword the password to check
     * @return Status-message
     **/
    public HttpStatus passwordUpdate(String newPassword){
        if (newPassword.length() <4 || newPassword==null){
            log.info("To short password");
            return HttpStatus.LENGTH_REQUIRED;
        }
        return HttpStatus.OK;
    }
}

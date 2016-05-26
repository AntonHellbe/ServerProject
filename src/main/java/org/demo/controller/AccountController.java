package org.demo.controller;

import org.demo.config.AuthoritiesConstants;
import org.demo.model.RfidKey;
import org.demo.model.security.Account;
import org.demo.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Anton on 2016-04-07.
 */
/**
 * Class that acts as a controller for methods and Classes associated with Users
 * @author Robin Johnsson, Sebastian Börebäck
 **/
@CrossOrigin(maxAge = 1)
@RestController
@RequestMapping("/api/users")
public class AccountController {


    @Autowired
    AccountService userService;


	private static final Logger log = LoggerFactory.getLogger(AccountController.class);


    /**
     * Fetches all the users from our list
     * @return all users in the list
     **/
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAllUser() {
        log.info("get all users");
        return userService.getAllUser();
    }

    /**
     * Fetches a specific user from our list
     * @param id the id of the user we are searching for
     * @return The sought after user
     **/
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Account> getUser(@PathVariable("id") String id) {
        return userService.getUser(id);
    }


    //Only admin app uses this
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Account> updateUserAdmin(@RequestBody Map<String, Object> newAccount){
        log.info("Entering adminUpdateUser");
        System.out.println("sent in data " + newAccount.toString());
        ArrayList<LinkedHashMap<String, Object>> accountMapList = (ArrayList<LinkedHashMap<String, Object>>) newAccount.get("Account");
        LinkedHashMap<String, Object> accountMap = accountMapList.get(0);
        LinkedHashMap<String, Object> rfidMap = (LinkedHashMap<String, Object>) accountMap.get("rfidKey");
        Account newAcc = new Account();

        newAcc.setId((String) accountMap.get("id")); //tror denna behövdes!
        newAcc.setUsername((String) accountMap.get("username"));
        newAcc.setPassword((String)accountMap.get("password"));
        newAcc.setFirstName((String)accountMap.get("firstName"));
        newAcc.setLastName((String)accountMap.get("lastName"));

        log.info("before rfid");
        RfidKey rfidKey = new RfidKey((String)rfidMap.get("id"));
        rfidKey.setEnabled(true);
        newAcc.setRfidKey(rfidKey);
        newAcc.setAuthorities(AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER));

        log.info("account to add from adminApp: " + newAcc);
        return userService.updateUser(newAcc);
    }

    /**
     * Deletes the user associated with the given id
     * @param id the id of the user to be deleted
     * @return the deleted user
     **/
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Account> removeUser(@PathVariable("id") String id) {
        return userService.removeUser(id);

    }

    //Only admin app uses this
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Account> addUserAdmin(@RequestBody Map<String, Object> newAccount){
        log.info("Entering adminAddUser");
        System.out.println("sent in data " + newAccount.toString());
        ArrayList<LinkedHashMap<String, Object>> accountMapList = (ArrayList<LinkedHashMap<String, Object>>) newAccount.get("Account");
        LinkedHashMap<String, Object> accountMap = accountMapList.get(0);
        LinkedHashMap<String, Object> rfidMap = (LinkedHashMap<String, Object>) accountMap.get("rfidKey");
        Account newAcc = new Account();
        log.info((String)newAccount.get("password"));
        newAcc.setPassword((String)accountMap.get("password"));
        newAcc.setFirstName((String)accountMap.get("firstName"));
        newAcc.setLastName((String)accountMap.get("lastName"));
        RfidKey rfidKey = new RfidKey((String)rfidMap.get("id"));
        rfidKey.setEnabled(true);
        newAcc.setRfidKey(rfidKey);
        newAcc.setAuthorities(AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER));
        return userService.addUser(newAcc);
    }
}

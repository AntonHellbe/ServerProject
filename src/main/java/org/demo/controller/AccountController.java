package org.demo.controller;

import org.demo.config.AuthoritiesConstants;
import org.demo.model.RfidKey;
import org.demo.model.security.Account;
import org.demo.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
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
 * @author Robin Johnsson, Sebastian Börebäck, Anton Hellbe
 **/
@CrossOrigin(maxAge = 1, origins = "*")
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

    /**
     * Updates a user with new information
     * @param updatedAccount the updated information
     * @return the updated user
     **/
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Account> updateUser(@RequestBody Account updatedAccount) throws InterruptedException {
	    log.info("updateing user "+updatedAccount);
        log.info("in controller");


	    return userService.updateUser(updatedAccount);
    }

    //Only admin app uses this
    @RequestMapping(method = RequestMethod.PUT, value = "/a")
    public ResponseEntity<Account> updateUserAdmin(@RequestBody Map<String, Object> newAccount){
        boolean admin = false;
        boolean user = false;
        boolean pi = false;
        log.info("Entering adminUpdateUser");
        System.out.println("sent in data " + newAccount.toString());
        ArrayList<LinkedHashMap<String, Object>> accountMapList = (ArrayList<LinkedHashMap<String, Object>>) newAccount.get("Account");
        LinkedHashMap<String, Object> accountMap = accountMapList.get(0);
        LinkedHashMap<String, Object> rfidMap = (LinkedHashMap<String, Object>) accountMap.get("rfidKey");
        ArrayList<LinkedHashMap<String, Object>> auth = (ArrayList<LinkedHashMap<String,Object>>) accountMap.get("authorities");
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

        List<GrantedAuthority> list = new ArrayList<>();

        for (int i = 0; i < auth.size(); i++) {
            if(auth.get(i).get("authority").toString().equals("ROLE_ADMIN")){
                admin = true;
            }else if(auth.get(i).get("authority").toString().equals("ROLE_USER")){
                user = true;
            }
            if(user && admin){
                newAcc.setAuthorities(AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER,
                        AuthoritiesConstants.ADMIN));
            }else if(admin){
                newAcc.setAuthorities(AuthorityUtils.createAuthorityList(AuthoritiesConstants.ADMIN));
            }else if(user){
                newAcc.setAuthorities(AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER));
            }
        }

        log.info("New list " + list.toString());
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

    /**
     * Adds a new user
     * @return the newly added user
     **/
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Account> addUser(@RequestBody Account newAccount) {
        System.out.println("sent in data " + newAccount);
        log.info("add user "+newAccount);
	    return userService.addUser(newAccount);

    }

    //Only admin app uses this
    @RequestMapping(method = RequestMethod.POST, value = "/a")
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

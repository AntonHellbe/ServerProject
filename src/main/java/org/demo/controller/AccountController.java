package org.demo.controller;

import org.demo.model.RfidKey;
import org.demo.model.security.Account;
import org.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Anton on 2016-04-07.
 */
/**
 * Class that acts as a controller for methods and Classes associated with Users
 **/
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class AccountController {

    @Autowired
    AccountService userService;


    /**
     * Fetches all the users from our list
     * @return all users in the list
     **/
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAllUser() {
        System.out.println("get all users");
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
     * @param updatedUserJSON the updated information
     * @return the updated user
     **/
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
//    public ResponseEntity<Account> updateUser(@RequestBody Account updatedAccount) {
    public ResponseEntity<Account> updateUser(@RequestBody Map<String, Object> updatedUserJSON) {
//        Map<String, Object> updatedUserJSON
        Account updatedAccount = new Account();
//        private String id;
//        private String username;
//        private String password;
//        private String firstName;
//        private String lastName;
//        private RfidKey rfidKey;
//
//        private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        updatedAccount.setId(updatedUserJSON.get("id").toString());
        updatedAccount.setUsername(updatedUserJSON.get("username").toString());
        updatedAccount.setPassword(updatedUserJSON.get("password").toString());
        updatedAccount.setFirstName(updatedUserJSON.get("firstName").toString());
        updatedAccount.setLastName(updatedUserJSON.get("lastName").toString());

	    if(updatedUserJSON.get("rfidKey") != null)
            updatedAccount.setRfidKey(new RfidKey(updatedUserJSON.get("rfidKey").toString()));
//        updatedAccount.setAuthorities((List<GrantedAuthority>) updatedUserJSON.get("authorities"));
//	    List < GrantedAuthority > aa = (List<GrantedAuthority>) updatedUserJSON.get("authorities");
	    // TODO Fix so that you can update authroities
	    List<String> auth = (List<String>) updatedUserJSON.get("authorities");
        System.out.println(auth);
        return userService.updateUser(updatedAccount);
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
        return userService.addUser(newAccount);

    }
}

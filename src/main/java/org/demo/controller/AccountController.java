package org.demo.controller;

import org.demo.model.RfidKey;
import org.demo.model.security.Account;
import org.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
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
    public ResponseEntity<Account> updateUser(@RequestBody Account updatedAccount) {
//    public ResponseEntity<Account> updateUser(@RequestBody Map<String, Object> updatedUserJSON) {
//        Map<String, Object> updatedUserJSON
//        Account updatedAccount = new Account();

//        updatedAccount.setId(updatedUserJSON.get("id").toString());
//        updatedAccount.setUsername(updatedUserJSON.get("username").toString());
//        updatedAccount.setPassword(updatedUserJSON.get("password").toString());
//        updatedAccount.setFirstName(updatedUserJSON.get("firstName").toString());
//        updatedAccount.setLastName(updatedUserJSON.get("lastName").toString());
//
//	    // TODO: 2016-05-02 :21:32 Make better!
//	    if(updatedUserJSON.get("rfidKey") != null) {
//		    LinkedHashMap<String, Object>  obj = (LinkedHashMap<String, Object>) updatedUserJSON.get("rfidKey");
//		    RfidKey key = new RfidKey(obj.get("id").toString());
//		    if (obj.get("enabled") != null) {
//			    System.out.println((Boolean) obj.get("enabled"));
//			    key.setEnabled((Boolean) obj.get("enabled"));
//		    }
//		    updatedAccount.setRfidKey(key);
//	    }
////        updatedAccount.setAuthorities((List<GrantedAuthority>) updatedUserJSON.get("authorities"));
////	    List < GrantedAuthority > aa = (List<GrantedAuthority>) updatedUserJSON.get("authorities");
//	    // TODO Fix so that you can update authroities
//	    List<LinkedHashMap<String,String>> auth = (List<LinkedHashMap<String,String>>) updatedUserJSON.get("authorities");
//	    List<GrantedAuthority> auths = updatedAccount.getAuthorities();
//	    auths.clear();
//	    auth.forEach(stringStringLinkedHashMap -> {
//		    auths.add(new SimpleGrantedAuthority(stringStringLinkedHashMap.get("authority")));
//	    });
//	    updatedAccount.setAuthorities(auths);
	    System.out.println("updateing user "+updatedAccount);

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
	    System.out.println("add user "+newAccount);
	    return userService.addUser(newAccount);

    }
}

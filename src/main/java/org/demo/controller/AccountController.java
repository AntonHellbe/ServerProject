package org.demo.controller;

import org.demo.model.RfidKey;
import org.demo.model.security.Account;
import org.demo.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
     * @param updatedUserJSON the updated information
     * @return the updated user
     **/
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Account> updateUser(@RequestBody Account updatedAccount) {
	    log.info("updateing user "+updatedAccount);

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
	    log.info("add user "+newAccount);
	    return userService.addUser(newAccount);

    }
}

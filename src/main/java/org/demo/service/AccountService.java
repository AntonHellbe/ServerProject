package org.demo.service;

import org.demo.model.security.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Anton on 2016-04-11.
 */

/**
 * Interface for methods used by the server to handle Users
 **/
public interface AccountService {

    /**
     *Fetches all the users in the list
     * @return all users in the list
     **/
    ResponseEntity<List<Account>> getAllUser();

    /**
     *Fetches the specified user from the list
     * @param id  id of the user we are after
     * @return the sought after user
     **/
    ResponseEntity<Account> getUser(@PathVariable("id") String id);

    /**
     * Updates an existing user with new information
     * @return the Updated user
     **/
    ResponseEntity<Account> updateUser(@RequestBody Account updatedAccount);

    /**
     * Removes the givien user from the list
     * @param id the id of the user to be removed
     * @return the deleted user
     **/
    ResponseEntity<Account> removeUser(@PathVariable("id") String id);

    /**
     * Adds a new user to the list
     * @return the new User
     **/
    ResponseEntity<Account> addUser(@RequestBody Account newAccount);



}

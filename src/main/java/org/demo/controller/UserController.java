package org.demo.controller;

import org.demo.model.User;
import org.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    /**
     * Fetches all the users from our list
     * @return all users in the list
     **/
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser() {
        return userService.getAllUser();
    }

    /**
     * Fetches a specific user from our list
     * @param id the id of the user we are searching for
     * @return The sought after user
     **/
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        return userService.getUser(id);
    }

    /**
     * Updates a user with new information
     * @param id the id of the user to be updated
     * @param updatedUserJSON the updated information
     * @return the updated user
     **/
    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id,
                                           @RequestBody Map<String, Object> updatedUserJSON) {
        return userService.updateUser(id, updatedUserJSON);
    }

    /**
     * Deletes the user associated with the given id
     * @param id the id of the user to be deleted
     * @return the deleted user
     **/
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<User> removeUser(@PathVariable("id") String id) {
        return userService.removeUser(id);

    }

    /**
     * Adds a new user
     * @param newUserJSON Information about the new user
     * @return the newly added user
     **/
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody Map<String, Object> newUserJSON) {
        return userService.addUser(newUserJSON);

    }
}

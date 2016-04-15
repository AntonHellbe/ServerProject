package org.demo.service;
import org.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;



import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Anton on 2016-04-11.
 */

/**
 * Interface for methods used by the server to handle Users
 **/
public interface UserService {

    /**
     *Fetches all the users in the list
     * @return all users in the list
     **/
    public ResponseEntity<ArrayList<User>> getAllUsers();

    /**
     *Fetches the specified user from the list
     * @param id  id of the user we are after
     * @return the sought after user
     **/
    public ResponseEntity<User> getUser(@PathVariable("id") String id);

    /**
     * Updates an existing user with new information
     * @param id id of the user to be updated
     * @param updatedUserJSON information to be applied to the user
     * @return the Updated user
     **/
    public ResponseEntity<User> updateUser(@PathVariable("id") String id,
                           @RequestBody Map<String, Object> updatedUserJSON);

    /**
     * Removes the givien user from the list
     * @param id the id of the user to be removed
     * @return the deleted user
     **/
    public ResponseEntity<User> removeUser(@PathVariable("id") String id);

    /**
     * Adds a new user to the list
     * @param newUserJSON information about the new user
     * @return the new User
     **/
    public ResponseEntity<User> addUser(@RequestBody Map<String, Object> newUserJSON);


}

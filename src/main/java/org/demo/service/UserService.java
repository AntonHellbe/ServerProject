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

public interface UserService {

    public ResponseEntity<ArrayList<User>> getAllUser();

    public ResponseEntity<User> getUser(@PathVariable("id") String id);

    public ResponseEntity<User> updateUser(@PathVariable("id") String id,
                           @RequestBody Map<String, Object> updatedUserJSON);

    public ResponseEntity<User> removeUser(@PathVariable("id") String id);

    public ResponseEntity<User> addUser(@RequestBody Map<String, Object> newUserJSON);


}

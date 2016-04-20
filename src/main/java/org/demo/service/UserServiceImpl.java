package org.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.demo.model.RfidKey;
import org.demo.model.User;
import org.demo.repository.ListRepository;
import org.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.*;

/**
 * Created by Anton on 2016-04-11.
 */
/**
 * Contains methods that enables the use and handling of users in the server
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;



    public ResponseEntity<List<User>> getAllUsers() {
        Map<String, Object> response = new LinkedHashMap<>();

        List<User> userList = userRepository.findAll();

        userList.forEach(user -> {
            //userRepository.insert(user);
            System.out.println(user.toString());
        });

        //List<RfidKey> dildo = userRepository.findAllRfidKeys();
        response.put("totalTimestamps", userList.size());
        response.put("Users", userList);
        if(userList != null) {
            return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        System.out.println("getUser with id" + id);
        User gotUser = userRepository.findOne(id);
        System.out.println("Got user: " + gotUser.toString());
        System.out.println("Hämtar följande användare från databas" + gotUser.toString());
        if(gotUser != null) {
            return new ResponseEntity<User>(gotUser, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<User> updateUser(@PathVariable("id") String id,
                           @RequestBody Map<String, Object> updatedUserJSON) {

        System.out.println("Update user: " + id);

        updatedUserJSON.forEach((key, obj) -> {
            System.out.println("KEY: " + key);
            System.out.println("VALUE: " + obj.toString());
        });

              User currentUser = userRepository.findOne(id);

        if (updatedUserJSON.get("firstName") != null) {
            System.out.println("Username updated");
            currentUser.setFirstName(updatedUserJSON.get("firstName").toString());
        }

        if (updatedUserJSON.get("lastName") != null) {
            System.out.println("Lastname updated");
            currentUser.setLastName(updatedUserJSON.get("lastName").toString());
        }
        if (updatedUserJSON.get("rfid") != null) {
            System.out.println("RFID updated");
            Map<String,String> rfid = (Map<String,String>) updatedUserJSON.get("rfid");
            currentUser.setRfid(new RfidKey(rfid.get("id")));
        }

        userRepository.save(currentUser);

        if(currentUser != null) {
            return new ResponseEntity<User>(currentUser, HttpStatus.OK);
        }else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<User> removeUser(@PathVariable("id") String id) {
        System.out.println("Remove following user" + id);

        User userToRemove = userRepository.findOne(id);

        System.out.println("Removing following user" + userToRemove.getFirstName());

        // TODO: 2016-04-09 :23:36 fixed so that remove works
        userRepository.delete(userToRemove.getId());
        if(userToRemove != null) {
            return new ResponseEntity<User>(userToRemove, HttpStatus.OK);
        }else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }


    }

    public ResponseEntity<User> addUser(@RequestBody Map<String, Object> newUserJSON) {

        // TODO: 2016-04-09 :23:34 Hitta bug, la till alltid 10 som id andra till size
        System.out.println(newUserJSON.get("firstName").toString());
        System.out.println(newUserJSON.get("lastName").toString());
        System.out.println(newUserJSON.get("rfid").toString());

        String firstName = newUserJSON.get("firstName").toString();
        String lastName = newUserJSON.get("lastName").toString();
        RfidKey newKey = new RfidKey(newUserJSON.get("rfid").toString());
        User newUser = new User(firstName,lastName,newKey);


        userRepository.insert(newUser);

        return new ResponseEntity<User>(newUser, HttpStatus.OK);
    }

}

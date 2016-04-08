package org.demo.controller;

import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Anton on 2016-04-07.
 */

@RestController
@RequestMapping("/users")
public class UserServiceController {


    private HashMap<RfidKey, User> userMap = new HashMap<>();
    private HashMap<String, ArrayList<TimeStamp>> timeStampMap = new HashMap<>();

    public UserServiceController() {
        MemberService test = new MemberService();
        try {
            userMap = test.loadMember();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<User> getAllUser() {
        System.out.println("Get all users");
        Map<String, Object> response = new LinkedHashMap<>();
        ArrayList<User> userList = new ArrayList<>(userMap.values());
        response.put("totalTimestamps", userList.size());
        response.put("Users", userList);
        return userList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public User getUser(@PathVariable("id") String id) {
        System.out.println("getUser with id" + id);
        User gotUser = userMap.get(new RfidKey(id));

        return gotUser;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public User updateUser(@PathVariable("id") String id,
                           @RequestBody Map<String, Object> updatedUserJSON) {

        System.out.println("Update user: " + id);

            updatedUserJSON.forEach((key, obj) -> {
	        System.out.println("KEY: "+key);
			System.out.println("VALUE: "+obj.toString());
		});

        User currentUser = userMap.get(new RfidKey(id));

        if(updatedUserJSON.get("firstName") != null) {
            System.out.println("Username updated");
            currentUser.setFirstName(updatedUserJSON.get("firstName").toString());
        }

        if(updatedUserJSON.get("lastName") != null) {
            System.out.println("Lastname updated");
            currentUser.setLastName(updatedUserJSON.get("lastName").toString());
        }
        if(updatedUserJSON.get("rfid") != null) {
            System.out.println("RFID updated");
            currentUser.setRfid(new RfidKey(updatedUserJSON.get("rfid").toString()));
        }

        User updatedUser = userMap.replace(currentUser.getRfid(), currentUser);

        return updatedUser;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public User removeUser(@PathVariable("id") String id){
        System.out.println("Remove following user" + id);

        User userToRemove = userMap.get(new RfidKey(id));

        System.out.println("Removing following user" + userToRemove.getFirstName());

        userToRemove = userMap.remove(userToRemove.getRfid());
        //Removes the user??
        //userRepository.delete(userToRemove);
        return userToRemove;

    }


    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    public User addUser(@RequestBody Map<String, Object> newUserJSON){

        User newUser = new User(newUserJSON.get("firstName").toString(), newUserJSON.get("lastName").toString(), new RfidKey(newUserJSON.get("rfid").toString()), "10");

        userMap.put(newUser.getRfid(), newUser);
        //Saves a user in mongoDb?
        //userRepository.save(newUser);

        return newUser;
    }




//    @RequestMapping(method = RequestMethod.GET, value = "/{id}/")
//    public ArrayList<TimeStamp> getAllTimeStamps(@PathVariable("id") String id) {
//        RfidKey rfidKey = new RfidKey(id);
//        ArrayList<TimeStamp> allTimes = new ArrayList<>();
//        ArrayList<TimeStamp> userStamps = timeStampMap.get(rfidKey);
//        userStamps.forEach(timeStamp -> {
//            allTimes.add(new TimeStamp(timeStamp.getDate(), timeStamp.getCheckIn(), timeStamp.getRfidkey()));
//
//        });
//        allTimes.forEach(timeStamp -> {
//            System.out.println(timeStamp);
//        });
//        return allTimes;
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/{id}/{stampId}")
//    public TimeStamp getTimeStamps(@PathVariable("id") String id, @PathVariable("stampId") int stampID) {
//        RfidKey rfidKey = new RfidKey(id);
//        ArrayList<TimeStamp> allTimes = new ArrayList<>();
//        ArrayList<TimeStamp> userStamps = timeStampMap.get(rfidKey);
//        System.out.println(userStamps.get(stampID));
//        return userStamps.get(stampID);
//    }






}

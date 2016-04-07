package org.demo.controller;

import org.demo.model.AndroidStamp;
import org.demo.model.RfidKey;
import org.demo.model.TimeSamples;
import org.demo.model.User;
import org.demo.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Anton on 2016-04-07.
 */

@RestController
@RequestMapping("/android")
public class AndroidServiceController {

    private ArrayList<TimeSamples> timeStamps = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();
    //stringrfid key , User value
    private HashMap<String, User> userMap = new HashMap<>();

    public AndroidServiceController(){
        MemberService test = new MemberService();
        try {
            userMap = test.loadMember();
            userList = test.getUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *Method that recives an id/username and returns the user it belongs too
     * @param id the id/username of the sought after user
     * @return The user we searched for
     **/
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable("id") String id){
        User wantedUser = this.userMap.get(id);
        return wantedUser;
    }

    /**
     * Used when you want to get all the times of a given user
     * @param rfidKey the rfid-key that is used to find the times created with the rfid-key
     * @return all the times associated with the rfid-key
     **/
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ArrayList<AndroidStamp> getAll(@RequestBody RfidKey rfidKey){
        ArrayList<AndroidStamp> allTimes = new ArrayList<>();
        User currentUser = userMap.get(rfidKey);
        return allTimes;
    }

    /**
     * Used when you want to get all the times of a given user
     * @param rfidKey the rfid-key that is used to find the times created with the rfid-key
     * @param from from what time we wanna search
     * @param too too what time we wanna search
     * @return the times associated with the rfid-key and in the period given
     **/
    public ArrayList<AndroidStamp> getBetween(@RequestBody RfidKey rfidKey, String from, String too){
        ArrayList<AndroidStamp> betweenTimes = new ArrayList<>();
        User currentUser = userMap.get(rfidKey);
        return betweenTimes;
    }
}

package org.demo.controller;

import org.demo.model.*;
import org.demo.repository.ListRepository;
import org.demo.service.AndroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Anton on 2016-04-07.
 *
 * Class that acts as the controller for Android methods and other classes, contains the methods that
 * the android clients make use of.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/android")
public class AndroidController {

	@Autowired
	AndroidService androidService;

    /**
     *Method that recives an id/username and returns the user it belongs too
     * @param id the id/username of the sought after user
     * @return The user we searched for
     **/
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") String id){
		return androidService.getUser(id);

    }

    /**
     * Used when you want to get all the times of a given user
     * @param rfidkeyJSON JSON object containing the RFID-key of the user
     * @return all the times associated with the rfid-key
     **/
	@RequestMapping(value = "/all",method = RequestMethod.POST)
	public ResponseEntity<ArrayList<AndroidStamp>> getAll(@RequestBody Map<String, Object> rfidkeyJSON){
		return androidService.getAll(rfidkeyJSON);
	}



    /**
     *Method used when the user wants to fetch all logged times between 2 dates
     * @param betweenJSON JSON object containing th RFID-key of the user, aswell as the from and to date.
     * @return the times associated with the RFID-key in the periods given
     **/
    @RequestMapping(value = "/between", method = RequestMethod.POST)
    public ResponseEntity<ArrayList<AndroidStamp>> getBetween(@RequestBody Map<String, Object> betweenJSON){
		return androidService.getBetween(betweenJSON);

    }
}

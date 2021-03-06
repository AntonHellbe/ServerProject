package org.demo.controller;

import org.demo.model.TimeStamp;
import org.demo.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by Robin_2 on 07/04/2016.
 */

/**
 * Class that creates and handles the TimeStamps that are assigned to users
 * @author Robin Johnsson, Sebastian Börebäck, Anton Hellbe
 **/
@CrossOrigin(maxAge = 1)
@RestController
@RequestMapping("/api/time")
public class TimeController {

    @Autowired
    TimeService timeService;

    /**
     * Controller that loads users from a Text file, just used until we get the database working
     **/
    public TimeController() {
    }
    /**
     * Updates the given time of a specific user
     *
     * @param id              the user
     * @param stampId         the id of the time to be changed
     * @param updateStamp     JSON of the updated object
     * @return updated time
     **/
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/{stampId}")
    public ResponseEntity<TimeStamp> updateTime(@PathVariable("id") String id, @PathVariable("stampId") String stampId,
                                                @RequestBody TimeStamp updateStamp) {
        return timeService.updateTime(id, stampId, updateStamp);
    }

    /**
     * Fetches the given time of a specific user
     *
     * @param id      the user
     * @param stampId the id of the time to be fetched
     * @return the time we searched for
     **/
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/{stampId}")
    public ResponseEntity<TimeStamp> getTime(@PathVariable("id") String id, @PathVariable("stampId") String stampId) {
        return timeService.getTime(id, stampId);
    }

    /**
     * Fetches all the times assigned to the user
     *
     * @param id the user
     * @return all the times for the user
     **/
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ArrayList<TimeStamp>> getAll(@PathVariable("id") String id) {
	    System.out.println("Calling get all!!");
	    return timeService.getAll(id);
    }

    /**
     *Adds a new TimeStamp to the user
     * @param id the id of the user
     * @return the added TimeStamp
     **/
	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	public ResponseEntity<TimeStamp> addNowTime(@PathVariable("id") String id) {
		return timeService.addNowTime(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/{isCustom}")
	public ResponseEntity<TimeStamp> addTime(@PathVariable("id") String id, @RequestBody TimeStamp newStamp, @PathVariable("isCustom") boolean isCustom) {
		if (isCustom) {
			return timeService.addTime(id,newStamp);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

    /**
     * Deletes the specified TimeStamp from the given user
     * @param id id of the user
     * @param stampId the TimeStamp to be deleted
     * @return the deleted time
     **/
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}/{stampId}")
	public ResponseEntity<TimeStamp> removeTime(@PathVariable("id") String id, @PathVariable("stampId") String stampId) {
		return timeService.deleteTime(id,stampId);
	}

}

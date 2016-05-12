package org.demo.controller;

import org.demo.model.AndroidBetweenQuery;
import org.demo.model.AndroidStamp;
import org.demo.model.RfidKey;
import org.demo.service.AndroidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 2016-04-07.
 *
 * Class that acts as the controller for Android methods and other classes, contains the methods that
 * the android clients make use of.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/android")
public class AndroidController {

	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(AndroidController.class);

	@Autowired
	AndroidService androidService;

    /**
     *Method used when the user wants to fetch all logged times between 2 dates
     * @return the times associated with the RFID-key in the periods given
     **/
    @RequestMapping(value = "/between&from={from}&to={to}&rfid={id}", method = RequestMethod.GET)
    public ResponseEntity<List<AndroidStamp>> getBetween(@PathVariable ("from") long from, @PathVariable("to") long to, @PathVariable("id") String id){
	    log.info("Calling get between");

		return androidService.getBetween(new AndroidBetweenQuery(from, to, new RfidKey(id)));

    }
}

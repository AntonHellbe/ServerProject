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

//    /**
//     *Method that recives an id/username and returns the user it belongs too
//     * @param id the id/username of the sought after user
//     * @return The user we searched for
//     **/
//    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
//    public ResponseEntity<Account> getUser(@PathVariable("id") String id){
//		return androidService.getUser(id);
//
//    }

//    /**
//     * Used when you want to get all the times of a given user
//     * @param rfidkeyJSON JSON object containing the RFID-key of the user
//     * @return all the times associated with the rfid-key
//     **/
//	@RequestMapping(value = "/all",method = RequestMethod.POST)
////	public ResponseEntity<List<AndroidStamp>> getAll(@RequestBody Map<String, Object> rfidkeyJSON){
//	public ResponseEntity<List<AndroidStamp>> getAll(@RequestBody RfidKey rfidkeyJSON){
//		log.info("Calling get all");
//		return androidService.getAll(rfidkeyJSON);
//	}

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity<Account> loginUser(@RequestBody Map<String, Object> getSpecificUserJSON) {
//        return androidService.loginUser(getSpecificUserJSON);
//    }


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

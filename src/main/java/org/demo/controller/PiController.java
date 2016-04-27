package org.demo.controller;

import org.demo.model.PiStamp;
import org.demo.model.RfidKey;
import org.demo.service.PiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by seb on 2016-04-07.
 *
 * Controller class for RaspberryPi methods and other classes, contains methods that the Pi-client makes use of.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pi")
public class PiController {


	@Autowired
	private PiService piService;


	/***
	 * Adds a new timestamp to the sent in RFID-key
	 * @param rfidKey belonging to the user to be given a new timestamp
	 * @return the name of the user, aswell as the timestamp
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<PiStamp> timeStampUser(@PathVariable("id") RfidKey rfidKey) {
		System.out.println("creating new Pistamp for id "+rfidKey.getId());
		return piService.addNewStamp(rfidKey);

	}

}

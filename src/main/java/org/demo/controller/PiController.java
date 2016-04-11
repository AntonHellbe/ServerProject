package org.demo.controller;

import org.demo.model.PiStamp;
import org.demo.model.RfidKey;
import org.demo.service.PiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by seb on 2016-04-07.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pi")
public class PiController {


	@Autowired
	private PiService piService;


	/***
	 * Handle rest request from client
	 * @param rfidKey
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<PiStamp> timeStampUser(@PathVariable("id") RfidKey rfidKey) {
		System.out.println("creating new Pistamp for id "+rfidKey.getId());
		return piService.addNewStamp(rfidKey);

	}

}

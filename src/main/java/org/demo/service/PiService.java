package org.demo.service;

import org.demo.model.PiStamp;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by seb on 2016-04-11.
 */


public interface PiService {

	/**
	 * Handles adding new stamp from Pi
	 * @return
	 */
	public ResponseEntity<PiStamp> addNewStamp(RfidKey rfidKey);

}

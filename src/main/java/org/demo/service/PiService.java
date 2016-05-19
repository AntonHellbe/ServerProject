package org.demo.service;

import org.demo.model.PiStamp;
import org.demo.model.RfidKey;
import org.springframework.http.ResponseEntity;

/**
 * Created by seb on 2016-04-11.
 */

/**
 * Interface for methods used by the RaspberryPi
 **/
public interface PiService {

	/**
	 * Handle the request from the controller
	 * @param rfidKey the new RFID of the user
	 * @return a ResponseEntity of PiStamp with with userinfo and timestamp-info
	 */
	ResponseEntity<PiStamp> addNewStamp(RfidKey rfidKey);

	ResponseEntity<PiStamp> getTime();

}

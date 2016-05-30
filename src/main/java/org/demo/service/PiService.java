package org.demo.service;

import org.demo.model.PiStamp;
import org.demo.model.RfidKey;
import org.springframework.http.ResponseEntity;

/**
 * @autor Sebastian Börebäck, Anton Hellbe on 2016-04-11.
 * Interface for methods used by the RaspberryPi
 **/
public interface PiService {

	/**
	 * Handle the request from the controller
	 * @param rfidKey the new RFID of the user
	 * @return a ResponseEntity of PiStamp with with userinfo and timestamp-info
	 */
	ResponseEntity<PiStamp> addNewStamp(RfidKey rfidKey);

	/**
	 * Method returns the current time from the server, used to set the clock on the pi
	 * @return PiStamp containing current time on server
     */
	ResponseEntity<PiStamp> getTime();

}

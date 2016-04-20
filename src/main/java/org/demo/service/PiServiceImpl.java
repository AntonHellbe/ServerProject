package org.demo.service;

import org.demo.model.PiStamp;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.repository.ListRepository;
import org.demo.repository.TimeRepository;
import org.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by seb on 2016-04-11.
 */
/**
 * Class that handles methods used by the RaspberryPi
 **/
@Service
public class PiServiceImpl implements PiService {

	/**
	 * Get the repository
	 */

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TimeRepository timeRepository;

	/**
	 * Handle the request from the controller
	 * @param rfidKey the new RFID of the user
	 * @return a ResponseEntity of PiStamp with with userinfo and timestamp-info
	 */
	@Override
	public ResponseEntity<PiStamp> addNewStamp(RfidKey rfidKey) {
		System.out.println("add timestamp from PI, on" + rfidKey.getId());

		try {

			User currentUser = userRepository.getUserByRfid(rfidKey);
			System.out.println(currentUser.toString());

			boolean state = timeRepository.getAllByRfid(rfidKey).size() % 2 == 0;
			TimeStamp newStamp = new TimeStamp(Calendar.getInstance(), state, currentUser.getRfid());
			if (state) {
				timeRepository.save(newStamp);
			} else {
				timeRepository.save(newStamp);
			}
			//newStamp;
			PiStamp piStamp = new PiStamp(newStamp.getCheckIn(), currentUser);
			//send the new Pistamp to client
			return new ResponseEntity<PiStamp>(piStamp, HttpStatus.OK);
		} catch (Exception e) {
			//if there was an error!
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}

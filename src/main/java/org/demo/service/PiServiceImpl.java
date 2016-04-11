package org.demo.service;

import org.demo.model.PiStamp;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by seb on 2016-04-11.
 */
@Service
public class PiServiceImpl implements PiService {

	/**
	 * Get the repository
	 */
	@Autowired
	ListRepository listRepository;

	/**
	 * Handle the request from the controller
	 * @param rfidKey the new RFID of the user
	 * @return a ResponseEntity of PiStamp with with userinfo and timestamp-info
	 */
	@Override
	public ResponseEntity<PiStamp> addNewStamp(RfidKey rfidKey) {
		System.out.println("add timestamp from PI, on" + rfidKey.getId());


		try {

			User currentUser = listRepository.getUserMap().get(rfidKey);
			System.out.println(currentUser.toString());

			ArrayList<TimeStamp> temp = listRepository.getTimeStampMap().get(currentUser.getRfid());
			boolean state = true;
			TimeStamp newStamp = new TimeStamp(Calendar.getInstance(), state, currentUser.getRfid());
			if (temp == null) {
				newStamp.setCheckIn(state);
				temp = new ArrayList<TimeStamp>();
				temp.add(newStamp);
				listRepository.getTimeStampMap().put(currentUser.getRfid(), temp);
			} else {
				state = listRepository.getTimeStampMap().get(currentUser.getRfid()).size() % 2 == 0;
				newStamp.setCheckIn(state);
				listRepository.getTimeStampMap().get(currentUser.getRfid()).add(newStamp);
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

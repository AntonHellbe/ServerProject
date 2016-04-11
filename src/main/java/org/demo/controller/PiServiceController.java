package org.demo.controller;

import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by seb on 2016-04-07.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pi")
public class PiServiceController {


	@Autowired
	private ListRepository listRepository;
	//private final HashMap<RfidKey, ArrayList<TimeStamp>> timeStampMap = new HashMap<>();
	//private HashMap<RfidKey, User> userMap;

	public PiServiceController() {


	}



	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public User timeStampUser(@PathVariable("id") RfidKey rfidKey) {


		User timeUser = this.listRepository.getUserMap().get(rfidKey);
		ArrayList<TimeStamp> userList = listRepository.getTimeStampMap().get(rfidKey);
		boolean state = false;
		if (userList.size() % 2 == 0) {

			userList.add(new TimeStamp(Calendar.getInstance(), state,rfidKey));
		}
		state = true;
		userList.add(new TimeStamp(Calendar.getInstance(), state, rfidKey));
		// TODO fixa sa att timestamps sparas po ratt stalle


		return timeUser;
	}
	// TODO: 2016-04-07 klar med rest f;r PI 1h

}

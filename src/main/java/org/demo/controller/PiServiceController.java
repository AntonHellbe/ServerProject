package org.demo.controller;

import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Created by seb on 2016-04-07.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pi")
public class PiServiceController {

	private final HashMap<RfidKey, ArrayList<TimeStamp>> timeStampMap = new HashMap<>();
	private HashMap<RfidKey, User> userMap;

	public PiServiceController() {

		MemberService test = new MemberService();
		try {
			userMap = test.loadMember();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Calendar from = new GregorianCalendar(2014, 1, 06, 10, 00);
		Calendar to = new GregorianCalendar(2014, 1, 06, 16, 00);

		ArrayList<TimeStamp> timeStamps = new ArrayList<>();

		timeStamps.add(new TimeStamp(from, true, new RfidKey("1")));
		timeStamps.add(new TimeStamp(to, false, new RfidKey("1")));

		timeStampMap.put(new RfidKey("1"), timeStamps);
		timeStampMap.put(new RfidKey("2"), timeStamps);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public User timeStampUser(@PathVariable("id") RfidKey rfidKey) {


		User timeUser = this.userMap.get(rfidKey);
		ArrayList<TimeStamp> userList = timeStampMap.get(rfidKey);
		boolean state = false;
		if (userList.size() % 2 == 0) {

			userList.add(new TimeStamp(Calendar.getInstance(), state,rfidKey));
		}
		state = true;
		userList.add(new TimeStamp(Calendar.getInstance(), state, rfidKey));

		return timeUser;
	}

}

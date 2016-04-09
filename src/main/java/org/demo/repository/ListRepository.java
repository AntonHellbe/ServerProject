package org.demo.repository;

import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.service.MemberService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Created by seb on 2016-04-08.
 */

@Component
public class ListRepository {


	private HashMap<RfidKey,User> userHashMap;
	private HashMap<RfidKey, ArrayList<TimeStamp>> timeStampMap = new HashMap<>();

	public ListRepository() {
		MemberService mfile = new MemberService();
		try {
			userHashMap = mfile.loadMember();
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

	public HashMap<RfidKey, ArrayList<TimeStamp>> getTimeStampMap() {
		return timeStampMap;
	}

	public void setTimeStampMap(HashMap<RfidKey, ArrayList<TimeStamp>> timeStampMap) {
		this.timeStampMap = timeStampMap;
	}

	public HashMap<RfidKey, User> getUserMap() {
		return userHashMap;
	}

	public void setUserMap(HashMap<RfidKey, User> userMap) {
		this.userHashMap = userMap;
	}
}

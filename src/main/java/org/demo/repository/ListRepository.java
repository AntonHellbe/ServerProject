package org.demo.repository;

import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.service.databaseservice.MemberService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Created by seb on 2016-04-08.
 */

/**
 * Class that handles the maps that contains information, used as the connection between the server and database
 **/
@Component
public class ListRepository {


	private HashMap<RfidKey,User> userHashMap;
	private HashMap<RfidKey, ArrayList<TimeStamp>> timeStampMap = new HashMap<>();

	/**
	 * Constructor that calls for a text-file to be read and from that creates a map
	 **/
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

//		timeStamps.add(new TimeStamp(from, true, new RfidKey("1")));
//		timeStamps.add(new TimeStamp(to, false, new RfidKey("1")));
//
//		timeStampMap.put(new RfidKey("1"), timeStamps);
//		timeStampMap.put(new RfidKey("2"), timeStamps);
	}

	/**
	 * Fetches the TimeStampMap, map with TimeStamps
	 * @return the map with TimeStamps
	 **/
	public HashMap<RfidKey, ArrayList<TimeStamp>> getTimeStampMap() {
		return timeStampMap;
	}

	/**
	 * Sets the TimeStampMap to the sent in value
	 * @param timeStampMap the map to be set
	 **/
	public void setTimeStampMap(HashMap<RfidKey, ArrayList<TimeStamp>> timeStampMap) {
		this.timeStampMap = timeStampMap;
	}

	/**
	 * Fetches the user map, map with users
	 * @return the user map
	 **/
	public HashMap<RfidKey, User> getUserMap() {
		return userHashMap;
	}

	/**
	 * Sets the userMap to the sent in value
	 * @param userMap the map to be set
	 **/
	public void setUserMap(HashMap<RfidKey, User> userMap) {
		this.userHashMap = userMap;
	}
}

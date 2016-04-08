package org.demo.repository;

import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.service.MemberService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
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
}

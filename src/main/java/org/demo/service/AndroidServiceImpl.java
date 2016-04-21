package org.demo.service;

import org.demo.model.*;
import org.demo.repository.TimeRepository;
import org.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by Anton on 2016-04-11.
 */

/**
 * Class that handles methods used by the Android clients
 **/

@Service
public class AndroidServiceImpl implements AndroidService {

	private static final Logger log = LoggerFactory.getLogger(AndroidServiceImpl.class);

	@Autowired
	TimeRepository timeRepository;

	@Autowired
	UserRepository userRepository;

	/**
	 * Fetches the user with the given RFID-key, the String gets converted into an RFID-key
	 *
	 * @param id the RFID-key
	 * @return the user
	 **/

	public ResponseEntity<User> getUser(String id) {
//		System.out.println("looking for RFID " + id);
//		User wantedUser = listRepository.getUserMap().get(new RfidKey(id));
//		System.out.println("found user: " + wantedUser);
//		if (wantedUser != null) {
//			return new ResponseEntity<User>(wantedUser, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//		}

		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<User> loginUser(Map<String, Object> getSpecificUserJSON) {
//		System.out.println(getSpecificUserJSON.get("firstName").toString());
//		System.out.println(getSpecificUserJSON.get("lastName").toString());
//		ArrayList<User> userList = new ArrayList<>(listRepository.getUserMap().values());
//		for (int i = 0; i < userList.size(); i++) {
//			if (userList.get(i).getFirstName().equals(getSpecificUserJSON.get("firstName").toString()) &&
//					userList.get(i).getLastName().equals(getSpecificUserJSON.get("lastName").toString())) {
//				User wantedUser = userList.get(i);
//				System.out.println(wantedUser.toString());
//				return new ResponseEntity<User>(wantedUser, HttpStatus.OK);
//			}
//		}
//
//
//		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}


	/**
	 * Fetches all times associated with the given user
	 *
	 * @param rfidKey The user with RFID sent in a JSON
	 * @return all the times
	 **/
	public ResponseEntity<List<AndroidStamp>> getAll(RfidKey rfidKey) {

		// TODO: 2016-04-21 :21:22 Just for logging
		log.info("Get all is called");
		log.info("get all stamps for id " + rfidKey);

		List<TimeStamp> userStamps = timeRepository.getByRfid(rfidKey);

		// TODO: 2016-04-21 :21:06 How to create new lists
		List<AndroidStamp> alist = userStamps.stream()
				.map(AndroidStamp::new)
				.collect(Collectors.toList());


		// TODO: 2016-04-21 :21:22 Just for logging
		log.info("sending to client");
		alist.forEach(item -> log.info(item.toString()));


		if (alist != null) {
			return new ResponseEntity<>(alist, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Fetches the times between the given times/dates
	 *
	 * @param androidBetweenQuery JSON containing the RFID of the user, the "from" date and the "to" date
	 * @return the times in the interval
	 **/
	public ResponseEntity<List<AndroidStamp>> getBetween(AndroidBetweenQuery androidBetweenQuery) {
		// TODO: 2016-04-21 :21:22 Just for logging
		log.info("Calling get between");

		log.info("got "+androidBetweenQuery.toString());

		Calendar fromDate = new GregorianCalendar();
		fromDate.setTimeInMillis(androidBetweenQuery.getFrom());
		Calendar toDate = new GregorianCalendar();
		toDate.setTimeInMillis(androidBetweenQuery.getTo());

		List<TimeStamp> userStamps = timeRepository.getByRfid(androidBetweenQuery.getId());

		// TODO: 2016-04-21 :21:06 How to create a filtered new lists
		List<AndroidStamp> betweenTimes = userStamps.stream()
				.filter(timeStamp ->
						timeStamp.getDate().after(fromDate) &&
						timeStamp.getDate().before(toDate))
				.map(AndroidStamp::new)
				.collect(Collectors.toList());

		if (betweenTimes != null) {
			return new ResponseEntity<>(betweenTimes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

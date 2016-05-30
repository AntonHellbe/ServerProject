package org.demo.service;

import org.demo.errorHandler.AndroidErrorHandler;
import org.demo.model.AndroidBetweenQuery;
import org.demo.model.AndroidStamp;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.demo.repository.ScheduleRepository;
import org.demo.repository.TimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by Anton on 2016-04-11.
 * @author Sebastian Börebäck, Robin Johnsson, Anton Hellbe
 */

/**
 * Class that handles methods used by the Android clients
 **/

@Service
public class AndroidServiceImpl implements AndroidService {

	private static final Logger log = LoggerFactory.getLogger(AndroidServiceImpl.class);

	@Autowired
	private AndroidErrorHandler androidErrorHandler;

	@Autowired
	TimeRepository timeRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ScheduleRepository scheduleRepository;

	@Override
	public ResponseEntity<Account> getUser(String id) {
		HttpStatus status = androidErrorHandler.getUserHandler(id);
		if (status!=HttpStatus.OK) {
			return new ResponseEntity<Account>(status);
		}
		Account wantedAccount = accountRepository.findOne(id);
		return new ResponseEntity<Account>(status);
	}

	@Override
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

	@Override
	public ResponseEntity<List<AndroidStamp>> getBetween(AndroidBetweenQuery androidBetweenQuery) {
		// TODO: 2016-04-21 :21:22 Just for logging
		log.info("Calling get between");

		log.info("got "+androidBetweenQuery.toString());

		List<TimeStamp> userStamps = timeRepository.getBetween(androidBetweenQuery);
		System.out.println(userStamps.size());

		List<AndroidStamp> betweenTimes = userStamps.stream()
				.map(AndroidStamp::new)
				.collect(Collectors.toList());

		return new ResponseEntity<List<AndroidStamp>>(betweenTimes, HttpStatus.OK);
	}

}

package org.demo.service;

import com.mongodb.WriteConcern;
import org.demo.errorHandler.TimeErrorHandler;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.demo.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Robin_2 on 11/04/2016.
 * @author Robin Johnsson, Sebastian Börebäck, Anton Hellbe
 */

/**
 * Contains methods that enables the use and handling of times in the server
 **/
@Service
public class TimeServiceImpl implements TimeService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TimeRepository timeRepository;

	@Autowired
	TimeErrorHandler timeErrorHandler;


	public TimeServiceImpl() {
	}

	@Override
	public ResponseEntity<TimeStamp> addNowTime(String id) {

		Account currentAccount = accountRepository.findOne(id);
		boolean state;
		TimeStamp got = timeRepository.stateCheck(currentAccount.getRfidKey()) ;
		if(got == null) {
			state = true;
		}else {
			state = !(got.getCheckIn());
		}
		TimeStamp newStamp = new TimeStamp(Calendar.getInstance().getTimeInMillis(), state, currentAccount.getRfidKey());

		timeRepository.save(newStamp);
		return new ResponseEntity<>(newStamp, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TimeStamp> addTime(String id, TimeStamp newStamp) {
		try {
			TimeStamp dildo = timeRepository.newStampCheck(newStamp.getRfidkey(), newStamp);
			if(dildo == null) {
				newStamp.setCheckIn(true);
			}else {
			newStamp.setCheckIn(!dildo.getCheckIn());
			}
			timeRepository.save(newStamp);
			return new ResponseEntity<>(newStamp, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<TimeStamp> deleteTime(String id, String stampId) {

		//HttpStatus statusOnRequest = timeErrorHandler.deleteTimeHandler(id, stampId);
		System.out.println("User to remove time from " + accountRepository.findOne(id).toString());
		if (stampId != null && timeRepository.findOne(stampId) != null) {
		//if(statusOnRequest == HttpStatus.OK) {
			TimeStamp removedTime = timeRepository.findOne(stampId);
			timeRepository.delete(stampId);
			return new ResponseEntity<TimeStamp>(removedTime, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@Override
	public ResponseEntity<ArrayList<TimeStamp>> getAll(String id) {
		HttpStatus statusOnRequest = timeErrorHandler.getAllHandler(id);
		if(statusOnRequest == HttpStatus.OK) {
		Account currentAccount = accountRepository.findOne(id);
		ArrayList<TimeStamp> userStamps = new ArrayList<>(timeRepository.getByRfid(currentAccount.getRfidKey()));
		for (int i = 0; i < userStamps.size() ; i++) {
			System.out.println(userStamps.get(i).toString());
		}
			return new ResponseEntity<>(userStamps, statusOnRequest);
		}

		return new ResponseEntity<>(statusOnRequest);
	}

	@Override
	public ResponseEntity<TimeStamp> getTime(String id, String stampId) {
		HttpStatus statusOnRequest = timeErrorHandler.getHandler(id,stampId);
		if(statusOnRequest == HttpStatus.OK) {
		TimeStamp timeToGet = timeRepository.findOne(stampId);
			System.out.println("Got following time " + timeToGet.toString());
			return new ResponseEntity<>(timeToGet, statusOnRequest);
		}

		return new ResponseEntity<TimeStamp>(statusOnRequest);
	}


	@Override
	public ResponseEntity<TimeStamp> updateTime(String id, String stampId, TimeStamp updateStamp) {
		HttpStatus statsOnRequest = timeErrorHandler.updateTimeHandler(updateStamp);
		if(statsOnRequest == HttpStatus.OK) {
			timeRepository.save(updateStamp);
			return new ResponseEntity<TimeStamp>(updateStamp, statsOnRequest);
		}

		return new ResponseEntity<>(statsOnRequest);

	}


}

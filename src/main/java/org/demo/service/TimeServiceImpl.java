package org.demo.service;

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
import java.util.Map;

/**
 * Created by Robin_2 on 11/04/2016.
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


	public TimeServiceImpl() {
	}

	@Override
	public ResponseEntity<TimeStamp> addNowTime(String id) {

		Account currentAccount = accountRepository.findOne(id);
		ArrayList<TimeStamp> temp = new ArrayList<>(timeRepository.getByRfid(currentAccount.getRfidKey()));
		boolean state = temp.size() % 2 == 0;

		TimeStamp newStamp = new TimeStamp(Calendar.getInstance(), state, currentAccount.getRfidKey());

		// TODO: 2016-05-10 :21:05 WAT!!!
//        if(state) {
//            timeRepository.save(newStamp);
//        }else {
//            timeRepository.save(newStamp);
//        }
		timeRepository.save(newStamp);
		return new ResponseEntity<>(newStamp, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TimeStamp> addTime(String id, TimeStamp newStamp) {
		try {
			timeRepository.save(newStamp);
			return new ResponseEntity<>(newStamp, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
//		return new ResponseEntity<>(newStamp, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TimeStamp> deleteTime(String id, String stampId) {

		System.out.println("User to remove time from " + accountRepository.findOne(id).toString());

		TimeStamp removedTime = timeRepository.findOne(stampId);

		if (removedTime != null) {
			timeRepository.delete(stampId);
		} else {
			return new ResponseEntity<TimeStamp>(HttpStatus.NOT_FOUND);
		}

		System.out.println("Deleted time: " + removedTime);
		return new ResponseEntity<TimeStamp>(removedTime, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ArrayList<TimeStamp>> getAll(String id) {
		Account currentAccount = accountRepository.findOne(id);
		ArrayList<TimeStamp> userStamps = new ArrayList<>(timeRepository.getByRfid(currentAccount.getRfidKey()));
		for (int i = 0; i < userStamps.size() ; i++) {
			System.out.println(userStamps.get(i).toString());
		}
		if (userStamps == null) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(userStamps, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TimeStamp> getTime(String id, String stampId) {

		TimeStamp timeToGet = timeRepository.findOne(stampId);
		if (timeToGet != null) {
			System.out.println("Got following time " + timeToGet.toString());
			return new ResponseEntity<>(timeToGet, HttpStatus.OK);
		}

		return new ResponseEntity<TimeStamp>(HttpStatus.NOT_FOUND);
	}


	@Override
	public ResponseEntity<TimeStamp> updateTime(String id, String stampId, TimeStamp updateStamp) {
		TimeStamp timeToUpdate = timeRepository.findOne(stampId);
		Calendar cal = new GregorianCalendar();
		if (updateStamp.getDate()!= null) {
			long date = updateStamp.getDate().getTimeInMillis();
			cal.setTimeInMillis(date);
			timeToUpdate.setDate(cal);
		}
			timeToUpdate.setCheckIn(updateStamp.getCheckIn());

		if (updateStamp.getRfidkey()!= null) {
			timeToUpdate.setRfidkey(new RfidKey(updateStamp.getRfidkey().toString()));
		}
		timeRepository.save(timeToUpdate);
		System.out.println("Updated time: " + timeToUpdate);
		return new ResponseEntity<>(timeToUpdate, HttpStatus.OK);
	}


}

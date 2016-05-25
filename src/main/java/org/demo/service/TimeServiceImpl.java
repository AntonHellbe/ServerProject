package org.demo.service;

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
 * @author Robin Johnsson, Sebastian Börebäck
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

		HttpStatus statusOnRequest = timeErrorHandler.deleteTimeHandler(id, stampId);
		System.out.println("User to remove time from " + accountRepository.findOne(id).toString());

		if(statusOnRequest == HttpStatus.OK) {
			TimeStamp removedTime = timeRepository.findOne(stampId);
			timeRepository.delete(stampId);
			return new ResponseEntity<TimeStamp>(removedTime, statusOnRequest);
		}

		return new ResponseEntity<>(statusOnRequest);

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
		TimeStamp timeToUpdate = timeRepository.findOne(stampId);
		Calendar cal = new GregorianCalendar();
		if (updateStamp.getDate() != 0) {
			long date = updateStamp.getDate();
			cal.setTimeInMillis(date);
			timeToUpdate.setDate(date);
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

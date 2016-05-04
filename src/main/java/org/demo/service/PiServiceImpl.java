package org.demo.service;

import org.demo.model.PiStamp;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.demo.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Created by seb on 2016-04-11.
 */
/**
 * Class that handles methods used by the RaspberryPi
 **/
@Service
public class PiServiceImpl implements PiService {

	/**
	 * Get the repository
	 */
	@Autowired
	TimeRepository timeRepository;

	@Autowired
	AccountRepository accountRepository;

	/**
	 * Handle the request from the controller
	 * @param rfidKey the new RFID of the user
	 * @return a ResponseEntity of PiStamp with with userinfo and timestamp-info
	 */
	@Override
	public ResponseEntity<PiStamp> addNewStamp(RfidKey rfidKey) {
		System.out.println("add timestamp from PI, on: " + rfidKey.getId());
		try {
			System.out.println(accountRepository.findUserByRfid(rfidKey));
			Account currentAccount = accountRepository.findUserByRfid(rfidKey);

			System.out.println("adding stamp on user> " + currentAccount);

			boolean state = timeRepository.getByRfid(rfidKey).size() % 2 == 0;
			TimeStamp newStamp = new TimeStamp(Calendar.getInstance(), state, currentAccount.getRfidKey());
			timeRepository.save(newStamp);
			System.out.println("Right before the return(good one)");
			return new ResponseEntity<>(new PiStamp(newStamp.getCheckIn(), currentAccount), HttpStatus.OK);
		}
		catch (Exception e) {
			//if there was an error!
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}

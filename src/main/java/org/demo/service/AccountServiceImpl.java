package org.demo.service;

import com.mongodb.DBPortPool;
import org.demo.config.UserNameGenerator;
import org.demo.model.RfidKey;
import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Anton on 2016-04-11.
 */

/**
 * Contains methods that enables the use and handling of users in the server
 **/
@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	UserNameGenerator userNameGenerator;

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


	public ResponseEntity<List<Account>> getAllUser() {
		Map<String, Object> response = new LinkedHashMap<>();
		List<Account> accountList = accountRepository.findAll();
		response.put("AllAccounts", accountList.size());
		response.put("Account", accountList);
		if (accountList != null) {
			return new ResponseEntity<>(accountList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Account> getUser(String id) {
		System.out.println("getUser with id" + id);
		Account gotAccount = accountRepository.findOne(id);
		if (gotAccount != null) {
			return new ResponseEntity<Account>(gotAccount, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	private ResponseEntity<Account> updateThisShit(Account updatedAccount){
		if (updatedAccount.getUsername().length()<4){
			log.info("username to short");
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
		accountRepository.save(updatedAccount);
		System.out.println("Account updated");
		return new ResponseEntity<Account>(updatedAccount, HttpStatus.OK);
	}

	public ResponseEntity<Account> updateUser(Account updatedAccount) {

		//Checks that there are things sent in
		if(updatedAccount != null) {
			Account temp = accountRepository.findOne(updatedAccount.getId());
			log.info("There is something sent in.");

			//Checks if the RFID of the update account is the same as the account we are trying to update
			if (temp.getRfidKey().equals(updatedAccount.getRfidKey())) {
				log.info("The RFID of the update is the same as the user to be updated (not changed).");

				//checks if the username is the same as before
				if(temp.getUsername().equals(updatedAccount.getUsername())){
					log.info("The username isnt changed from before.");
					return updateThisShit(updatedAccount);
				}

				if(accountRepository.findByUserName(updatedAccount.getUsername()) !=null){
					log.info("Username is somewhere else in the database!!!");
					System.out.println("Account NOT updated");
					return new ResponseEntity<>(HttpStatus.IM_USED);
				}

				log.info("The RFID is the same as before, but there is a new username that isn't anywhere else!");
				return updateThisShit(updatedAccount);

			}
			if (updatedAccount.getRfidKey().toString().length() != 0 && accountRepository.findUserByRfid(updatedAccount.getRfidKey()) != null) {
				log.info("RFID is somewhere in the database, already in use!!!");
				System.out.println("Account NOT updated");
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}

			//annat rfid

			//checks if the username is the same as before
			if(temp.getUsername().equals(updatedAccount.getUsername())){
				log.info("The RFID is new, but username the same.");
				return updateThisShit(updatedAccount);
			}

			System.out.println(accountRepository.findByUserName(updatedAccount.getUsername()));
			if(accountRepository.findByUserName(updatedAccount.getUsername()) !=null){
				log.info("The RFID is changed, but the new username is already taken!");
				System.out.println("Account NOT updated");
				return new ResponseEntity<>(HttpStatus.IM_USED);
			}

			log.info("The RFID is new but not taken, and the username is new but not taken!");
			System.out.println("account UPDATED!");
			accountRepository.save(updatedAccount);

//			//do ws update
//			WsAnswer wsanswer = new WsAnswer(AffectedArea.ACCOUNT, CrudType.UPDATE, updatedAccount.getId());
//			wsanswer.setPayload(updatedAccount);
//			wsCtrl.serverInformClients(wsanswer);

			return new ResponseEntity<Account>(updatedAccount, HttpStatus.OK);
		}
		log.info("You really messed something up here you know...");
		return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<Account> updatePassword(@RequestBody String newPass, @PathVariable("id") String id) {
		if (newPass != null) {
			Account updatedAccount = accountRepository.findOne(id);
			updatedAccount.setPassword(passwordEncoder.encode(newPass));
			accountRepository.save(updatedAccount);
			return new ResponseEntity<Account>(updatedAccount, HttpStatus.OK);
		} else {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Account> removeUser(String id) {
		System.out.println("Remove following user" + id);

		Account accountToRemove = accountRepository.findOne(id);

		System.out.println("Removing following user" + accountToRemove.getFirstName());

		accountRepository.delete(accountToRemove.getId());

		if (accountToRemove != null) {
			return new ResponseEntity<Account>(accountToRemove, HttpStatus.OK);
		} else {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}


	}

	public ResponseEntity<Account> addUser(Account newAccount) {

		/**
		 * Create username
		 */
		if (newAccount.getUsername() == null) {
			newAccount.setUsername(userNameGenerator.userNameGenerator(newAccount));
		}

		/**
		 * Encode password
		 */
		if (newAccount.getPassword() != null) {
			newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
		}

		accountRepository.save(newAccount);

		System.out.println("saving to db " + newAccount);
//		System.out.println("RFID: " + newAccount.getRfidKey().isEnabled());
		return new ResponseEntity<Account>(newAccount, HttpStatus.OK);
	}


}

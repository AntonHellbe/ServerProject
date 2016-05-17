package org.demo.service;

import org.demo.config.UserNameGenerator;
import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
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

	public ResponseEntity<Account> updateUser(Account updatedAccount) {

		if(updatedAccount != null) {
			Account temp = accountRepository.findOne(updatedAccount.getId());
//		if (temp.getRfidKey().equals(updatedAccount.getRfidKey()) ) {
//				accountRepository.save(updatedAccount);
//			}
			if (temp.getRfidKey().equals(updatedAccount.getRfidKey())) {
				accountRepository.save(updatedAccount);
				System.out.println("Account updated");
				return new ResponseEntity<Account>(updatedAccount, HttpStatus.OK);
			}
			if (accountRepository.findUserByRfid(updatedAccount.getRfidKey()) != null) {
				System.out.println("Account NOT updated");
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			System.out.println("account UPDATED!");
			accountRepository.save(updatedAccount);
			System.out.println("better");

//			//do ws update
//			WsAnswer wsanswer = new WsAnswer(AffectedArea.ACCOUNT, CrudType.UPDATE, updatedAccount.getId());
//			wsanswer.setPayload(updatedAccount);
//			wsCtrl.serverInformClients(wsanswer);

			return new ResponseEntity<Account>(updatedAccount, HttpStatus.OK);
		}
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

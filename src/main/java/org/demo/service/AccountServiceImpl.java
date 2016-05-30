package org.demo.service;

import org.demo.config.UserNameGenerator;
import org.demo.errorHandler.AccountErrorHandler;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains methods that enables the use and handling of users in the server
 * @author Robin Johnsson, Sebastian Börebäck, Anton Hellbe
 *
 **/

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountErrorHandler errorHandler;

	private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	UserNameGenerator userNameGenerator;

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


	public ResponseEntity<List<Account>> getAllUser() {
		log.info("Entering getAllUser");
		Map<String, Object> response = new LinkedHashMap<>();
		List<Account> accountList = accountRepository.findAll();
		response.put("AllAccounts", accountList.size());
		response.put("Account", accountList);

		//Calls our ErrorHandler and gets a response, either OK or something bad
		HttpStatus status = errorHandler.getAllHandler(accountList);
		if (status!=HttpStatus.OK){
			return new ResponseEntity<>(status);
		}else return new ResponseEntity<>(accountList, status);
	}

	public ResponseEntity<Account> getUser(String id) {
		log.info("Entering getUser");
		Account gotAccount = accountRepository.findOne(id);
		HttpStatus status = errorHandler.getUserHandler(gotAccount);
		if (status == HttpStatus.OK) {
			return new ResponseEntity<Account>(gotAccount, status);
		} else {
			return new ResponseEntity<>(status);
		}

	}

	public ResponseEntity<Account> updateUser(Account updatedAccount) {
		log.info("Entering updateUser");
		//Account temp = accountRepository.findOne(updatedAccount.getId());
		HttpStatus status = errorHandler.updateHandler(updatedAccount);
		if(status!=HttpStatus.OK){
			return new ResponseEntity<Account>(status);
		}else {
			if (!updatedAccount.getPassword().equals(accountRepository.findOne(updatedAccount.getId()).getPassword())) {
				updatedAccount.setPassword(passwordEncoder.encode(updatedAccount.getPassword()));
			}
			accountRepository.save(updatedAccount);
			return new ResponseEntity<Account>(updatedAccount, HttpStatus.OK);
		}
	}

	public Account passwordUpdater(String newPassword, String userId) throws Exception {
		log.info("Entering passwordUpdater");
		HttpStatus status = errorHandler.passwordUpdate(newPassword);
		if (status != HttpStatus.OK) {
			Account updatedAccount = accountRepository.findOne(userId);
			updatedAccount.setPassword(passwordEncoder.encode(newPassword));
			Account returnAccount=accountRepository.save(updatedAccount);
			return returnAccount;
		} else {
			throw new Exception("Failed to update password, due to new password is not set!");
		}
	}

	public ResponseEntity<Account> removeUser(String id) {
		log.info("Entering removeUser");
		Account accountToRemove = accountRepository.findOne(id);
		HttpStatus status = errorHandler.deleteHandler(accountToRemove);
		if(status!=HttpStatus.OK){
			return new ResponseEntity<Account>(status);
		}else
			accountRepository.delete(accountRepository.findOne(id));
		return new ResponseEntity<Account>(accountToRemove,status);
	}

	public ResponseEntity<Account> addUser(Account newAccount) {
		log.info("Entering addUser");
		HttpStatus status = errorHandler.addHandler(newAccount);
		if(status != HttpStatus.OK) {
			System.out.println("error?" + status);
			return new ResponseEntity<Account>(status);
		}
		if (newAccount.getUsername() == null) {
			newAccount.setUsername(userNameGenerator.userNameGenerator(newAccount));
		}
		if (newAccount.getPassword() != null) {
			newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
		}
		accountRepository.save(newAccount);
		return new ResponseEntity<Account>(newAccount, HttpStatus.OK);
	}
}
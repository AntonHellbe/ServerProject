package org.demo.service;

import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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



    public ResponseEntity<List<Account>> getAllUser() {
        Map<String, Object> response = new LinkedHashMap<>();
        List<Account> accountList = accountRepository.findAll();
//        userRepository.deleteAll();
//        userList.forEach(user -> {
//            userRepository.save(user);
//        });
        response.put("AllAccounts", accountList.size());
        response.put("Account", accountList);
        if(accountList != null) {
            return new ResponseEntity<List<Account>>(accountList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Account> getUser(String id) {
        System.out.println("getUser with id" + id);
        Account gotAccount = accountRepository.findOne(id);
        if(gotAccount != null) {
            return new ResponseEntity<Account>(gotAccount, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Account> updateUser(Account updatedAccount) {
        if(updatedAccount != null) {
            accountRepository.save(updatedAccount);
            return new ResponseEntity<Account>(updatedAccount, HttpStatus.OK);
        }else {
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Account> removeUser(String id) {
        System.out.println("Remove following user" + id);

        Account accountToRemove = accountRepository.findOne(id);

        System.out.println("Removing following user" + accountToRemove.getFirstName());

        // TODO: 2016-04-09 :23:36 fixed so that remove works


        accountRepository.delete(accountToRemove.getId());

        if(accountToRemove != null) {
            return new ResponseEntity<Account>(accountToRemove, HttpStatus.OK);
        }else {
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }


    }

    public ResponseEntity<Account> addUser(Account newAccount) {


	    accountRepository.save(newAccount);

	    System.out.println("saving to db "+newAccount);
        return new ResponseEntity<Account>(newAccount, HttpStatus.OK);
    }


}

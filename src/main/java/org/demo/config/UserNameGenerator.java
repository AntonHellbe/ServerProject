package org.demo.config;

import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Anton on 2016-04-27.
 */
public final class UserNameGenerator {

    @Autowired
    private AccountRepository accountRepository;

    public String userNameGenerator(Account account) {

        String firstName = account.getFirstName();
        String lastName = account.getLastName();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lastName.length() && i < 3; i++) {
            sb.append(firstName.charAt(i));
        }
        for (int i = 0; i < firstName.length() && i < 3; i++) {
            sb.append(firstName.charAt(i));
        }

        boolean exists = true;
        String userName = sb.toString() + "1";
        int n = 2;
        while (exists) {
            exists = accountRepository.findByUserName(userName) != null;
            if(exists)
                userName = userName.substring(0, userName.length() - 2) + n++; //Ifall det går över 10 fucker det
        }
        return userName;
    }
}

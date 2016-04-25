package org.demo.service.security;

import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by Sebastian Börebäck on 2016-04-22.
 */
@Component
public class MongoUserDetailsService implements UserDetailsService {


	@Autowired
	private AccountRepository accountRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if (account != null) {
			return account;
		} else {
			throw new UsernameNotFoundException("could not find the user '"
					+ username + "'");
		}
	}
}

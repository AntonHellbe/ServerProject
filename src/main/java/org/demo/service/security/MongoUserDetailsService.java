package org.demo.service.security;

import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(MongoUserDetailsService.class);

	@Autowired
	AccountRepository accountRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("doing auth db call");
		Account account = accountRepository.findByUserName(username);
		if (account != null) {
			return account;
		} else {
			throw new UsernameNotFoundException("could not find the user '"
					+ username + "'");
		}
	}
}

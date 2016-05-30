package org.demo.config;

import org.demo.repository.AccountRepository;
import org.demo.service.security.MongoUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Sebastian Börebäck on 2016-04-22.
 * @author Sebastian Börebäck
 * Connects the MongoDB with authentication.
 */
@Configuration
class WebSecurityAuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	MongoUserDetailsService mongoUserDetailsService;

	private static final Logger log = LoggerFactory.getLogger(WebSecurityAuthenticationConfig.class);

	/**
	 * Establishes the Spring boot authentication, so that it will use
	 * mongoUserDetailsService, which mean it will use MongoDB for looking up user that want to login
	 * @param auth authenticationMangerbuilder, build how the login authenticion will be handled.
	 * @throws Exception
	 */
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = new BCryptPasswordEncoder();

		log.info("doing Authenticaion call");
		auth.userDetailsService(mongoUserDetailsService).passwordEncoder(encoder);
	}

}
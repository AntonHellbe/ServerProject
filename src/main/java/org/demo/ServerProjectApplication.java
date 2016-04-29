package org.demo;


import org.demo.config.AuthoritiesConstants;
import org.demo.model.RfidKey;
import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class ServerProjectApplication implements CommandLineRunner{

	private String ip = "http://localhost:8080/users";
	private static final Logger log = LoggerFactory.getLogger(ServerProjectApplication.class);

	@Value("${management.security.role}")
	private String adminRole;

	@Autowired
	AccountRepository accountRepository;


	public static void main(String[] args) {
		SpringApplication.run(ServerProjectApplication.class, args);
	}




	@Override
	public void run(String... strings) throws Exception {

		// TODO: 2016-04-24 :17:24 here you can add new users or run other commands for upstart loadning data

		Account defaultUser = accountRepository.findByUserName("user");
		Account adminUser = accountRepository.findByUserName("admin");
		if (defaultUser == null) {
			System.out.println("CREATING DEFAULT USER");
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String pass = encoder.encode("pass");
			RfidKey test = new RfidKey("C48659EC");
			Account newAccount = new Account("Anton", "Hellbe","user", pass,
					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
					);
			accountRepository.save(newAccount);
		}
		else {
			System.out.println("USER ALLREADY in DB");
		}
		if (adminUser== null) {
			System.out.println("CREATING ADMIN USER");
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String pass = encoder.encode("pass");
			accountRepository.save(new Account("Master", "Swaggins", "admin", pass,
					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER,
							adminRole)));
		}
		else {
			System.out.println("USER ALLREADY in DB");
		}

//		Account wantedAccount = accountRepository.findByUserName("user");
//		wantedAccount.setEnabled(false);
//		accountRepository.save(wantedAccount);
//		List<Account> disabledUsers = accountRepository.findDisabledUsers("false");
//		for (int i = 0; i < disabledUsers.size(); i++) {
//			System.out.println(disabledUsers.get(i).toString());
//
//		}

//		wantedAccount.setEnabled(true);
//		accountRepository.save(wantedAccount);

//		//add some dummy users
//		userRepository.save(new User("john", "smith", new RfidKey("123")));
//		userRepository.save(new User("lisa", "smith", new RfidKey("234")));
//		userRepository.save(new User("clark", "smith", new RfidKey("4")));
//		userRepository.save(new User("erik", "smith", new RfidKey("5")));


	}
}

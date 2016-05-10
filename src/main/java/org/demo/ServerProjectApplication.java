package org.demo;


import org.demo.config.AuthoritiesConstants;
import org.demo.model.RfidKey;
import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.demo.repository.TimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ServerProjectApplication implements CommandLineRunner {

	private String ip = "http://localhost:8080/users";
	private static final Logger log = LoggerFactory.getLogger(ServerProjectApplication.class);


	@Autowired
	AccountRepository accountRepository;


	@Autowired
	private TimeRepository timeRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServerProjectApplication.class, args);
	}


	@Override
	public void run(String... strings) throws Exception {

		// TODO: 2016-04-24 :17:24 here you can add new users or run other commands for upstart loadning data

		Account defaultUser = accountRepository.findByUserName("user");
		Account adminUser = accountRepository.findByUserName("admin");
		Account piUser = accountRepository.findByUserName("piUser");

		if (defaultUser == null) {
			System.out.println("CREATING DEFAULT USER");
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String pass = encoder.encode("pass");
			RfidKey test = new RfidKey("C48659EC");
			defaultUser = new Account("Doris", "Popo", "user", pass,
					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
			);
			defaultUser.setRfidKey(test);
			accountRepository.save(defaultUser);
		} else {
			System.out.println("USER ALLREADY in DB");
		}
		if (adminUser == null) {
			System.out.println("CREATING ADMIN USER");
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String pass = encoder.encode("pass");
			adminUser = new Account("Master", "Swaggins", "admin", pass,
					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER,
							AuthoritiesConstants.PIUSER, AuthoritiesConstants.ADMIN));
			adminUser.setRfidKey(new RfidKey("34915AEC"));

			accountRepository.save(adminUser);
		} else {
			System.out.println("USER ALLREADY in DB");
		}
		if (piUser == null) {
			System.out.println("CREATING PIUSER USER");
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String pass = encoder.encode("pass");
			accountRepository.save(new Account("Pie", "Rubarb", "piUser", pass,
					AuthorityUtils.createAuthorityList(AuthoritiesConstants.PIUSER)));
		} else {
			System.out.println("USER ALLREADY in DB");
		}


	}

}

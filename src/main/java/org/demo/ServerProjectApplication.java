package org.demo;


import org.demo.config.AuthoritiesConstants;
import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
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
public class ServerProjectApplication implements CommandLineRunner{

	private String ip = "http://localhost:8080/users";
	private static final Logger log = LoggerFactory.getLogger(ServerProjectApplication.class);

	@Autowired
	AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServerProjectApplication.class, args);
	}




	@Override
	public void run(String... strings) throws Exception {

		// TODO: 2016-04-24 :17:24 here you can add new users or run other commands for upstart loadning data

		Account defaultUser = accountRepository.findByUsername("user");
		Account adminUser = accountRepository.findByUsername("admin");
		if (defaultUser == null) {
			System.out.println("CREATING DEFAULT USER");
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String pass = encoder.encode("pass");
			accountRepository.save(new Account("user", pass,
					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)));
		}
		else {
			System.out.println("USER ALLREADY in DB");
		}
		if (adminUser== null) {
			System.out.println("CREATING ADMIN USER");
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String pass = encoder.encode("pass");
			accountRepository.save(new Account("admin", pass,
					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER,
							AuthoritiesConstants.ADMIN)));
		}
		else {
			System.out.println("USER ALLREADY in DB");
		}
	}
}

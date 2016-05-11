package org.demo;


import org.demo.config.AuthoritiesConstants;
import org.demo.config.UserNameGenerator;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

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
		Account user2 = accountRepository.findByUserName("user2");
		Account user3 = accountRepository.findByUserName("user3");
		Account user4 = accountRepository.findByUserName("user4");

		if (defaultUser == null) {
			log.info("CREATING DEFAULT USER");
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String pass = encoder.encode("pass");
			RfidKey test = new RfidKey("C48659EC");
			defaultUser = new Account("Doris", "Popo", "user", pass,
					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
			);
			defaultUser.setRfidKey(test);
			accountRepository.save(defaultUser);
		} else {
			log.info("USER ALLREADY in DB");
		}
		if (adminUser == null) {
			log.info("CREATING ADMIN USER");
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String pass = encoder.encode("pass");
			adminUser = new Account("Darth", "Vader", "admin", pass,
					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER,
							AuthoritiesConstants.PIUSER, AuthoritiesConstants.ADMIN));
			adminUser.setRfidKey(new RfidKey("34915AEC"));

			accountRepository.save(adminUser);
		} else {
			log.info("USER ALLREADY in DB");
		}
		if (piUser == null) {
			log.info("CREATING PIUSER USER");
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String pass = encoder.encode("pass");
			accountRepository.save(new Account("Pie", "Rubarb", "piUser", pass,
					AuthorityUtils.createAuthorityList(AuthoritiesConstants.PIUSER)));
		} else {
			log.info("USER ALLREADY in DB");
		}
//		PasswordEncoder encoder = new BCryptPasswordEncoder();
//		String pass = encoder.encode("pass");
//		Account test = new Account("Robin", "Johnsson", pass);
//		accountRepository.save(test);
//
//		test.setUsername(userNameGenerator.userNameGenerator("Robin", "Johnsson"));
//		accountRepository.save(test);


//		////////////////////////////////////////////////////////////////////////
//		PasswordEncoder encoder = new BCryptPasswordEncoder();
//		if (user2 == null) {
//			user2 = new Account("Matt", "Murdock", "user2",
//					encoder.encode("pass"),
//					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
//			);
//			user2.setRfidKey(new RfidKey("A448182B"));
//			accountRepository.save(user2);
//
//		}else
//			log.info(user2.getFirstName()+" "+user2.getLastName()+ " IS ALLREADY IN DB");
//		////////////////////////////////////////////////////////////////////////
//		if (user3 == null) {
//			user3 = new Account("John", "Snow", "user3",
//					encoder.encode("pass"),
//					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
//			);
//			user3.setRfidKey(new RfidKey("4B79295"));
//			accountRepository.save(user3);
//		}else
//			log.info(user3.getFirstName()+" "+user3.getLastName()+ " IS ALLREADY IN DB");
//		////////////////////////////////////////////////////////////////////////
//		if (user4 == null) {
//			user4 = new Account("Lucas", "Hood", "user4",
//					encoder.encode("pass"),
//					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
//			);
//			user4.setRfidKey(new RfidKey("247615E"));
//
//			accountRepository.save(user4);
//		}else
//			log.info(user4.getFirstName()+" "+user4.getLastName()+ " IS ALLREADY IN DB");

//		ArrayList<TimeStamp> calsA = generateStamps(adminUser.getRfidKey());
//		ArrayList<TimeStamp> cals1 = generateStamps(defaultUser.getRfidKey());
//		ArrayList<TimeStamp> cals2 = generateStamps(user2.getRfidKey());
//		ArrayList<TimeStamp> cals3 = generateStamps(user3.getRfidKey());
//		ArrayList<TimeStamp> cals4 = generateStamps(user4.getRfidKey());
//		//clear stamps
//		timeRepository.deleteAll();
//		//add stamps
//		timeRepository.save(calsA);
//		timeRepository.save(cals1);
//		timeRepository.save(cals2);
//		timeRepository.save(cals3);
//		timeRepository.save(cals4);


		//		user;C48659EC;1
		//		admin;34915AEC;2
		//		user2;A448182B;3
		//		user3;4B79295;4
		//		user4;247615E;5
		//		Anna;Ek;1C699EB6;6
		//		Carsten;Panduro;8BA8A996;7


		//end of runner
//	}

//	private ArrayList<TimeStamp> generateStamps(RfidKey rfidKey) {
//		Random rnd = new Random();
//		ArrayList<TimeStamp> cals = new ArrayList<>();
//		GregorianCalendar inCal = new GregorianCalendar(), outCal = new GregorianCalendar();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//
//		int rand = rnd.nextInt(3);
//
//		for (int i = 0; i < 30; i++) {
//
//			rand = rnd.nextInt(3);
//			inCal = new GregorianCalendar(2016, 5, (1 + i), (6 + rand), 0);
//
////			log.info("cal " + sdf.format(inCal.getTime()));
//			cals.add(new TimeStamp(inCal, true, rfidKey));
//
//			rand = rnd.nextInt(3);
////			log.info("rand22 " + rand);
//			outCal = new GregorianCalendar(2016, 05, (1 + i), (13 + rand), 0);
//			cals.add(new TimeStamp(outCal, false, rfidKey));
//		}
//		log.info("rfid " + rfidKey);
//		return cals;
	}

}

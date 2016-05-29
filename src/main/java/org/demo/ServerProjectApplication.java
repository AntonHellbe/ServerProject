package org.demo;


import org.demo.config.AuthoritiesConstants;
import org.demo.model.RfidKey;
import org.demo.model.ScheduleStamp;
import org.demo.model.TimeStamp;
import org.demo.model.security.Account;
import org.demo.repository.AccountRepository;
import org.demo.repository.ScheduleRepository;
import org.demo.repository.TimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Sebastian Börebäck
 * Main class, create also initiale users etc.
 */
@SpringBootApplication
public class ServerProjectApplication implements CommandLineRunner {

	private String ip = "http://localhost:8080/users";
	private static final Logger log = LoggerFactory.getLogger(ServerProjectApplication.class);


	//TODO remove only test
	@Autowired
	private MongoOperations mongoOperations;


	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ScheduleRepository scheduleRepository;


	@Autowired
	private TimeRepository timeRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServerProjectApplication.class, args);
	}


	@Override
	public void run(String... strings) throws Exception {
		long date1 = Long.parseLong("1464339600000");
		long date2 = Long.parseLong("1464372000000");
		long date3 = Long.parseLong("1464447600000");
		long date4 = Long.parseLong("1464458400000");
		long date5 = Long.parseLong("1464508800000");
		long date6 = Long.parseLong("1464523200000");
		long date7 = Long.parseLong("1464526800000");
		long date8 = Long.parseLong("1464544800000");

		String[] userIds = {"57470ef0e4b02a31bebd06e8", "57470edbe4b02a31bebd06e7", "57470a82e4b02a31bebd06c1", "57470a9ae4b02a31bebd06c2", "57470b45e4b02a31bebd06c9"};
		//Clear DB
//		clearDB();
		ScheduleStamp tempStamp = new ScheduleStamp(date1, date2, userIds);
		ScheduleStamp tempStamp2 = new ScheduleStamp(date3, date4, userIds);
		ScheduleStamp tempStamp3 = new ScheduleStamp(date5, date6, userIds);
		ScheduleStamp tempStamp4 = new ScheduleStamp(date7, date8, userIds);
		scheduleRepository.save(tempStamp);
		scheduleRepository.save(tempStamp2);
		scheduleRepository.save(tempStamp3);
		scheduleRepository.save(tempStamp4);
//		//Check and create accounts
//		ArrayList<Account> accounts = createUsers();
//		//create timestamps and add to db
//		createTimestamps(accounts);
//		String ids[] = new String[1];
//		ids[0] = "574619afc1185c40bcd28587";
//
//		generateScheds(ids);

//		List<ScheduleStamp> stampList = scheduleRepository.getById("574619afc1185c40bcd28587");
//		for (int i = 0; i < stampList.size(); i++) {
//			System.out.println(stampList.get(i).toString());
//		}

//		end of runner
	}

//	private void createTimestamps(ArrayList<Account> accounts) {
//
////		//generate timestamps for usersers
//		accounts.forEach(account -> {
//			ArrayList<TimeStamp> cals = generateStamps(account.getRfidKey());
//			timeRepository.save(cals);
//		});
//
//	}

//	private ArrayList<Account> createUsers() {
//
//		PasswordEncoder encoder = new BCryptPasswordEncoder();
//		Account defaultUser = accountRepository.findByUserName("user");
//		Account adminUser = accountRepository.findByUserName("admin");
//		Account piUser = accountRepository.findByUserName("piUser");
//		Account user2 = accountRepository.findByUserName("user2");
//		Account user3 = accountRepository.findByUserName("user3");
//		Account user4 = accountRepository.findByUserName("user4");
//
//
//		//create users
//
//		if (defaultUser == null) {
//			log.info("CREATING DEFAULT USER");
//			String pass = encoder.encode("pass");
//			RfidKey test = new RfidKey("C48659EC");
//			defaultUser = new Account("Doris", "Popo", "user", pass,
//					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
//			);
//			defaultUser.setRfidKey(test);
//			accountRepository.save(defaultUser);
//		} else {
//			log.info("USER ALLREADY in DB");
//		}
//		if (adminUser == null) {
//			log.info("CREATING ADMIN USER");
//			String pass = encoder.encode("pass");
//			adminUser = new Account("Lucifer", "Morningstar", "admin", pass,
//					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER,
//							AuthoritiesConstants.PIUSER, AuthoritiesConstants.ADMIN));
//			adminUser.setRfidKey(new RfidKey("34915AEC"));
//
//			accountRepository.save(adminUser);
//		} else {
//			log.info("USER ALLREADY in DB");
//		}
//		if (piUser == null) {
//			log.info("CREATING PIUSER USER");
//			String pass = encoder.encode("pass");
//			accountRepository.save(new Account("Pie", "Rubarb", "piUser", pass,
//					AuthorityUtils.createAuthorityList(AuthoritiesConstants.PIUSER)));
//		} else {
//			log.info("USER ALLREADY in DB");
//		}
//
//
//		////////////////////////////////////////////////////////////////////////
//		if (user2 == null) {
//			user2 = new Account("Matt", "Murdock", "user2",
//					encoder.encode("pass"),
//					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
//			);
//			user2.setRfidKey(new RfidKey("A448182B"));
//			accountRepository.save(user2);
//
//		} else
//			log.info(user2.getFirstName() + " " + user2.getLastName() + " IS ALLREADY IN DB");
//		////////////////////////////////////////////////////////////////////////
//		if (user3 == null) {
//			user3 = new Account("John", "Snow", "user3",
//					encoder.encode("pass"),
//					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
//			);
//			user3.setRfidKey(new RfidKey("4B79295"));
//			accountRepository.save(user3);
//		} else
//			log.info(user3.getFirstName() + " " + user3.getLastName() + " IS ALLREADY IN DB");
//		////////////////////////////////////////////////////////////////////////
//		if (user4 == null) {
//			user4 = new Account("Lucas", "Hood", "user4",
//					encoder.encode("pass"),
//					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
//			);
//			user4.setRfidKey(new RfidKey("247615E"));
//
//			accountRepository.save(user4);
//		} else
//			log.info(user4.getFirstName() + " " + user4.getLastName() + " IS ALLREADY IN DB");
//
//
//		ArrayList<Account> accounts = new ArrayList<>();
//		accounts.add(adminUser);
//		accounts.add(defaultUser);
//		accounts.add(user2);
//		accounts.add(user3);
//		accounts.add(user4);
//		return accounts;
//	}
//
//	private void clearDB() {
//		accountRepository.deleteAll();
//		timeRepository.deleteAll();
//		scheduleRepository.deleteAll();
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
//			inCal = new GregorianCalendar(2016, 4, (1 + i), (6 + rand), 0);
//			cals.add(new TimeStamp(inCal.getTimeInMillis(), true, rfidKey));
//			rand = rnd.nextInt(3);
//			outCal = new GregorianCalendar(2016, 4, (1 + i), (13 + rand), 0);
//			cals.add(new TimeStamp(outCal.getTimeInMillis(), false, rfidKey));
//
//		}
//		return cals;
//	}

//	private ArrayList<ScheduleStamp> generateScheds(String[] ids) {
//		Random rnd = new Random();
//		ArrayList<ScheduleStamp> cals = new ArrayList<>();
//		GregorianCalendar inCal = new GregorianCalendar(), outCal = new GregorianCalendar();
//		int rand = rnd.nextInt(3);
//
//		//4st
//
//		for (int i = 0; i < 30; i++) {
//
//			rand = rnd.nextInt(3);
//			inCal = new GregorianCalendar(2016, 4, (1 + i), (6 + rand), 0);
//			rand = rnd.nextInt(3);
//			outCal = new GregorianCalendar(2016, 4, (1 + i), (13 + rand), 0);
//			rand = rnd.nextInt(4)+1;
//			String[] tempIds = Arrays.copyOf(ids,rand);
//
//			cals.add(new ScheduleStamp(inCal.getTimeInMillis(), outCal.getTimeInMillis(),tempIds));
//
//		}
//		scheduleRepository.save(cals);
//		return cals;
//	}

}

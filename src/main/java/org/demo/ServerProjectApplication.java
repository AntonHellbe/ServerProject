package org.demo;


import org.demo.config.AuthoritiesConstants;
import org.demo.model.RfidKey;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

		// TODO: 2016-04-24 :17:24 here you can add new users or run other commands for upstart loadning data

		PasswordEncoder encoder = new BCryptPasswordEncoder();

//		accountRepository.deleteAll();
//		timeRepository.deleteAll();
//		scheduleRepository.deleteAll();

		Account defaultUser = accountRepository.findByUserName("user");
		Account adminUser = accountRepository.findByUserName("admin");
		Account piUser = accountRepository.findByUserName("piUser");
		Account user2 = accountRepository.findByUserName("user2");
		Account user3 = accountRepository.findByUserName("user3");
		Account user4 = accountRepository.findByUserName("user4");


		//create users

		if (defaultUser == null) {
			log.info("CREATING DEFAULT USER");
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
			String pass = encoder.encode("pass");
			adminUser = new Account("Lucifer", "Morningstar", "admin", pass,
					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER,
							AuthoritiesConstants.PIUSER, AuthoritiesConstants.ADMIN));
			adminUser.setRfidKey(new RfidKey("34915AEC"));

			accountRepository.save(adminUser);
		} else {
			log.info("USER ALLREADY in DB");
		}
		if (piUser == null) {
			log.info("CREATING PIUSER USER");
			String pass = encoder.encode("pass");
			accountRepository.save(new Account("Pie", "Rubarb", "piUser", pass,
					AuthorityUtils.createAuthorityList(AuthoritiesConstants.PIUSER)));
		} else {
			log.info("USER ALLREADY in DB");
		}


		////////////////////////////////////////////////////////////////////////
		if (user2 == null) {
			user2 = new Account("Matt", "Murdock", "user2",
					encoder.encode("pass"),
					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
			);
			user2.setRfidKey(new RfidKey("A448182B"));
			accountRepository.save(user2);

		} else
			log.info(user2.getFirstName() + " " + user2.getLastName() + " IS ALLREADY IN DB");
		////////////////////////////////////////////////////////////////////////
		if (user3 == null) {
			user3 = new Account("John", "Snow", "user3",
					encoder.encode("pass"),
					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
			);
			user3.setRfidKey(new RfidKey("4B79295"));
			accountRepository.save(user3);
		} else
			log.info(user3.getFirstName() + " " + user3.getLastName() + " IS ALLREADY IN DB");
		////////////////////////////////////////////////////////////////////////
		if (user4 == null) {
			user4 = new Account("Lucas", "Hood", "user4",
					encoder.encode("pass"),
					AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
			);
			user4.setRfidKey(new RfidKey("247615E"));

			accountRepository.save(user4);
		} else
			log.info(user4.getFirstName() + " " + user4.getLastName() + " IS ALLREADY IN DB");


//		TimeStamp newStamp = new TimeStamp(Long.parseLong("1462878000000"), true, new RfidKey("4B79295") );
//		TimeStamp newStamp1 = new TimeStamp(Long.parseLong("1462856400000"), true, new RfidKey("4B79295") );
//		TimeStamp newStamp2 = new TimeStamp(Long.parseLong("1462770000000"), true, new RfidKey("4B79295") );
//		TimeStamp newStamp3 = new TimeStamp(Long.parseLong("1462856400000"), true, new RfidKey("4B79295") );
//		timeRepository.save(newStamp);
//		timeRepository.save(newStamp1);
//		timeRepository.save(newStamp2);
//		timeRepository.save(newStamp3);
//		List<TimeStamp> gotAll = timeRepository.findAll();
//		for (int i = 0; i < gotAll.size() ; i++) {
//			gotAll.get(i).setTime(gotAll.get(i).getDate().getTimeInMillis());
//			timeRepository.save(gotAll.get(i));
//		}
//
//		Calendar day1 = Calendar.getInstance();
//		day1.set(2016, 4, 17, 18, 0, 0);
//		String[] ids = new String[1];
//		ids[0] = user2.getId();
//		ScheduleStamp newStamp = new ScheduleStamp(Calendar.getInstance().getTimeInMillis(), day1.getTimeInMillis(), ids);
//		scheduleRepository.save(newStamp);
//		//generate timestamps for usersers
//		ArrayList<TimeStamp> calsA = generateStamps(adminUser.getRfidKey());
//		ArrayList<TimeStamp> cals1 = generateStamps(defaultUser.getRfidKey());
//		ArrayList<TimeStamp> cals2 = generateStamps(user2.getRfidKey());
//		ArrayList<TimeStamp> cals3 = generateStamps(user3.getRfidKey());
//		ArrayList<TimeStamp> cals4 = generateStamps(user4.getRfidKey());
////
////		//add stamps
//
//		timeRepository.save(calsA);
//		timeRepository.save(cals1);
//		timeRepository.save(cals2);
//		timeRepository.save(cals3);
//		timeRepository.save(cals4);


		//TODO SEE unit test: testDBQueryFindLatest in ServerProjectApplicationTests
		//db.getCollection('TimeStamps').find({"rfidKey._id":"34915AEC"}).sort({date:-1}).limit(1)
//		Query query = new Query();
//		query.limit(1);
//		query.with(new Sort(Sort.Direction.DESC, "date.time")).addCriteria(Criteria.where("rfidKey._id").is("34915AEC"));
//
//		TimeStamp got = mongoOperations.findOne(query, TimeStamp.class);



//		log.info("Lucifer Morningstars last timestamp: "+got);



//				user;C48659EC;1
//				admin;34915AEC;2
//				user2;A448182B;3
//				user3;4B79295;4
//				user4;247615E;5
//				Anna;Ek;1C699EB6;6
//				Carsten;Panduro;8BA8A996;7

//
//		long from = Long.parseLong("1462280400000");
//		long to = Long.parseLong("1463547600000");

//		AndroidBetweenQuery bw = new AndroidBetweenQuery(from,to,new RfidKey("C48659EC"));
//		List<TimeStamp> got = timeRepository.getBetween(bw);
//
//
//		log.info("Get between");
//		got.forEach(timeStamp -> {
//			log.info("tes "+timeStamp.toString());
//		});


//		end of runner
	}

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
//
////			log.info("cal " + sdf.format(inCal.getTime()));
//			cals.add(new TimeStamp(inCal.getTimeInMillis(), true, rfidKey));
//			log.info("inCAl "+inCal.getTime());
//
//			rand = rnd.nextInt(3);
////			log.info("rand22 " + rand);
//			outCal = new GregorianCalendar(2016, 4, (1 + i), (13 + rand), 0);
//			cals.add(new TimeStamp(outCal.getTimeInMillis(), false, rfidKey));
//
//			log.info("inCAl "+outCal.getTime());
//		}
//		log.info("rfid " + rfidKey);
//		return cals;
//	}

}

package org.demo;

import org.demo.model.RfidKey;
import org.demo.model.User;
import org.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerProjectApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServerProjectApplication.class, args);
	}

//	@Override
//	public void run(String... strings) throws Exception {
//		RfidKey test = new RfidKey("247615E");
//		System.out.println("Finding ID" + test.toString());
//		User gotUser = userRepository.findByRfid(test);
//		System.out.println("Found user" + gotUser.toString());
//
//
//	}
}

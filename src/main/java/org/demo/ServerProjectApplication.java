package org.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerProjectApplication implements CommandLineRunner{

	private String ip = "http://localhost:8080/users";
	private static final Logger log = LoggerFactory.getLogger(ServerProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServerProjectApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		// TODO: 2016-04-24 :17:24 here you can add new users or run other commands for upstart loadning data
	}
}

package org.demo;

import org.demo.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

//@WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServerProjectApplication.class)
@WebIntegrationTest({"server.port=8080"})
public class ServerProjectApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(ServerProjectApplication.class);
	private static  String ip = "http://localhost:8080/users";

	private MockMvc mockMvc;

	@Rule
	public OutputCapture capture = new OutputCapture();

	@Test
	public void testName() throws Exception {
		System.out.println("Hello World!");
		assertThat(capture.toString(), containsString("World"));
	}


	@Test
	public void testGetAllUsers() {

		RestTemplate restTemplate = new RestTemplate();
		User[] got = restTemplate.getForObject(ip, User[].class);
		log.info(Arrays.toString(got));

		assertThat(got, notNullValue());
		log.info("first item in list is a User");
		assertThat(got[0],instanceOf(User.class) );
		log.info("User info: \n"+
				got[0].toString());
	}




	@Test(expected=ResourceAccessException.class)
	public void testWrongPort() {

		ip = "http://localhost:8081/users";
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<User> got = restTemplate.getForObject(ip, ArrayList.class);
		log.info(got.toString());
	}



	//----Security Tests-----



	@Autowired
	private ApplicationContext context;

	private Authentication authentication;
	private AuthenticationManager authenticationManager;

	@Before
	public void setUp() throws Exception {

//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//				.build();

		authenticationManager = this.context
				.getBean(AuthenticationManager.class);
	}

	@After
	public void close() {
		SecurityContextHolder.clearContext();
	}

	@Test
	public void authenticated() throws Exception {
		this.authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken("user", "pass"));
		SecurityContextHolder.getContext().setAuthentication(this.authentication);
	}

	@Test(expected=BadCredentialsException.class)
	public void failAuthenticated() throws Exception {
		this.authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken("user", "wrongPassword"));

		SecurityContextHolder.getContext().setAuthentication(this.authentication);



		// TODO: 2016-04-22 :18:49 to understand assertThat and is var's
		//they are static imports
//		import static org.hamcrest.Matchers.is;
//		import static org.junit.Assert.assertThat;
//		assertThat(123, is("Greetings from Spring Boot!"));

	}

}

//package org.demo;
//
//import org.demo.model.security.Account;
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.OutputCapture;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.boot.test.TestRestTemplate;
//import org.springframework.boot.test.WebIntegrationTest;
//import org.springframework.context.ApplicationContext;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.client.ResourceAccessException;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import static org.hamcrest.Matchers.*;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
//
////@WebAppConfiguration
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = ServerProjectApplication.class)
//@WebIntegrationTest({"server.port=9090"})
//public class ServerProjectApplicationTests {
//
//	private static final Logger log = LoggerFactory.getLogger(ServerProjectApplication.class);
//	private static  String ip = "http://localhost:9090/users";
//
//	private MockMvc mockMvc;
//
//	@Rule
//	public OutputCapture capture = new OutputCapture();
//
//	@Test
//	public void testName() throws Exception {
//		System.out.println("Hello World!");
//		assertThat(capture.toString(), containsString("World"));
//	}
//
//
//	@Test
//	public void testGetAllUsers() {
//
//		RestTemplate template = new TestRestTemplate("admin", "pass");
//		ResponseEntity<String> response = template.getForEntity("http://localhost:" + port
//				+ "/api/users", String.class);
//        //ResponseEntity<Account[]> response = template.getForEntity("http://localhost:" + port
//		//		+ "/api/users", Account[].class);
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		//Account[] users = response.getBody();
//		System.out.println("USERS ");
//        System.out.println("status Code");
//        System.out.println(response.getStatusCode());
//        System.out.println("body::");
//        System.out.println(response.getBody());
//        //System.out.println(Arrays.toString(users));
//
////		RestTemplate restTemplate = new RestTemplate();
////		User[] got = restTemplate.getForObject(ip, User[].class);
////		log.info(Arrays.toString(got));
////
////		assertThat(got, notNullValue());
////		log.info("first item in list is a User");
////		assertThat(got[0],instanceOf(User.class) );
////		log.info("User info: \n"+
////				got[0].toString());
//	}
//
//
//    @Test
//    public void testUpdateUser() {
//
//        RestTemplate template = new TestRestTemplate("admin", "pass");
//        ResponseEntity<Account> response = template.getForEntity("http://localhost:" + port
//                + "/api/users/5720bb7ecbcb3e71d7e6f715", Account.class);
//        //ResponseEntity<Account[]> response = template.getForEntity("http://localhost:" + port
//        //		+ "/api/users", Account[].class);
//        //assertEquals(HttpStatus.OK, response.getStatusCode());
//        //Account[] users = response.getBody();
//        System.out.println("USERS ");
//        System.out.println("status Code");
//        System.out.println(response.getStatusCode());
//        System.out.println("body::");
//        System.out.println(response.getBody());
//        //System.out.println(Arrays.toString(users));
//
//		Account user = response.getBody();
//		user.setFirstName("lolo");
//
//		RestTemplate template2 = new TestRestTemplate("admin", "pass");
//		ResponseEntity<Account> got = template2.postForEntity("http://localhost:" + port
//				+ "/api/users/5720bb7ecbcb3e71d7e6f715", user, Account.class);
//		System.out.println("GOT "+got.getStatusCode());
//		System.out.println("GOT "+got.getBody());
//
//
//
//    }
//
//	@Value("${local.server.port}")
//	private int port;
//
//	@Test
//	public void homePageProtected() {
//		ResponseEntity<String> response = new TestRestTemplate().getForEntity(ip, String.class);
//		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
//	}
//
//
//
//
//
//	@Test(expected=ResourceAccessException.class)
//	public void testWrongPort() {
//
//		ip = "http://localhost:8081/users";
//		RestTemplate restTemplate = new RestTemplate();
//		ArrayList<Account> got = restTemplate.getForObject(ip, ArrayList.class);
//		log.info(got.toString());
//	}
//
//
//
//	//----Security Tests-----
//
//
//
//	@Autowired
//	private ApplicationContext context;
//
//	private Authentication authentication;
//	private AuthenticationManager authenticationManager;
//
//	@Before
//	public void setUp() throws Exception {
//
////		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
////				.build();
//
//		authenticationManager = this.context
//				.getBean(AuthenticationManager.class);
//	}
//
//	@After
//	public void close() {
//		SecurityContextHolder.clearContext();
//	}
//
//	@Test
//	public void authenticated() throws Exception {
//		this.authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken("user", "pass"));
//		SecurityContextHolder.getContext().setAuthentication(this.authentication);
//	}
//
//	@Test(expected=BadCredentialsException.class)
//	public void failAuthenticated() throws Exception {
//		this.authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken("user", "wrongPassword"));
//
//		SecurityContextHolder.getContext().setAuthentication(this.authentication);
//
//
//
//		// TODO: 2016-04-22 :18:49 to understand assertThat and is var's
//		//they are static imports
////		import static org.hamcrest.Matchers.is;
////		import static org.junit.Assert.assertThat;
////		assertThat(123, is("Greetings from Spring Boot!"));
//
//	}
//
//}

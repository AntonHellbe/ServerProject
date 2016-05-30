package org.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.demo.config.AuthoritiesConstants;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.security.Account;
import org.demo.repository.TimeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServerProjectApplication.class)
@WebIntegrationTest({"server.port=9090"})
public class ServerProjectApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(ServerProjectApplication.class);
	private static  String ip = "http://localhost:9090/users";

	@Autowired
	private TimeRepository timeRepository;
	@Autowired
	private MongoOperations mongoOperations;


	/**
	 * Testing jackson serializing of JSON object to Java and otherway.
	 * @throws IOException
	 */
	@Test
	public void test1() throws IOException {

//		Account userAccountAuthentication = new Account();
//		userAccountAuthentication.getAuthorities().add(new SimpleGrantedAuthority("role1"));
//		userAccountAuthentication.getAuthorities().add(new SimpleGrantedAuthority("role2"));

		System.out.println("CREATING DEFAULT USER");
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String pass = encoder.encode("pass");
		RfidKey test = new RfidKey("C48659EC");
		test.setEnabled(true);
		Account newAccount = new Account("Doris", "Popo","user", pass,
				AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
		);
		newAccount.setRfidKey(test);




		String json1 = new ObjectMapper().writeValueAsString(newAccount);
		System.out.println(json1);


//      //For undestanding jackson
//		//http://www.baeldung.com/jackson-json-to-jsonnode
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode actualObj = mapper.readTree(json1);
//		actualObj.fieldNames().forEachRemaining(s -> {
//			System.out.println("field: "+s);
//		});
//		System.out.println("get auth= "+actualObj.get("authorities"));
//		actualObj.get("authorities").forEach(jsonNode -> {
//			System.out.println("role: ["+jsonNode.get("authority").asText()+"]");
//		});



		Account readValue = new ObjectMapper().readValue(json1, Account.class);
		String json2 = new ObjectMapper().writeValueAsString(readValue);
		System.out.println("SHOD: "+newAccount.getRfidKey());
		System.out.println("auth: "+readValue.getRfidKey());
		assertEquals(json1, json2);
	}

	/**
	 * Login test
	 */
	@Test
	public void loginSucceeds() {
		RestTemplate template = new TestRestTemplate("user", "pass");
		ResponseEntity<String> response = template.getForEntity("http://localhost:9090"
				+ "/api/account", String.class);
		System.out.println(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	/**
	 * Logout test
	 */
	@Test
	public void logOUTSucceeds() {
		RestTemplate template = new TestRestTemplate("user", "pass");
		ResponseEntity<String> response = template.getForEntity("http://localhost:9090"
				+ "/api/account", String.class);
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());
		//assertEquals(HttpStatus.OK, response.getStatusCode());
		ResponseEntity<String> logoutResponse = template.postForEntity("http://localhost:9090"
				+ "/logout","", String.class);

		System.out.println(logoutResponse.getBody());
		System.out.println(logoutResponse.getStatusCode());
	}

	/**
	 * Mongo DB query test to find and sort
	 * @throws Exception
	 */
	@Test
	public void testDBQueryFindLatest() throws Exception {
		System.out.println("Testing Lucifer Morningstar");
		System.out.println("Rfid: 34915AEC");
		Query query = new Query();
		query.limit(1);
		query.with(new Sort(Sort.Direction.DESC, "date.time")).addCriteria(Criteria.where("rfidKey._id").is("34915AEC"));

		TimeStamp got = mongoOperations.findOne(query, TimeStamp.class);



		System.out.println("got "+got);

	}


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

}

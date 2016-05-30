package org.demo;

import clientcode.BasicAuthRestTemplate;
import org.demo.model.security.Account;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by Sebastian Börebäck on 2016-05-26.
 */
public class Tester {

	private static final Logger log = LoggerFactory.getLogger(Tester.class);

	private SimpleClientHttpRequestFactory getReqFactory() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(12000);
		requestFactory.setReadTimeout(12000);
		return requestFactory;
	}

	/**
	 * Test Rest connection, with SSL and password
	 * tests getting all users and updating a user.
	 * @throws Exception
	 */
	@Test
	public void testRest() throws Exception {


		BasicAuthRestTemplate rest = new BasicAuthRestTemplate("admin", "pass", getReqFactory());

		String url = "http://localhost:44344/api/users/";
//		String url2 = "https://projektessence.se/api/users/";

		//fix the issue with SSL
		BasicAuthRestTemplate.trustSelfSignedSSL();


		try {
			ResponseEntity<DummyAccount[]> restall = rest.exchange(url, HttpMethod.GET, null, DummyAccount[].class);

			log.info(Arrays.toString(restall.getBody()));

			DummyAccount[] all = restall.getBody();
			DummyAccount doris = all[0];
			log.info("last: " + doris.getLastName());
			doris.setLastName("asdf");
			doris.getAuthorities().add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			Set<String> auths = AuthorityUtils.authorityListToSet(doris.getAuthorities());


			HttpEntity<DummyAccount> entity = new HttpEntity<>(doris);

			ResponseEntity<Account> restup = rest.exchange(url + doris.getId(), HttpMethod.PUT, entity, Account.class);
			log.info("update " + restup.getStatusCode());

			log.info("update " + restup.getBody());
			restall = rest.exchange(url, HttpMethod.GET, null, DummyAccount[].class);

			log.info(Arrays.toString(restall.getBody()));

		} catch (Exception e) {
			log.info(e.getMessage());

		}

	}
}

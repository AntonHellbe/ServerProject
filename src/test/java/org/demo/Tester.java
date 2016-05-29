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

	private SimpleClientHttpRequestFactory getFac(){
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(12000);
		requestFactory.setReadTimeout(12000);
		//restTemplate.setRequestFactory(requestFactory);
		return requestFactory;
	}

	@Test
	public void testRest() throws Exception {


		BasicAuthRestTemplate rest = new BasicAuthRestTemplate("admin", "pass",getFac());
//		RestTemplate rest = new RestTemplate(getFac());

//		((SimpleClientHttpRequestFactory)rest.getRequestFactory()).setReadTimeout(1000*30);

		String url = "http://localhost:44344/api/users/";
//		String url2 = "https://projektessence.se/api/users/";

		BasicAuthRestTemplate.trustSelfSignedSSL();

//		HttpComponentsClientHttpRequestFactory rf =
//				(HttpComponentsClientHttpRequestFactory) rest.getRequestFactory();
//		rf.setReadTimeout(1 * 1000);
//		rf.setConnectTimeout(1 * 1000);

		try {
			ResponseEntity<DummyAccount[]> restall = rest.exchange(url, HttpMethod.GET, null, DummyAccount[].class);

			log.info(Arrays.toString(restall.getBody()));

			DummyAccount[] all = restall.getBody();
			DummyAccount doris = all[0];
			log.info("last: "+doris.getLastName());
			doris.setLastName("asdf");
			doris.getAuthorities().add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			Set<String> auths = AuthorityUtils.authorityListToSet(doris.getAuthorities());


			HttpEntity<DummyAccount> entity = new HttpEntity<>(doris);

			ResponseEntity<Account> restup= rest.exchange(url+doris.getId(), HttpMethod.PUT, entity, Account.class);
			log.info("update "+restup.getStatusCode());
			log.info("update "+restup.getBody());

			 restall = rest.exchange(url, HttpMethod.GET, null, DummyAccount[].class);

			log.info(Arrays.toString(restall.getBody()));

		} catch (Exception e) {
			log.info(e.getMessage());

		}





//		ResponseEntity<Account> restUpdate= rest.exchange(url+doris.getId(), HttpMethod.PUT, doris, Account[].class);

//		restTemplate.exchange("https://abc.com/api/request", HttpMethod.POST,
//				formEntity, YourPojoThatMapsToJsonResponse.class);
	}

//	@Bean
//	@ConfigurationProperties(prefix = "custom.rest.connection")
//	private ClientHttpRequestFactory clientHttpRequestFactory() {
//		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//		factory.setReadTimeout(2000);
//		factory.setConnectTimeout(2000);
//		return factory;
//	}






}

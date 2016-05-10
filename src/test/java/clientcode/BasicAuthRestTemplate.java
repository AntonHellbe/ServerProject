package clientcode;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sebastian Börebäck on 2016-05-10.
 */
public class BasicAuthRestTemplate extends RestTemplate {

	public BasicAuthRestTemplate(String username, String password) {
		addAuthentication(username, password);
	}

	private void addAuthentication(String username, String password) {
		if (username == null) {
			return;
		}
		List<ClientHttpRequestInterceptor> interceptors = Collections
				.<ClientHttpRequestInterceptor> singletonList(
						new BasicAuthorizationInterceptor(username, password));
		setRequestFactory(new InterceptingClientHttpRequestFactory(getRequestFactory(),
				interceptors));
	}

	private static class BasicAuthorizationInterceptor implements
			ClientHttpRequestInterceptor {

		private final String username;

		private final String password;

		public BasicAuthorizationInterceptor(String username, String password) {
			this.username = username;
			this.password = (password == null ? "" : password);
		}

		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body,
		                                    ClientHttpRequestExecution execution) throws IOException {
			byte[] token = Base64.getEncoder().encode(
					(this.username + ":" + this.password).getBytes());
			request.getHeaders().add("Authorization", "Basic " + new String(token));
			return execution.execute(request, body);
		}

	}

}
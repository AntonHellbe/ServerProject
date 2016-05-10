package clientcode;

import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by Sebastian Börebäck on 2016-05-10.
 */
public class MySimpleClientHttpRequestFactory extends SimpleClientHttpRequestFactory
{

	private final HostnameVerifier verifier;

	public MySimpleClientHttpRequestFactory(final HostnameVerifier verifier)
	{
		this.verifier = verifier;
	}

	@Override
	protected void prepareConnection(final HttpURLConnection connection, final String httpMethod) throws IOException
	{
		if (connection instanceof HttpsURLConnection)
		{
			((HttpsURLConnection) connection).setHostnameVerifier(verifier);
		}
		super.prepareConnection(connection, httpMethod);
	}
}
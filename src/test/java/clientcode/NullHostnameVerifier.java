package clientcode;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by Sebastian Börebäck on 2016-05-10.
 */
public class NullHostnameVerifier implements HostnameVerifier
{
	@Override
	public boolean verify(final String hostname, final SSLSession session)
	{
		return true;
	}

}

package clientcode;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by Sebastian Börebäck on 2016-05-10.
 */
public class MrTrusterSSL {

	public String doRestTemplate() {
		BasicAuthRestTemplate restTemplate = new BasicAuthRestTemplate("user", "pass");
		String url = "https://projektessence.se/api/account";

		trustSelfSignedSSL();

		String got = "";
		try {
			// Make the HTTP GET request to the Basic Auth protected URL
			got = restTemplate.getForObject(url, String.class);
			System.out.println(got);
		} catch (Exception e) {
			System.out.println("error with doing rest "+e.getMessage());
		}
		return got;
	}

	public static void trustSelfSignedSSL() {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {

				public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[]{tm}, null);
			SSLContext.setDefault(ctx);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

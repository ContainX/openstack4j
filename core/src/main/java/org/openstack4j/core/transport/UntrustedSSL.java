package org.openstack4j.core.transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * Contains an open SSL Context which allows self signed certs and an open hostname verifier
 *
 * @author Jeremy Unruh
 */
public class UntrustedSSL {

    private static final UntrustedSSL INSTANCE = new UntrustedSSL();
    private static final Logger LOG = LoggerFactory.getLogger(UntrustedSSL.class);

    private SSLContext context;
    private HostnameVerifier verifier;

    private UntrustedSSL() {
        try
        {
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                	return new X509Certificate[]{};
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
            } };
            SSLContext ssc = SSLContext.getInstance("TLS");
            ssc.init(null, trustAllCerts, new SecureRandom());

            this.context = ssc;
            this.verifier = new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession session) {
                    return true;
                } };
        }
        catch (Throwable t) {
            LOG.error(t.getMessage(), t);
        }
    }

    public static SSLContext getSSLContext() {
        return INSTANCE.context;
    }

    public static HostnameVerifier getHostnameVerifier() {
        return INSTANCE.verifier;
    }

}

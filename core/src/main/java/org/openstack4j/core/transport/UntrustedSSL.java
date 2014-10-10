package org.openstack4j.core.transport;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Contains an open SSL Context which allows self signed certs and an open hostname verifier
 * 
 * @author Jeremy Unruh
 */
public class UntrustedSSL {

    private static final UntrustedSSL INSTANCE = new UntrustedSSL();
    
    private SSLContext context;
    private HostnameVerifier verifier;
    
    private UntrustedSSL() {
        try
        {
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
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
            t.printStackTrace();
        }
    }
    
    public static SSLContext getSSLContext() {
        return INSTANCE.context;
    }
    
    public static HostnameVerifier getHostnameVerifier() {
        return INSTANCE.verifier;
    }
    
}

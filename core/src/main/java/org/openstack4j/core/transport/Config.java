package org.openstack4j.core.transport;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

/**
 * OpenStack4j Configuration - options that are configured with OpenStack4j clients.
 * 
 * @author Jeremy Unruh
 */
public class Config {

    private int connectTimeout;
    private int readTimeout;
    private SSLContext sslContext;
    private HostnameVerifier hostNameVerifier;
    private boolean ignoreSSLVerification;
    
    /**
     * Sets the connection timeout in milliseconds
     * 
     * @param connectTimeout timeout in milliseconds
     * @return Config
     */
    public Config withConnectionTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }
    
    /**
     * Sets the read timeout in milliseconds
     * 
     * @param readTimeout timeout in milliseconds
     * @return Config
     */
    public Config withReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }
    
    /**
     * Associates the <b>initialized</b> SSL Context to use when querying secure endpoints
     * 
     * @param sslContext
     * @return Config
     */
    public Config withSSLContext(SSLContext sslContext) {
        this.sslContext = sslContext;
        return this;
    }
    
    /**
     * Sets the Hostname Verifier to use with SSL
     * 
     * @param hostnameVerifier the hostname verifier
     * @return Config
     */
    public Config withHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.hostNameVerifier = hostnameVerifier;
        return this;
    }
    
    /**
     * If no SSL Context has been specified and this SSL Verification is disabled we will by pass certificate checks (useful for self signed certificates).
     * 
     * NOTE: This property used to be known as "useNonStrictSSL" in previous releases
     * 
     * @return Config
     */
    public Config withSSLVerificationDisabled() {
        this.ignoreSSLVerification = Boolean.TRUE;
        return this;
    }
    
    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public SSLContext getSslContext() {
        return sslContext;
    }

    public HostnameVerifier getHostNameVerifier() {
        return hostNameVerifier;
    }

    public boolean isIgnoreSSLVerification() {
        return ignoreSSLVerification;
    }
    
}

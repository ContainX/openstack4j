package org.openstack4j.core.transport;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

/**
 * OpenStack4j Configuration - options that are configured with OpenStack4j clients.
 * 
 * @author Jeremy Unruh
 */
public final class Config {

    public static final Config DEFAULT = new Config();
    
    private int connectTimeout;
    private int readTimeout;
    private SSLContext sslContext;
    private HostnameVerifier hostNameVerifier;
    private boolean ignoreSSLVerification;
    
    private Config() {
    }
    
    /**
     * @return A new client configuration
     */
    public static Config newConfig() {
        return new Config();
    }
    
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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + connectTimeout;
        result = prime * result + (ignoreSSLVerification ? 1231 : 1237);
        result = prime * result + readTimeout;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Config other = (Config) obj;
        if (connectTimeout != other.connectTimeout)
            return false;
        if (ignoreSSLVerification != other.ignoreSSLVerification)
            return false;
        if (readTimeout != other.readTimeout)
            return false;
        return true;
    }
    
}

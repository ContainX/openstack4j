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
    private String natHostOrIP;
    
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
     * If connecting to an OpenStack deployment is in front of a NAT or Proxy then this option can be provided to dynamically change
     * the service endpoints hostname/IP to the one NAT is using.  
     * <p>
     * Example:<br>
     * Setting NAT IP to: 24.24.24.24<br>
     * <br>
     * Would be substitued in any endpoint for any service.  Let's assume we're looking for Heat endpoint 
     * which is returning 192.168.0.2:8000<br>
     * <br>
     * The result would be translated dynamically to 24.24.24.24:8000 so we can access via NAT
     * 
     * @param natHostOrIP the FQDN Host or IP Address
     * @return Config
     */
    public Config withEndpointNATResolution(String natHostOrIP) {
        this.natHostOrIP = natHostOrIP;
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
    
    public String getEndpointNATResolution() {
        return natHostOrIP;
    }
    
    public boolean isBehindNAT() {
        return natHostOrIP != null;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + connectTimeout;
        result = prime * result + (ignoreSSLVerification ? 1231 : 1237);
        result = prime * result + ((natHostOrIP == null) ? 0 : natHostOrIP.hashCode());
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
        if (natHostOrIP == null) {
            if (other.natHostOrIP != null)
                return false;
        } else if (!natHostOrIP.equals(other.natHostOrIP))
            return false;
        if (readTimeout != other.readTimeout)
            return false;
        return true;
    }
    
}

package org.openstack4j.openstack.client;

import org.openstack4j.api.OSClient;
import org.openstack4j.api.client.IOSClientBuilder;
import org.openstack4j.api.exceptions.AuthenticationException;
import org.openstack4j.api.types.Facing;
import org.openstack4j.core.transport.Config;
import org.openstack4j.openstack.identity.domain.Credentials;
import org.openstack4j.openstack.identity.domain.v3.KeystoneAuth;
import org.openstack4j.openstack.internal.OSAuthenticator;

/**
 * Builder definitions for creating a Client
 * 
 * @author Jeremy Unruh
 *
 * @param <R> Client Type
 * @param <T> Builder Type
 */
public abstract class OSClientBuilder<R, T extends IOSClientBuilder<R, T>> implements IOSClientBuilder<R, T> {

    Config config;
    String endpoint;
    String user;
    String password;
    Facing perspective;
    
    @SuppressWarnings("unchecked")
    @Override
    public T withConfig(Config config) {
        this.config = config;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T credentials(String user, String password) {
        this.user = user;
        this.password = password;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T endpoint(String endpoint) {
        this.endpoint = endpoint;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T perspective(Facing perspective) {
        this.perspective = perspective;
        return (T) this;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T useNonStrictSSLClient(boolean useNonStrictSSL) {
        if (config == null)
            config = Config.newConfig().withSSLVerificationDisabled();
        return (T) this;
    }
    
    public static class ClientV2 extends OSClientBuilder<OSClient, IOSClientBuilder.V2> implements IOSClientBuilder.V2 {

        String tenantName;
        String tenantId;
        
        @Override
        public ClientV2 tenantName(String tenantName) {
            this.tenantName = tenantName;
            return this;
        }

        @Override
        public ClientV2 tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }
        
        @Override
        public OSClient authenticate() throws AuthenticationException {
            return OSAuthenticator.invoke(new Credentials(user, password, tenantName, tenantId), endpoint, perspective, config);
        }
    }
    
    public static class ClientV3 extends OSClientBuilder<OSClient, IOSClientBuilder.V3> implements IOSClientBuilder.V3 {

        String domainId;
        String domainName;
        
        @Override
        public ClientV3 domainName(String domainName) {
            this.domainName = domainName;
            return this;
        }

        @Override
        public ClientV3 domainId(String domainId) {
            this.domainId = domainId;
            return this;
        }

        @Override
        public OSClient authenticate() throws AuthenticationException {
            return OSAuthenticator.invoke(new KeystoneAuth(user, password, domainName, domainId), endpoint, perspective, config);
        }

    }
}

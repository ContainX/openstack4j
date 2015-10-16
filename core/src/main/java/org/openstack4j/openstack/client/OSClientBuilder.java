package org.openstack4j.openstack.client;

import org.openstack4j.api.OSClient;
import org.openstack4j.api.client.CloudProvider;
import org.openstack4j.api.client.IOSClientBuilder;
import org.openstack4j.api.exceptions.AuthenticationException;
import org.openstack4j.api.types.Facing;
import org.openstack4j.core.transport.Config;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.identity.domain.KeystoneAuth;
import org.openstack4j.openstack.identity.domain.KeystoneAuth.AuthScope;
import org.openstack4j.openstack.internal.OSAuthenticator;

/**
 * Builder definitions for creating a Client
 *
 * @author Jeremy Unruh
 *
 */
public abstract class OSClientBuilder implements IOSClientBuilder {

    Config config;
    String endpoint;
    String user;
    String password;
    Facing perspective;
    CloudProvider provider = CloudProvider.UNKNOWN;

    @Override
    public OSClientBuilder withConfig(Config config) {
        this.config = config;
        return this;
    }

    @Override
    public OSClientBuilder provider(CloudProvider provider) {
        this.provider = provider;
        return this;
    }


    @Override
    public OSClientBuilder credentials(String user, String password) {
        this.user = user;
        this.password = password;
        return this;
    }

    @Override
    public OSClientBuilder endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    @Override
    public OSClientBuilder perspective(Facing perspective) {
        this.perspective = perspective;
        return this;
    }

    @Override
    public OSClientBuilder useNonStrictSSLClient(boolean useNonStrictSSL) {
        if (config == null)
            config = Config.newConfig().withSSLVerificationDisabled();
        return this;
    }

    public static class Client extends OSClientBuilder implements IOSClientBuilder {

        Identifier domain;
        AuthScope scope;
        String tokenId;

        @Override
        public Client domainName(String domainName) {
            this.domain = Identifier.byName(domainName);
            return this;
        }

        @Override
        public Client domainId(String domainId) {
            this.domain = Identifier.byId(domainId);
            return this;
        }

        @Override
        public Client credentials(String user, String password, Identifier domain) {
            this.user = user;
            this.password = password;
            this.domain = domain;
            return this;
        }

        @Override
        public Client token(String tokenId) {
            this.tokenId = tokenId;
            return this;
        }

        @Override
        public OSClient authenticate() throws AuthenticationException {
            if (tokenId != null && tokenId.length() > 0)
                return OSAuthenticator.invoke(new KeystoneAuth(tokenId, scope), endpoint, perspective, config, provider);
            return OSAuthenticator.invoke(new KeystoneAuth(user, password, domain, scope), endpoint, perspective, config, provider);
        }

        @Override
        public Client scopeToProject(Identifier project, Identifier domain) {
            this.scope = AuthScope.project(project, domain);
            return this;
        }

        @Override
        public Client scopeToProject(Identifier project) {
            this.scope = AuthScope.project(project);
            return this;
        }

        @Override
        public Client scopeToDomain(Identifier domain) {
            this.scope = AuthScope.domain(domain);
            return this;
        }

        @Override
        public Client scopeToTrust(String id) {
            this.scope = AuthScope.trust(id);
            return this;
        }
    }
}

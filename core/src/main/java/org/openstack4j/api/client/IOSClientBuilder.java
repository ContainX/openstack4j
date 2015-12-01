package org.openstack4j.api.client;

import org.openstack4j.api.OSClient;
import org.openstack4j.api.exceptions.AuthenticationException;
import org.openstack4j.api.types.Facing;
import org.openstack4j.core.transport.Config;
import org.openstack4j.model.common.Identifier;

/**
 * OpenStack4j Client Builder
 *
 * @author Jeremy Unruh
 *
 */
public interface IOSClientBuilder {

    /**
     * Associates the given configuration with this Client
     *
     * @param config OpenStack4j configuration options
     * @return self for method chaining
     */
    IOSClientBuilder withConfig(Config config);

    /**
     * The authentication credentials
     *
     * @param userId the user id to authenticate with
     * @param password the password to authenticate with
     * @return self for method chaining
     */
    IOSClientBuilder credentials(String userId, String password);

    /**
     * The authentication credentials and default scoped domain
     *
     * @param userName the user name to authenticate with
     * @param password the password to authenticate with
     * @param domain the domain if using "default scoped"
     * @return self for method chaining
     */
    IOSClientBuilder credentials(String userName, String password, Identifier domain);

    /**
     * The identity endpoint to connect to
     *
     * @param endpoint the endpoint URL of the identity service
     * @return self for method chaining
     */
    IOSClientBuilder endpoint(String endpoint);

    /**
     * The OpenStack cloud provider which helps determine compatibility within requests
     *
     * @param provider the cloud provider
     * @return self for method chaining
     */
    IOSClientBuilder provider(CloudProvider provider);

    /**
     * Allows for a specific network perspective to be used.  For example to only use AdminURL Endpoints you would want to
     * set the {@link Facing#ADMIN} as a facing perspective.
     *
     * NOTE: If you choose PUBLIC some features may not work that are normally admin based configuration/functionality.  If you normally
     * are not using these features PUBLIC works fine in most cases.
     *
     * @param perspective the network facing perspective
     * @return self for method chaining
     */
    IOSClientBuilder perspective(Facing perspective);

    /**
     * DEPRECATED: Use {@link #applyConfig(Config)} to configure SSL policies
     *
     * In some private environments self signed certificates are used.  If you are using HTTPS and using
     * self-signed cerificates then set this to true.  Otherwise the default strict hostname and properly
     * signed validation based client will be used.
     *
     * @param useNonStrictSSL true if an HTTPS self-signed environment
     * @return self for method chaining
     */
    @Deprecated
    IOSClientBuilder useNonStrictSSLClient(boolean useNonStrictSSL);

    /**
     * DEPRECATED: Please use {@link #credentials(String, String, Identifier)
     *
     * Authenticates against the specified domain name
     *
     * @param domainName the domain name to authenticate against
     * @return self for method chaining
     */
    @Deprecated
    IOSClientBuilder domainName(String domainName);

    /**
     * DEPRECATED: Please use {@link #credentials(String, String, Identifier)}
     *
     * Authenticates against the specified domain identifier
     *
     * @param domainId the domain identifier to authenticate against
     * @return self for method chaining
     */
    @Deprecated
    IOSClientBuilder domainId(String domainId);

    /**
     * A token object. With token authentication, the id uniquely identifies the token.
     * This method is typically used in combination with a request to change authorization scope
     *
     * @param tokenId the token identifier
     * @return self for method chaining
     */
    IOSClientBuilder token(String tokenId);

    /**
     * Scopes the token to a project level
     *
     * @param project the project ID or Name value
     * @param domain the domain ID or Name value
     * @return self for method chaining
     */
    IOSClientBuilder scopeToProject(Identifier project, Identifier domain);

    /**
     * scopes the token to a project level
     *
     * @param project the project id
     * @return self for method chaining
     */
    IOSClientBuilder scopeToProject(Identifier project);

    /**
     * Scopes the token to a domain level
     *
     * @param domain the domain ID or Name value
     * @return self for method chaining
     */
    IOSClientBuilder scopeToDomain(Identifier domain);

    /**
     * Scopes the token to a specific trust
     *
     * @param id the trust id
     * @return self for method chaining
     */
    IOSClientBuilder scopeToTrust(String id);

    /**
     * Attempts to connect, authenticated and obtain an authorization access entity which contains a token, service catalog and endpoints
     * from the controller. As a result a client will be returned encapsulating the authorized access and corresponding API access
     *
     * @return the authenticated client
     * @throws AuthenticationException if the credentials or default tenant is invalid
     */
    OSClient authenticate() throws AuthenticationException;

}

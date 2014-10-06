package org.openstack4j.openstack;

import org.openstack4j.api.OSClient;
import org.openstack4j.api.exceptions.AuthenticationException;
import org.openstack4j.api.types.Facing;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.internal.HttpExecutor;
import org.openstack4j.model.identity.Access;
import org.openstack4j.openstack.OSFactory.OSFactoryBuilder.OSFactoryBuilderV3;
import org.openstack4j.openstack.identity.domain.Credentials;
import org.openstack4j.openstack.identity.domain.KeystoneAccess;
import org.openstack4j.openstack.identity.domain.v3.KeystoneAuth;
import org.openstack4j.openstack.internal.OSAuthenticator;
import org.openstack4j.openstack.internal.OSClientSession;

/**
 * A Factory which sets up the APIs to be used a previously non-expired authorization or new authorization.
 * 
 * @author Jeremy Unruh
 *
 */
public abstract class OSFactory<T extends OSFactory<T>> {

	String endpoint;
	String user;
	String password;
	boolean useNonStrictSSL;
	Facing perspective;

	private OSFactory() { }

	/**
	 * Skips Authentication and created the API around a previously cached Access object.  This can be useful in multi-threaded environments
	 * or scenarios where a client should not be re-authenticated due to a token lasting 24 hours
	 * @param access an authorized access entity which is to be used to create the API
	 * 
	 * @return the OSCLient
	 */
	public static OSClient clientFromAccess(Access access) {
		return OSClientSession.createSession((KeystoneAccess) access);
	}

	public static OSFactoryBuilder builder() {
		return new OSFactoryBuilder();
	}
	
	public static OSFactoryBuilderV3 builderV3() {
		return new OSFactoryBuilderV3();
	}

	/**
	 * The identity endpoint to connect to
	 * @param endpoint the endpoint URL of the identity service
	 * @return self for method chaining
	 */
	@SuppressWarnings("unchecked")
	public T endpoint(String endpoint) {
		this.endpoint = endpoint;
		return (T) this;
	}
	
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
	@SuppressWarnings("unchecked")
	public T perspective(Facing perspective) {
		this.perspective = perspective;
		return (T) this;
	}

	/**
	 * The authentication credentials
	 * 
	 * @param user the user to authenticate with
	 * @param password the password to authenticate with
	 * @return self for method chaining
	 */
	@SuppressWarnings("unchecked")
	public T credentials(String user, String password) {
		this.user = user;
		this.password = password;
		return (T) this;
	}

	/**
	 * In some private environments self signed certificates are used.  If you are using HTTPS and using
	 * self-signed cerificates then set this to true.  Otherwise the default strict hostname and properly
	 * signed validation based client will be used.
	 * 
	 * @param useNonStrictSSL true if an HTTPS self-signed environment
	 * @return self for method chaining
	 */
	@SuppressWarnings("unchecked")
	public T useNonStrictSSLClient(boolean useNonStrictSSL) {
		this.useNonStrictSSL = useNonStrictSSL;
		return (T) this;
	}
	/**
	 * Attempts to connect, authenticated and obtain an authorization access entity which contains a token, service catalog and endpoints
	 * from the controller. As a result a client will be returned encapsulating the authorized access and corresponding API access
	 * 
	 * @return the authenticated client
	 * @throws AuthenticationException if the credentials or default tenant is invalid
	 */
	public abstract OSClient authenticate() throws AuthenticationException;
	
	/**
	 * Attempts to connect, authenticated ,but not create session,just return whether credentials and tenant right
	 * from the controller. As a result a client will be returned encapsulating the authorized access and corresponding API access
	 * 
	 * @return if username,password,tenant is right
	 */
	public abstract boolean identifyUser();

	public static class OSFactoryBuilder extends OSFactory<OSFactoryBuilder> {

		String tenantName;
		String tenantId;

		/**
		 * The tenant/project to authenticate as
		 * @param tenantName the tenant/project name
		 * @return self for method chaining
		 */
		public OSFactoryBuilder tenantName(String tenantName) {
			this.tenantName = tenantName;
			return this;
		}

		/**
		 * The tenant/project to authenticate as (some clouds such as HP use tenantId vs tenantName)
		 * @param tenantId the tenant/project id
		 * @return self for method chaining
		 */
		public OSFactoryBuilder tenantId(String tenantId) {
			this.tenantId = tenantId;
			return this;
		}

		@Override
		public OSClient authenticate() throws AuthenticationException {
			return OSAuthenticator.invoke(new Credentials(user, password, tenantName, tenantId), endpoint, perspective, useNonStrictSSL);
		}
		
		@Override
		public boolean identifyUser() {
			try 
			{
				Credentials credentials = new Credentials(user, password, tenantName, tenantId);
				HttpRequest<KeystoneAccess> request = HttpRequest.builder(KeystoneAccess.class).useNonStrictSSL(useNonStrictSSL).endpoint(endpoint).method(HttpMethod.POST).path("/tokens").entity(credentials).build();
				HttpExecutor.create().execute(request).getEntity(KeystoneAccess.class);
				return Boolean.TRUE;
			} catch (AuthenticationException ae)
			{
				return Boolean.FALSE;
			}
		}

		public static class OSFactoryBuilderV3 extends OSFactory<OSFactoryBuilderV3> {

			String domainId;
			String domainName;
			
			/**
			 * Authenticates against the specified domain name
			 * 
			 * @param domainName the domain name to authenticate against
			 * @return OSFactoryBuilderV3
			 */
			public OSFactoryBuilderV3 domainName(String domainName) {
				this.domainName = domainName;
				return this;
			}
			
			/**
			 * Authenticates against the specified domain identifier
			 * 
			 * @param domainId the domain identifier to authenticate against
			 * @return OSFactoryBuilderV3
			 */
			public OSFactoryBuilderV3 domainId(String domainId) { 
				this.domainId = domainId;
				return this;
			}

			@Override
			public OSClient authenticate() throws AuthenticationException {
				return OSAuthenticator.invoke(new KeystoneAuth(user, password, domainName, domainId), endpoint, perspective, useNonStrictSSL);
			}

			@Override
			public boolean identifyUser() {
				try 
				{
					KeystoneAuth credentials = new KeystoneAuth(user, password, domainId, domainId);
					HttpRequest<KeystoneAccess> request = HttpRequest.builder(KeystoneAccess.class).useNonStrictSSL(useNonStrictSSL).endpoint(endpoint).method(HttpMethod.POST).path("/auth/tokens").entity(credentials).build();
					HttpExecutor.create().execute(request).getEntity(KeystoneAccess.class);
					return Boolean.TRUE;
				} catch (AuthenticationException ae)
				{
					return Boolean.FALSE;
				}
			}

		}
	}

}

package org.openstack4j.openstack;

import org.openstack4j.api.OSClient;
import org.openstack4j.api.exceptions.AuthenticationException;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.internal.HttpExecutor;
import org.openstack4j.model.identity.Access;
import org.openstack4j.openstack.identity.domain.Credentials;
import org.openstack4j.openstack.identity.domain.KeystoneAccess;
import org.openstack4j.openstack.internal.OSClientSession;

/**
 * A Factory which sets up the APIs to be used a previously non-expired authorization or new authorization.
 * 
 * @author Jeremy Unruh
 *
 */
public class OSFactory {

		String endpoint;
		String user;
		String tenantName;
		String password;
		String tenantId;
		boolean useNonStrictSSL;
		
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
		
		public static OSFactory builder() {
			return new OSFactory();
		}
		
		/**
		 * The identity endpoint to connect to
		 * @param endpoint the endpoint URL of the identity service
		 * @return self for method chaining
		 */
		public OSFactory endpoint(String endpoint) {
			this.endpoint = endpoint;
			return this;
		}
		
		/**
		 * The authentication credentials
		 * 
		 * @param user the user to authenticate with
		 * @param password the password to authenticate with
		 * @return self for method chaining
		 */
		public OSFactory credentials(String user, String password) {
		  this.user = user;
			this.password = password;
			return this;
		}
		
		/**
		 * The tenant/project to authenticate as
		 * @param tenantName the tenant/project name
		 * @return self for method chaining
		 */
		public OSFactory tenantName(String tenantName) {
			this.tenantName = tenantName;
			return this;
		}
		
		/**
		 * The tenant/project to authenticate as (some clouds such as HP use tenantId vs tenantName)
		 * @param tenantId the tenant/project id
		 * @return self for method chaining
		 */
		public OSFactory tenantId(String tenantId) {
			this.tenantId = tenantId;
			return this;
		}
		
		/**
		 * In some private environments self signed certificates are used.  If you are using HTTPS and using
		 * self-signed cerificates then set this to true.  Otherwise the default strict hostname and properly
		 * signed validation based client will be used.
		 * 
		 * @param useNonStrictSSL true if an HTTPS self-signed environment
		 * @return self for method chaining
		 */
		public OSFactory useNonStrictSSLClient(boolean useNonStrictSSL) {
			this.useNonStrictSSL = useNonStrictSSL;
			return this;
		}
		/**
		 * Attempts to connect, authenticated and obtain an authorization access entity which contains a token, service catalog and endpoints
		 * from the controller. As a result a client will be returned encapsulating the authorized access and corresponding API access
		 * 
		 * @return the authenticated client
		 * @throws AuthenticationException if the credentials or default tenant is invalid
		 */
		public OSClient authenticate() throws AuthenticationException {
			Credentials credentials = new Credentials(user, password, tenantName, tenantId);
			HttpRequest<KeystoneAccess> request = HttpRequest.builder(KeystoneAccess.class).endpoint(endpoint).method(HttpMethod.POST).path("/tokens").entity(credentials).build();
			KeystoneAccess access = HttpExecutor.create().execute(request, useNonStrictSSL).getEntity(KeystoneAccess.class);
			return OSClientSession.createSession(access.applyContext(endpoint, credentials), useNonStrictSSL);
		}
		
}

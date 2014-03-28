package org.openstack4j.openstack;

import org.openstack4j.api.OSClient;
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
		
		public OSFactory endpoint(String endpoint) {
			this.endpoint = endpoint;
			return this;
		}
		
		public OSFactory credentials(String user, String password) {
		  this.user = user;
			this.password = password;
			return this;
		}
		
		public OSFactory tenantName(String tenantName) {
			this.tenantName = tenantName;
			return this;
		}
		
		/**
		 * Attempts to connect, authenticated and obtain an authorization access entity which contains a token, service catalog and endpoints
		 * from the controller. As a result a client will be returned encapsulating the authorized access and corresponding API access
		 * 
		 * @return the authenticated client
		 */
		public OSClient authenticate() {
			Credentials credentials = new Credentials(user, password, tenantName);
			HttpRequest<KeystoneAccess> request = HttpRequest.builder(KeystoneAccess.class).endpoint(endpoint).method(HttpMethod.POST).path("/tokens").entity(credentials).build();
			KeystoneAccess access = HttpExecutor.create().execute(request).getEntity(KeystoneAccess.class);
			return OSClientSession.createSession(access.applyContext(endpoint, credentials));
		}
		
}

package org.openstack4j.openstack;

import org.openstack4j.api.OSClient;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.internal.HttpExecutor;
import org.openstack4j.openstack.identity.domain.Credentials;
import org.openstack4j.openstack.identity.domain.KeystoneAccess;
import org.openstack4j.openstack.internal.OSClientSession;

public class OSFactory {

		String endpoint;
		String user;
		String tenantName;
		String password;

		private OSFactory() { }
		
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
		
		public OSClient authenticate() {
			HttpRequest<KeystoneAccess> request = HttpRequest.builder(KeystoneAccess.class).endpoint(endpoint).method(HttpMethod.POST).path("/tokens").entity(new Credentials(user, password, tenantName)).build();
			KeystoneAccess access = HttpExecutor.create().execute(request).getEntity(KeystoneAccess.class);
			return OSClientSession.createSession(access, endpoint);
		}
		
}

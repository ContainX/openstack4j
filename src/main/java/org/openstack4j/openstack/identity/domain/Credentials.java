package org.openstack4j.openstack.identity.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("auth")
public class Credentials extends Auth {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="passwordCredentials")
	private PasswordCredentials passwordCreds = new PasswordCredentials();
	
	
	public Credentials() { }
	
	public Credentials(String username, String password) {
		passwordCreds.setCredentials(username, password);
	}
	
	public Credentials(String username, String password, String tenantName) {
		passwordCreds.setCredentials(username, password);
		setTenantName(tenantName);
	}
	
	public Credentials(String username, String password, String tenantName, String tenantId) {
		passwordCreds.setCredentials(username, password);
		setTenantName(tenantName);
		setTenantId(tenantId);
	}
	
	@JsonIgnore
	public String getUsername() {
		return passwordCreds.username;
	}
	
	@JsonIgnore
	public String getPassword() {
		return passwordCreds.password;
	}
	
	private static final class PasswordCredentials {
		 
		@JsonProperty
		 String username;
		 @JsonProperty
		 String password;
		 
		 public void setCredentials(String username, String password) {
			 this.username = username;
			 this.password = password;
		 }
	}

}

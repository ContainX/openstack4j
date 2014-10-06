package org.openstack4j.openstack.identity.domain;

import org.openstack4j.model.identity.AuthStore;
import org.openstack4j.model.identity.AuthVersion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("auth")
public class Credentials extends Auth implements AuthStore {

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

    @SuppressWarnings("unchecked")
    @JsonIgnore
    @Override
    public <T> T unwrap() {
        return (T) this;
    }

    @JsonIgnore
    @Override
    public String getId() {
        return getTenantId();
    }

    @JsonIgnore
    @Override
    public String getName() {
        return getTenantName();
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

    @JsonIgnore
    @Override
    public AuthVersion getVersion() {
        return AuthVersion.V2;
    }
}

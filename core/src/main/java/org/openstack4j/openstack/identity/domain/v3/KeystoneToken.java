package org.openstack4j.openstack.identity.domain.v3;

import java.util.Date;
import java.util.List;

import org.openstack4j.model.identity.AuthStore;
import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.openstack.identity.domain.TokenAuth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

@JsonRootName("token")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeystoneToken implements Token {

    private static final long serialVersionUID = 1L;

    @JsonProperty("expires_at")
    private Date expires;
    @JsonProperty("issued_at")
    private Date issued;
    @JsonProperty("audit_ids")
    private List<String> auditIds;
    @JsonProperty
    private List<String> methods;
    @JsonProperty
    private List<KeystoneRole> roles;
    @JsonProperty(required = false)
    private KeystoneProject project;
    @JsonProperty(required = false)
    private KeystoneDomain domain;
    @JsonProperty
    private KeystoneUser user;
    @JsonProperty
    private List<KeystoneCatalog> catalog;
    private KeystoneAuth credentials;
    private String endpoint;
    @JsonIgnore
    private String extras;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
                .add("expires", expires)
                .add("issued", issued)
                .add("methods", methods)
                .add("user", user)
                .add("roles", roles)
                .add("project", project)
                .add("domain", domain)
                .addValue("\n")
                .add("catalog", catalog)
                .toString();
    }

    @Override
    public Date getExpires() {
        return expires;
    }

    @Override
    public AuthVersion getVersion() {
        return AuthVersion.V3;
    }

    @Override
    public Date getIssuedAt() {
        return issued;
    }

    @Override
    public List<String> getAuditIds() {
        return auditIds;
    }

    @Override
    public List<String> getMethods() {
        return methods;
    }

    @Override
    public List<? extends KeystoneCatalog> getCatalog() {
        return catalog;
    }

    @Override
    public KeystoneProject getProject() {
        return project;
    }

    @Override
    public KeystoneDomain getDomain() {
        return domain;
    }

    @Override
    public KeystoneUser getUser() {
        return user;
    }

    @Override
    public List<? extends KeystoneRole> getRoles() {
        return roles;
    }

    public KeystoneAuth getCredentials() {
        return credentials;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public KeystoneToken applyContext(String endpoint, KeystoneAuth credentials) {
        this.credentials = credentials;
        this.endpoint = endpoint;
        return this;
    }

    public KeystoneToken applyContext(String endpoint, TokenAuth token) {
        this.endpoint = endpoint;
        return this;
    }

    public KeystoneToken applyContext(String endpoint, AuthStore credentials) {
        this.endpoint = endpoint;
        this.credentials = new KeystoneAuth(endpoint, credentials.getPassword());
        return this;
    }
}

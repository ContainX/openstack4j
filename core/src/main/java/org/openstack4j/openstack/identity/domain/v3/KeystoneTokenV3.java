package org.openstack4j.openstack.identity.domain.v3;

import java.util.Date;
import java.util.List;

import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.model.identity.v3.Catalog;
import org.openstack4j.model.identity.v3.TokenV3;
import org.openstack4j.openstack.common.BasicResourceEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

@SuppressWarnings("serial")
@JsonRootName("token")
public class KeystoneTokenV3 implements TokenV3 {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    public String id;
    @JsonProperty("expires_at")
    private Date expires;
    @JsonProperty("issued_at")
    private Date issued;
    @JsonProperty("methods")
    private List<String> methods;

    @JsonProperty("roles")
    List<KeystoneRoleV3> roles;
    @JsonProperty("project")
    private KeystoneProjectV3 project;

    @JsonProperty
    private List<KeystoneCatalog> catalog;

    @JsonIgnore
    private KeystoneAuth credentials;
    @JsonIgnore
    private String endpoint;

    @Override 
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
                .add("id", id).add("expires", expires).add("issued", issued)
                .add("methods", methods).add("roles", roles).add("project", project)
                .addValue("\n").add("catalog", catalog)
                .toString();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Date getExpires() {
        return expires;
    }

    @JsonIgnore
    @Override
    public AuthVersion getVersion() {
        return AuthVersion.V3;
    }

    @Override
    public Date getIssuedAt() {
        return issued;
    }

    @Override
    public List<String> getMethods() {
        return methods;
    }

    @Override
    public List<? extends Catalog> getCatalog() {
        return catalog;
    }

    @JsonIgnore
    @Override
    public ProjectV3 getProject() {
        return project;
    }

    @JsonIgnore
    @Override
    public List<? extends RoleV3> getRoles() {
        return roles;
    }
    
    @JsonIgnore
    public KeystoneAuth getCredentials() {
        return credentials;
    }

    @JsonIgnore
    public String getEndpoint() {
        return this.endpoint;
    }
    
    public KeystoneTokenV3 applyContext(String endpoint, KeystoneAuth credentials) {
        this.endpoint = endpoint;
        this.credentials = credentials;
        return this;
    }

    public static class KeystoneRoleV3 extends BasicResourceEntity implements RoleV3 {
    }

    public static class KeystoneProjectV3 extends BasicResourceEntity implements ProjectV3 {

        @JsonProperty
        private KeystoneDomainV3 domain;

        @Override
        public DomainV3 getDomain() {
            return domain;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(getClass()).omitNullValues()
                    .add("id", getId()).add("name", getName()).add("domain", domain)
                    .toString();
        }

        public static class KeystoneDomainV3 extends BasicResourceEntity implements DomainV3 {
        }

    }
}

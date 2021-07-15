package org.openstack4j.openstack.identity.v3.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.openstack4j.model.identity.v3.ApplicationCredential;
import org.openstack4j.model.identity.v3.Role;
import org.openstack4j.model.identity.v3.builder.ApplicationCredentialBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@JsonRootName("application_credential")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeystoneApplicationCredential implements ApplicationCredential {

    private String id;
    private String secret;
    @JsonProperty("project_id")
    private String projectId;
    private String name;
    private String description;
    private Map<String, String> links;
    @JsonProperty("expires_at")
    private Date expiresAt;
    private Boolean unrestricted;
    @JsonDeserialize(contentAs = KeystoneRole.class)
    private List<? extends Role> roles = new ArrayList<>();

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Map<String, String> getLinks() {
        return links;
    }

    @Override
    public Date getExpiresAt() {
        return expiresAt;
    }

    @Override
    public Boolean getUnrestricted() {
        return unrestricted;
    }

    @Override
    public List<? extends Role> getRoles() {
        return roles;
    }

    @Override
    public ApplicationCredentialBuilder toBuilder() {
        return new ApplicationCredentialConcreteBuilder(this);
    }

    public static ApplicationCredentialBuilder builder() {
        return new ApplicationCredentialConcreteBuilder();
    }

    public static final class ApplicationCredentialConcreteBuilder implements ApplicationCredentialBuilder {

        KeystoneApplicationCredential model;

        public ApplicationCredentialConcreteBuilder(KeystoneApplicationCredential model) {
            this.model = model;
        }

        public ApplicationCredentialConcreteBuilder() {
            this(new KeystoneApplicationCredential());
        }

        @Override
        public ApplicationCredentialBuilder id(String id) {
            model.id = id;
            return this;
        }

        @Override
        public ApplicationCredentialBuilder secret(String secret) {
            model.secret = secret;
            return this;
        }

        @Override
        public ApplicationCredentialBuilder name(String name) {
            model.name = name;
            return this;
        }

        @Override
        public ApplicationCredentialBuilder description(String description) {
            model.description = description;
            return this;
        }

        @Override
        public ApplicationCredentialBuilder projectId(String projectId) {
            model.projectId = projectId;
            return this;
        }

        @Override
        public ApplicationCredentialBuilder roles(List<? extends Role> roles) {
            model.roles = roles;
            return this;
        }

        @Override
        public ApplicationCredentialBuilder unrestricted(Boolean unrestricted) {
            model.unrestricted = unrestricted;
            return this;
        }

        @Override
        public ApplicationCredentialBuilder expiresAt(Date expiresAt) {
            model.expiresAt = expiresAt;
            return this;
        }

        @Override
        public ApplicationCredentialBuilder links(Map<String, String> links) {
            model.links = links;
            return this;
        }

        @Override
        public ApplicationCredential build() {
            return model;
        }

        @Override
        public ApplicationCredentialBuilder from(ApplicationCredential in) {
            if (null != in) {
                this.model = (KeystoneApplicationCredential) in;
            }
            return this;
        }
    }

    public static final class ApplicationCredentials extends ListResult<KeystoneApplicationCredential> {
        private static final long serialVersionUID = 1L;

        @JsonProperty("application_credentials")
        private List<KeystoneApplicationCredential> applicationCredentials;

        @Override
        protected List<KeystoneApplicationCredential> value() {
            return applicationCredentials;
        }
    }
}

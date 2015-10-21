package org.openstack4j.openstack.identity.domain.v3;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.identity.builder.v3.UserBuilder;
import org.openstack4j.model.identity.v3.Domain;
import org.openstack4j.model.identity.v3.User;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

/**
 * User model class for identity.v3
 * 
 * @see <a href=
 *      "http://developer.openstack.org/api-ref-identity-v3.html#users-v3">API
 *      reference</a>
 */
@JsonRootName("user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeystoneUser implements User {

    private static final long serialVersionUID = 1L;
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private KeystoneDomain domain;
    private String domainId;
    private String email;
    private String password;
    private String description;
    private String defaultProjectId;
    private Map<String, String> links;
    private Boolean enabled = true;

    /**
     * @return the user builder
     */
    public static UserBuilder builder() {
        return new UserConcreteBuilder();
    }

    @Override
    public UserBuilder toBuilder() {
        return new UserConcreteBuilder(this);
    }

    /**
     * @return the id of the user
     */
    public String getId() {
        return id;
    }

    /**
     * @return the of the user
     */
    public String getName() {
        return name;
    }

    /**
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the description of the user
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the domainId of the user
     */
    public String getDomainId() {
        return domainId;
    }

    /**
     * @return the domain of the user
     */
    public Domain getDomain() {
        return domain;
    }

    /**
     * @return the defaultProjectId of the user
     */
    public String getDefaultProjectId() {
        return defaultProjectId;
    }

    /**
     * @return the links of the user
     */
    public Map<String, String> getLinks() {
        return links;
    }

    /**
     * @return the enabled of the user
     */
    public boolean isEnabled() {
        return enabled != null && enabled;
    }

    /**
     * set user enabled
     * 
     * @param enabled
     *            the new enabled status
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
                .add("name", name)
                .add("id", id)
                .add("email", email)
                .add("password", password)
                .add("description", description)
                .add("domainId", domainId)
                .add("links", links)
                .add("enabled", enabled)
                .add("defaultProjectId", defaultProjectId)
                .toString();
    }

    public static class Users extends ListResult<KeystoneUser> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("users")
        private List<KeystoneUser> list;

        public List<KeystoneUser> value() {
            return list;
        }
    }

    public static class UserConcreteBuilder implements UserBuilder {

        KeystoneUser model;

        UserConcreteBuilder() {
            this(new KeystoneUser());
        }

        UserConcreteBuilder(KeystoneUser model) {
            this.model = model;
        }

        /**
         * @see KeystoneUser#getId()
         */
        public UserBuilder id(String id) {
            model.id = id;
            return this;
        }

        /**
         * @return the KeystoneUser model
         */
        public User build() {
            return model;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public UserBuilder from(User in) {
            if (in != null)
                this.model = (KeystoneUser) in;
            return this;
        }

        /**
         * @see KeystoneUser#getName()
         */
        public UserBuilder name(String name) {
            model.name = name;
            return this;
        }

        /**
         * @see KeystoneUser#getDefaultProjectId()
         */
        public UserBuilder defaultProjectId(String defaultProjectId) {
            model.defaultProjectId = defaultProjectId;
            return this;
        }

        /**
         * @see KeystoneUser#getDomainId()
         */
        public UserBuilder domainId(String domainId) {
            model.domainId = domainId;
            return this;
        }

        /**
         * @see KeystoneUser#getDomain()
         */
        public UserBuilder domain(Domain domain) {
            // model.domain = domain;
            if (domain != null && domain.getId() != null)
                model.domainId = domain.getId();
            return this;
        }

        /**
         * @see KeystoneUser#getEmail()
         */
        public UserBuilder email(String email) {
            model.email = email;
            return this;
        }

        /**
         * @see KeystoneUser#getPassword()
         */
        public UserBuilder password(String password) {
            model.password = password;
            return this;
        }

        /**
         * @see KeystoneUser#getLinks()
         */
        public UserBuilder links(Map<String, String> links) {
            model.links = links;
            return this;
        }

        /**
         * @see KeystoneUser#isEnabled()
         */
        public UserBuilder enabled(boolean enabled) {
            model.enabled = enabled;
            return this;
        }

        /**
         * @see KeystoneUser#getDescription()
         */
        public UserBuilder description(String description) {
            model.description = description;
            return this;
        }
    }

}

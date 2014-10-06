package org.openstack4j.openstack.identity.domain.v3;

import java.util.List;

import org.openstack4j.model.identity.AuthStore;
import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.model.identity.v3.Authentication;
import org.openstack4j.openstack.common.BasicResourceEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.collect.Lists;

@JsonRootName("auth")
public class KeystoneAuth implements Authentication, AuthStore {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private AuthIdentity identity;
    private AuthScope scope;

    public KeystoneAuth(String username, String password) {
        this(username, password, null, null);
    }

    public KeystoneAuth(String username, String password, String domainName, String domainId) {
        identity = AuthIdentity.createCredentialType(username, password, domainName, domainId);
    }

    @Override
    public Identity getIdentity() {
        return identity;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @SuppressWarnings("unchecked")
    @Override
    @JsonIgnore
    public <T> T unwrap() {
        return (T) this;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return identity.getPassword().getUser().getName();
    }

    @Override
    public String getPassword() {
        return identity.getPassword().getUser().getPassword();
    }

    @Override
    @JsonIgnore
    public String getId() {
        return identity.getPassword().getUser().getDomain().getId();
    }

    @Override
    @JsonIgnore
    public String getName() {
        return identity.getPassword().getUser().getDomain().getName();
    }

    public static final class AuthIdentity implements Identity {

        private AuthPassword password;
        private AuthToken token;
        private List<String> methods = Lists.newArrayList();

        static AuthIdentity createCredentialType(String username, String password) {
            return createCredentialType(username, password, null, null);
        }

        static AuthIdentity createCredentialType(String username, String password, String domainName, String domainId) {
            AuthIdentity identity = new AuthIdentity();
            identity.password = new AuthPassword(username, password, domainName, domainId);
            identity.methods.add("password");
            return identity;
        }

        @Override
        public Password getPassword() {
            return password;
        }

        @Override
        public Token getToken() {
            return token;
        }

        @Override
        public List<String> getMethods() {
            return methods;
        }

        public static final class AuthToken implements Token {

            private String id;

            @Override
            public String getId() {
                return id;
            }
        }

        public static final class AuthPassword implements Password {

            private AuthUser user;

            public AuthPassword() { }

            public AuthPassword(String username, String password, String domainName, String domainId) {
                this.user = new AuthUser(username, password, domainName, domainId);
            }

            @Override
            public User getUser() {
                return user;
            }

            public static final class AuthUser extends BasicResourceEntity implements User {

                private static final long serialVersionUID = 1L;

                private AuthDomain domain;
                private String password;

                public AuthUser() {
                }

                public AuthUser(String username, String password, String domainName, String domainId) {
                    this.password = password;
                    if (domainName != null || domainId != null) {
                        domain = new AuthDomain();
                        domain.setName(domainName);
                        setName(username);
                    }
                    else
                        setId(username);
                }


                @Override
                public Domain getDomain() {
                    return domain;
                }

                @Override
                public String getPassword() {
                    return password;
                }

                public static final class AuthDomain extends BasicResourceEntity implements Domain {

                    private static final long serialVersionUID = 1L;

                }
            }

        }
    }

    public static final class AuthScope implements Scope {

        private AuthProject project;

        @Override
        public Project getProject() {
            return project;
        }

        public static final class AuthProject extends BasicResourceEntity implements Project {

            private static final long serialVersionUID = 1L;
            private AuthDomain domain;

            @Override
            public Domain getDomain() {
                return domain;
            }

            public static final class AuthDomain extends BasicResourceEntity implements Domain {

                private static final long serialVersionUID = 1L;

            }
        }
    }

    @JsonIgnore
    @Override
    public AuthVersion getVersion() {
        return AuthVersion.V3;
    }
}

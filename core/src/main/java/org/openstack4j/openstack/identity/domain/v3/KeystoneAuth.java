package org.openstack4j.openstack.identity.domain.v3;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.identity.AuthStore;
import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.model.identity.v3.Authentication;
import org.openstack4j.openstack.common.BasicResourceEntity;
import org.openstack4j.openstack.common.IdResourceEntity;

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

    public KeystoneAuth(String tokenId) {
        this.identity = AuthIdentity.createTokenType(tokenId);
    }
    
    public KeystoneAuth(String username, String password) {
        this(username, password, null, null);
    }

    public KeystoneAuth(String username, String password, Identifier domain, AuthScope scope) {
        this.identity = AuthIdentity.createCredentialType(username, password, domain);
        this.scope = scope;
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

    @JsonIgnore
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

        static AuthIdentity createTokenType(String tokenId) {
            AuthIdentity identity = new AuthIdentity();
            identity.methods.add("token");
            identity.token = new AuthToken(tokenId);
            return identity;
        }
        
        static AuthIdentity createCredentialType(String username, String password) {
            return createCredentialType(username, password, null);
        }

        static AuthIdentity createCredentialType(String username, String password, Identifier domain) {
            AuthIdentity identity = new AuthIdentity();
            identity.password = new AuthPassword(username, password, domain);
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

            @JsonProperty
            private String id;

            AuthToken() { }
            
            AuthToken(String id) {
                this.id = id;
            }
            
            @Override
            public String getId() {
                return id;
            }
        }

        public static final class AuthPassword implements Password {

            private AuthUser user;

            public AuthPassword() { }

            public AuthPassword(String username, String password, Identifier domain) {
                this.user = new AuthUser(username, password, domain);
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

                public AuthUser(String username, String password, Identifier domainIdentifier) {
                    this.password = password;
                    if (domainIdentifier != null) {
                        domain = new AuthDomain();
                        if (domainIdentifier.isTypeID())
                            domain.setId(domainIdentifier.getId());
                        else
                            domain.setName(domainIdentifier.getId());
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

        @JsonProperty("project")
        private ScopeProject project;

        @JsonProperty("domain")
        private AuthDomain domain;
        
        @JsonProperty("OS-TRUST:trust")
        private ScopeTrust trust;
        
        public AuthScope(ScopeProject project) {
            this.project = project;
        }
        
        public AuthScope(AuthDomain domain) {
            this.domain = domain;
        }
        public AuthScope(ScopeTrust trust) {
            this.trust = trust;
        }
        
        public static AuthScope project(Identifier project, Identifier domain) {
            return new AuthScope(ScopeProject.create(project, domain));
        }
        
        public static AuthScope domain(Identifier domain) {
            checkNotNull(domain, "Domain Scope: domain identifier or name cannot be null");
            return new AuthScope(new AuthDomain(domain));
        }
        
        public static AuthScope trust(String id) {
            checkNotNull(id, "Trust Scope: trust id cannot be null");
            return new AuthScope(new ScopeTrust(id));
        }
        
        @Override
        public Project getProject() {
            return project;
        }
        
        public static final class ScopeProject extends BasicResourceEntity implements Project {

            private static final long serialVersionUID = 1L;
            private AuthDomain domain;
            @JsonProperty
            private String id;
            @JsonProperty
            private String name;

            public static ScopeProject create(Identifier project, Identifier domain) {
               checkNotNull(project, "Project Scope: project identifier or name cannot be null");
               checkNotNull(domain, "Project Scope: domain identifier or name cannot be null");
               
               ScopeProject scope = new ScopeProject();
               scope.domain = new AuthDomain(domain);
               if (project.isTypeID()) {
                   scope.id = project.getId();
               }
               else {
                   scope.name = project.getId();
               }
               return scope;
            }
            
            @Override
            public Domain getDomain() {
                return domain;
            }
            
            public String getId() {
                return id;
            }
            
            public String getName() {
                return name;
            }
        }
        
        public static final class AuthDomain extends BasicResourceEntity implements Domain {

            private static final long serialVersionUID = 1L;

            @JsonProperty
            private String id;
            @JsonProperty
            private String name;
            
            public AuthDomain(Identifier domain) {
                if (domain.isTypeID())
                    this.id = domain.getId();
                else
                    this.name = domain.getId();
            }
            
            public String getId() {
                return id;
            }
            
            public String getName() {
                return name;
            }
        }
        
        public static final class ScopeTrust extends IdResourceEntity {

            private static final long serialVersionUID = 1L;

            public ScopeTrust(String id) {
                this.setId(id);
            }

        }
    }

    @JsonIgnore
    @Override
    public AuthVersion getVersion() {
        return AuthVersion.V3;
    }
}

package org.openstack4j.openstack.identity.domain.v3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.common.Link;
import org.openstack4j.model.identity.Access;
import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.model.identity.Endpoint;
import org.openstack4j.model.identity.Role;
import org.openstack4j.model.identity.Tenant;
import org.openstack4j.model.identity.TokenV2;
import org.openstack4j.model.identity.v3.Catalog;
import org.openstack4j.model.identity.v3.Project;
import org.openstack4j.model.identity.v3.User;
import org.openstack4j.openstack.identity.domain.KeystoneTenant;
import org.openstack4j.openstack.identity.functions.ServiceFunctions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.SortedSetMultimap;

/**
 * This class wraps V3 objects and satisfies the original V2 API calls to bridge
 * the differences between V2 and V3
 * 
 * @author Jeremy Unruh
 */
public class AccessWrapper implements Access {

    private static final long serialVersionUID = 1L;
    private String id;

    KeystoneToken token;

    private AccessWrapper(KeystoneToken token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Wraps the V3 Token into a V2 Access type
     *
     * @param token
     *            the V3 token
     * @return the access wrapper
     */
    public static AccessWrapper wrap(KeystoneToken token) {
        return new AccessWrapper(token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.openstack4j.model.identity.Token getToken() {

        Tenant tenant = null;

        if (token.getProject() != null) {
            Project project = KeystoneProject.builder()
                    .id(token.getProject().getId())
                    .name(token.getProject().getName())
                    .build();

            tenant = KeystoneTenant.builder()
                    .id(project.getId())
                    .name(project.getName())
                    .description(project.getDescription())
                    .enabled(project.isEnabled())
                    .build();
        }

        return new V2Token(id, token.getExpires(), token.getVersion(), tenant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Service> getServiceCatalog() {
        List<? extends Catalog> catalogs = this.token.getCatalog();
        List<Service> services = new ArrayList<Service>(catalogs.size());
        for (Catalog catalog : catalogs) {
            Service wrappedCatalog = ServiceWrapper.wrap(catalog);
            services.add(wrappedCatalog);
        }
        return services;
    }

    @Override
    public SortedSetMultimap<String, ? extends Service> getAggregatedCatalog() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails getUser() {
        return UserWrapper.wrap(KeystoneUser.builder()
                .id(token.getUser().getId())
                .name(token.getUser().getName())
                .domainId(token.getUser().getDomain().getId())
                .build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEndpoint() {
        return token.getEndpoint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthVersion getVersion() {
        return AuthVersion.V3;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T unwrap() {
        return (T) token;
    }

    public static class ServiceWrapper implements Service {

        Catalog catalog;

        private ServiceWrapper(Catalog catalog) {
            this.catalog = catalog;
        }

        static ServiceWrapper wrap(Catalog catalog) {
            ServiceWrapper wrapper = new ServiceWrapper(catalog);
            return wrapper;
        }

        @Override
        public String getType() {
            return catalog.getType();
        }

        @Override
        public String getName() {
            return getServiceType().getServiceName();
        }

        @Override
        public ServiceType getServiceType() {
            return ServiceType.forName(catalog.getType());
        }

        @Override
        public List<? extends Endpoint> getEndpoints() {
            return Collections.emptyList();
        }

        @Override
        public List<? extends Link> getEndpointsLinks() {
            return Collections.emptyList();
        }

        @Override
        public Integer getVersion() {
            return ServiceFunctions.VERSION_FROM_TYPE.apply(catalog.getType());
        }

    }

    public static class UserWrapper implements UserDetails {

        User user;

        private UserWrapper(User user) {
            this.user = user;
        }

        static UserWrapper wrap(User user) {
            UserWrapper wrapper = new UserWrapper(user);
            return wrapper;
        }

        @Override
        public String getId() {
            return user.getId();
        }

        @Override
        public String getName() {
            return user.getName();
        }

        @Override
        public String getUsername() {
            return user.getName();
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        @Override
        public List<? extends Role> getRoles() {
            return Collections.emptyList();
        }

        @Override
        public List<? extends Link> getRolesLinks() {
            return Collections.emptyList();
        }

    }

    private class V2Token implements TokenV2 {

        private static final long serialVersionUID = 1L;
        private String id;
        private Date expires;
        private AuthVersion version;
        private Tenant tenant;

        public V2Token(String id, Date expires, AuthVersion version, Tenant tenant) {
            super();
            this.id = id;
            this.expires = expires;
            this.version = version;
            this.tenant = tenant;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public Date getExpires() {
            return expires;
        }

        @Override
        public AuthVersion getVersion() {
            return version;
        }

        @Override
        public Tenant getTenant() {
            return tenant;
        }

    }

    @JsonIgnore
    @Override
    public String getCacheIdentifier() {
        return getEndpoint() + getToken().getId();
    }
}

package org.openstack4j.openstack.identity.domain;

import java.util.List;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.common.Link;
import org.openstack4j.model.identity.Access;
import org.openstack4j.model.identity.AuthStore;
import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.model.identity.Endpoint;
import org.openstack4j.model.identity.Role;
import org.openstack4j.model.identity.TokenV2;
import org.openstack4j.openstack.common.GenericLink;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

@JsonRootName("access")
public class KeystoneAccess implements Access {

	private static final long serialVersionUID = 1L;
	private static final String CACHE_FMT = "%s:%s";
	private KeystoneToken token;
	private List<AccessService> serviceCatalog;
	private AccessUser user;
	private String endpoint;
	private AuthStore credentials;

	/**
	 * @return the token
	 */
	public TokenV2 getToken() {
		return token;
	}

	/**
	 * @return the serviceCatalog
	 */
	public List<? extends Service> getServiceCatalog() {
		return serviceCatalog;
	}

	/**
	 * @return the user
	 */
	public UserDetails getUser() {
		return user;
	}

	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * ONLY used for unit tests
	 * @param endpoint
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public AuthStore getCredentials() {
		return credentials;
	}

	public KeystoneAccess applyContext(String endpoint, AuthStore credentials) {
		this.credentials = credentials;
		this.endpoint = endpoint;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				.add("token", token).add("serviceCatalog", serviceCatalog).add("user", user)
				.toString();
	}

	@JsonIgnoreProperties(ignoreUnknown=true)
	public static final class AccessUser implements UserDetails {

		private String id;
		private String name;
		private String username;
		private List<AccessRole> roles;
		private Boolean enabled;

		@JsonProperty("roles_links")
		private List<GenericLink> rolesLinks;

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getUsername() {
			return username;
		}

		public List<? extends Role> getRoles() {
			return roles;
		}

		public List<? extends Link> getRolesLinks() {
			return rolesLinks;
		}

		public boolean isEnabled() {
			return (enabled != null && enabled);
		}

		/**
		 * {@inheritDoc}
		 */
		public String toString() {
			return Objects.toStringHelper(this).omitNullValues()
					.add("id", id).add("name", name).add("username", username).add("enabled", enabled)
					.add("roles", roles).add("rolesLinks", rolesLinks)
					.toString();
		}

		@JsonIgnoreProperties(ignoreUnknown=true)
		public static final class AccessRole extends KeystoneRole
		{
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			public String toString() {
				return Objects.toStringHelper(this).omitNullValues()
						.add("id", getId()).add("name", getName()).add("tenantId", getTenantId())
						.toString();
			}
		}
	}

	public static class AccessUsers extends ListResult<AccessUser> {

		private static final long serialVersionUID = 1L;
		@JsonProperty("users")
		private List<AccessUser> list;

		public List<AccessUser> value() {
			return list;
		}
	}


	public static final class AccessService implements Service
	{
		private String type;
		private String name;
		private List<KeystoneEndpoint> endpoints;
		private ServiceType serviceType;

		@JsonProperty("endpoints_links")
		private List<GenericLink> endpointsLinks;

		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		@JsonIgnore
		public ServiceType getServiceType() {
			if (serviceType == null)
				serviceType = ServiceType.forName(name);
			return serviceType;
		}

		/**
		 * @return the endpoints
		 */
		public List<? extends Endpoint> getEndpoints() {
			return endpoints;
		}

		/**
		 * @return the endpointsLinks
		 */
		public List<? extends Link> getEndpointsLinks() {
			return endpointsLinks;
		}

		/**
		 * {@inheritDoc}
		 */
		public String toString() {
			return Objects.toStringHelper(this).omitNullValues()
					.add("name", name).add("type", type).add("endpoints", endpoints)
					.toString();
		}
	}

	@JsonIgnore
	@Override
	public AuthVersion getVersion() {
		return AuthVersion.V2;
	}

	@SuppressWarnings("unchecked")
	@JsonIgnore
	@Override
	public <T> T unwrap() {
		return (T) this;
	}

	@JsonIgnore
    @Override
    public String getCacheIdentifier() {
	    String uniq = null;
	    if (token.getTenant() != null)
	        uniq = token.getTenant().getId();
	    else if (user != null)
	        uniq = user.getId();
	    else
	        uniq = "";
	    return String.format(CACHE_FMT, endpoint, uniq);
	    
    }

}

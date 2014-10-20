package org.openstack4j.openstack.identity.domain.v3;

import java.util.Collections;
import java.util.List;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.common.Link;
import org.openstack4j.model.identity.Access;
import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.model.identity.Endpoint;
import org.openstack4j.model.identity.v3.Catalog;
import org.openstack4j.model.identity.v3.TokenV3;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * This class wraps V3 objects and satisfies the original V2 API calls to bridge the differences between V2 and V3
 * 
 * @author Jeremy Unruh
 */
public class AccessWrapper implements Access {
	
	private static final long serialVersionUID = 1L;
	
	KeystoneTokenV3 token;
	
	private AccessWrapper(KeystoneTokenV3 token) {
		this.token = token;
	}

	/**
	 * Wraps the V3 Token into a V2 Access type
	 *
	 * @param token the V3 token
	 * @return the access wrapper
	 */
	public static AccessWrapper wrap(KeystoneTokenV3 token) {
		return new AccessWrapper(token);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TokenV3 getToken() {
		return token;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Service> getServiceCatalog() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDetails getUser() {
		return null;
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
		
	}


	@JsonIgnore
    @Override
    public String getCacheIdentifier() {
        return getEndpoint() + getToken().getId();
    }
}

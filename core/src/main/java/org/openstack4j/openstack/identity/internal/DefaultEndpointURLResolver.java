package org.openstack4j.openstack.identity.internal;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openstack4j.api.exceptions.RegionEndpointNotFoundException;
import org.openstack4j.api.identity.EndpointURLResolver;
import org.openstack4j.api.types.Facing;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.identity.Access;
import org.openstack4j.model.identity.Access.Service;
import org.openstack4j.model.identity.Endpoint;
import org.openstack4j.model.identity.URLResolverParams;
import org.openstack4j.model.identity.v3.Catalog;
import org.openstack4j.model.identity.v3.EndpointV3;
import org.openstack4j.model.identity.v3.TokenV3;

/**
 * Resolves an Endpoint URL based on the Service Type and Facing perspective
 * 
 * @author Jeremy Unruh
 */
public class DefaultEndpointURLResolver implements EndpointURLResolver {

	private static final Map<Key, String> CACHE = new HashMap<Key, String>();
	private static final boolean LEGACY_EP_HANDLING = Boolean.getBoolean(LEGACY_EP_RESOLVING_PROP);
	private String publicHostIP;

	@Override
	public String findURL(URLResolverParams p) {

		if (p.type == null)
			return p.access.getEndpoint();
		Key key = Key.of(p.access.getCacheIdentifier(), p.type, p.perspective, p.region);

		String url = CACHE.get(key);

		if (url != null)
			return url;

		switch (p.access.getVersion()) {
			case V3:
				url = resolveV3(p);
				break;
			case V2:
			default:
				url = resolveV2(p);
		}

		if (url != null)
		{
			CACHE.put(key, url);
			return url;
		}
		else if (p.region != null)
		    throw RegionEndpointNotFoundException.create(p.region, p.type.getServiceName());
		
		return p.access.getEndpoint();
	}

	private String resolveV2(URLResolverParams p) {
		for (Service sc : p.access.getServiceCatalog()) {
			if (p.type.getServiceName().equals(sc.getName()) || p.type.name().toLowerCase().equals(sc.getType()) || p.type.getServiceName().equals(sc.getType()))
			{
				for (Endpoint ep : sc.getEndpoints())
				{
					if (p.region != null && !p.region.equalsIgnoreCase(ep.getRegion()))
						continue;
					
					if (sc.getServiceType() == ServiceType.NETWORK)
					{
						sc.getEndpoints().get(0).toBuilder().type(sc.getServiceType().name());
					}
	
					if (p.perspective == null)
						return getEndpointURL(p.access, ep);
	
					switch (p.perspective) {
						case ADMIN:
							return ep.getAdminURL().toString();
						case INTERNAL:
							return ep.getInternalURL().toString();
						case PUBLIC:
						default:
							return ep.getPublicURL().toString();
					}
				}
			}
		}
		return null;
	}

	private String resolveV3(URLResolverParams p) {
		TokenV3 token = p.access.unwrap();
		if (p.perspective == null)
			p.perspective = Facing.PUBLIC;

		for (Catalog catalog : token.getCatalog()) {
			if (p.type == ServiceType.forName(catalog.getType())) 
			{
				for (EndpointV3 ep : catalog.getEndpoints()) {
					// Since we only support V3 authentication - skip a V3 URL
					if (ep.getInterface() == p.perspective && !isEndpointV3(ep.getURL()))
						return ep.getURL().toString();
				}
			}
		}
		return null;
	}

	private boolean isEndpointV3(URL url) {
		return url.toString().contains("/v3");
	}

	/**
	 * Gets the endpoint url.
	 *
	 * @param access the current access data source
	 * @param endpoint the endpoint
	 * @return the endpoint url
	 */
	private String getEndpointURL(Access access, Endpoint endpoint) {
	    if (LEGACY_EP_HANDLING)
	    {
    		if (endpoint.getAdminURL() != null)
    		{
    			if (getPublicIP(access) != null && !getPublicIP(access).equals(endpoint.getAdminURL().getHost()))
    			{
    				return endpoint.getAdminURL().toString().replaceAll(endpoint.getAdminURL().getHost(), getPublicIP(access));
    			}
    			return endpoint.getAdminURL().toString();
    		}
	    }
		return endpoint.getPublicURL().toString();
	}

	private String getPublicIP(Access access) {
		if (publicHostIP == null) {
			try {
				publicHostIP = new URI(access.getEndpoint()).getHost();
			}
			catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return publicHostIP;
	}

	private static final class Key {
		private final String uid;
		private final ServiceType type;
		private final Facing perspective;

		private Key(String uid, ServiceType type, Facing perspective) {
			this.type = type;
			this.perspective = perspective;
			this.uid = uid;
		}

		static Key of(String uid, ServiceType type, Facing perspective, String region) {
			return new Key((region == null) ? uid : uid+region, type, perspective);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((perspective == null) ? 0 : perspective.hashCode());
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			result = prime * result + ((uid == null) ? 0 : uid.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Key other = (Key) obj;
			if (perspective != other.perspective)
				return false;
			if (type != other.type)
				return false;
			if (uid == null) {
				if (other.uid != null)
					return false;
			} else if (!uid.equals(other.uid))
				return false;
			return true;
		}


	}

}

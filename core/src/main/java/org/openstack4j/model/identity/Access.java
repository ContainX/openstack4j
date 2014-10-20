package org.openstack4j.model.identity;

import java.util.List;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.Link;

/**
 * Access is the entity returned when Authenticated by the Identity service
 * 
 * @author Jeremy Unruh
 */
public interface Access extends ModelEntity {

	/**
	 * @return the authorized token
	 */
	Token getToken();
	
	/**
	 * @return the service catalog
	 */
	List<? extends Service> getServiceCatalog();
	
	/**
	 * @return the original endpoint used to authenticate
	 */
	String getEndpoint();
	
	/**
	 * @return details about the current user
	 */
	UserDetails getUser();
	
	/**
	 * If Access is being wrapped such as in V3 then this will return the underlying wrapped instance.  Otherwise it returns itself
	 * 
	 * @return the unwrapped underlying data source
	 */
	<T> T unwrap();
	
	
	/**
	 * @return the internal UUID used for cache lookups of this access
	 */
	String getCacheIdentifier();
	
	/**
	 * @return the version of the authentication method
	 */
	AuthVersion getVersion();
	
	public interface UserDetails
	{
		String getId();

		String getName();

		String getUsername();
		
		boolean isEnabled();

		List<? extends Role> getRoles();

		List<? extends Link> getRolesLinks();
	}
	
	public interface Service
	{
		String getType();

		String getName();

		ServiceType getServiceType();

		List<? extends Endpoint> getEndpoints();

		List<? extends Link> getEndpointsLinks();
	}
}

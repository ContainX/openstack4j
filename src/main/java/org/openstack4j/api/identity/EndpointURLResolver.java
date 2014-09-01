package org.openstack4j.api.identity;

import org.openstack4j.model.identity.URLResolverParams;

/**
 * Resolves an Endpoint URL based on the Service Type and Facing perspective
 * 
 * @author Jeremy Unruh
 */
public interface EndpointURLResolver {

	  /**
	   * Resolves the given ServiceType and Facing perspective down to a single URL
	   * 
	   * @param params URLResolverParams containing mandatory and optional params
	   * @return the URL of the Endpoint
	   */
	  String findURL(URLResolverParams params);
	
}

package org.openstack4j.api.identity;

import org.openstack4j.model.identity.URLResolverParams;

/**
 * Resolves an Endpoint URL based on the Service Type and Facing perspective
 * 
 * @author Jeremy Unruh
 */
public interface EndpointURLResolver {

      /** Older OpenStack deployments can send invalid URL endpoints we have found. This can address the issue to apply
       *  our own logic in these cases.  Only use this in rare cases
       */
      String LEGACY_EP_RESOLVING_PROP = "legacy.endpoint.resolving.enabled";
    
	  /**
	   * Resolves the given ServiceType and Facing perspective down to a single URL
	   * 
	   * @param params URLResolverParams containing mandatory and optional params
	   * @return the URL of the Endpoint
	   */
	  String findURL(URLResolverParams params);
	
}

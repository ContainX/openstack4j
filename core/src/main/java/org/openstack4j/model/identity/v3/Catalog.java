package org.openstack4j.model.identity.v3;

import java.util.List;

import org.openstack4j.model.ModelEntity;

/**
 * Represents an OpenStack Identity Version 3 Catalog.  A catalog describes a service as well as the available 
 * Endpoint's for the service
 * 
 * @author Jeremy Unruh
 */
public interface Catalog extends ModelEntity {

	 /**
	  * @return the unique identifier for this catalog
	  */
	 String getId();
	
	 /**
	  * @return the type of catalog
	  */
	 String getType();
	
	 /**
	  * @return the available Endpoints
	  */
	 List<? extends EndpointV3> getEndpoints();
	 
	 
	
}

package org.openstack4j.model.identity.v3;

import java.net.URL;

import org.openstack4j.api.types.Facing;
import org.openstack4j.model.ModelEntity;

/**
 * Represents a Version 3 Endpoint
 *
 * @author Jeremy Unruh
 */
public interface EndpointV3 extends ModelEntity {

	/**
	 * The Endpoints unique identifier
	 * 
	 * @return the identifier for this Endpoint
	 */
	String getId();

	/**
	 * The perspective for this Endpoint
	 * 
	 * @return the Endpoint interface perspective
	 */
	Facing getInterface();
	
	/**
	 * @return the region of this Endpoint
	 */
	String getRegion();
	
	/**
	 * @return the URL for this Endpoint
	 */
	URL getURL();
}

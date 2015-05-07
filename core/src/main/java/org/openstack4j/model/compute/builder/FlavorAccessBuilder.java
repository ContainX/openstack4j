package org.openstack4j.model.compute.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.compute.FlavorAccess;

/**
 * Builder for a FlavorAccess model class
 * 
 * @author Moodpo
 */
public interface FlavorAccessBuilder extends Builder<FlavorAccessBuilder, FlavorAccess> {
	
	/**
	 * @see FlavorAccess#getFlavorId()
	 */
	FlavorAccessBuilder flavorId(String flavorId);
	
	/**
	 * @see FlavorAccess#getTenantId()
	 */
	FlavorAccessBuilder tenantId(String tenantId);
	
	/**
	 * @see FlavorAccess#getTenant()
	 */
	FlavorAccessBuilder tenant(String tenant);
}

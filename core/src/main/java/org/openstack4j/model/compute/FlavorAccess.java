package org.openstack4j.model.compute;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.compute.builder.FlavorAccessBuilder;

/**
 * An OpenStack Flavor Access for tenants
 * 
 * @author Moodpo
 */
public interface FlavorAccess extends ModelEntity, Buildable<FlavorAccessBuilder>{
	
	/**
	 * @return the id for this flavor
	 */
	String getFlavorId();
	
	/**
	 * @return the id for this tenant
	 */
	String getTenantId();
	
	/**
	 * @return the name for this tenant
	 */
	String getTenant();

}

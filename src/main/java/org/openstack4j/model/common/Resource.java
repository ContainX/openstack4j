package org.openstack4j.model.common;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;


/**
 * A Resource is a common model which contains an Id, TenantId and Name
 * 
 * @author Jeremy Unruh
 */
public interface Resource extends ModelEntity, Buildable {

	/**
	 * @return the identifier for this resource
	 */
	String getId();
	
	/**
	 * @return the name for this resource
	 */
	String getName();
	
	/**
	 * @return the tenant identifier for this resource
	 */
	String getTenantId();
	
	/**
	 * Sets the identifier for this resource.  Note: creating a new resource should not have the idenfier set since OpenStack will 
	 * assign one on the create call
	 * 
	 * @param id the identifier
	 */
	void setId(String id);
	
	/**
	 * Sets the name for this resource
	 * 
	 * @param name the name to set
	 */
	void setName(String name);
	
	/**
	 * Sets the tenant identifier
	 * 
	 * @param tenantId the tenant id to set
	 */
	void setTenantId(String tenantId);
	
}

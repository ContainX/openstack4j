package org.openstack4j.model.common;

import org.openstack4j.model.ModelEntity;


/**
 * A Resource is a common model which contains an Id, TenantId and Name
 * 
 * @author Jeremy Unruh
 */
public interface Resource extends ModelEntity {

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
	
}

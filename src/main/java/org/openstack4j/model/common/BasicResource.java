package org.openstack4j.model.common;

import org.openstack4j.model.ModelEntity;

/**
 * A basic resource that captures an Id and Name of the resource
 * 
 * @author Jeremy Unruh
 */
public interface BasicResource extends ModelEntity {

	/**
	 * @return the identifier for this resource
	 */
	String getId();
	
	/**
	 * @return the name for this resource
	 */
	String getName();
	
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
	
}

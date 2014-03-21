package org.openstack4j.model.identity;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;

/**
 * An Identity User within OpenStack
 * 
 * @author Jeremy Unruh
 */
public interface User extends ModelEntity, Buildable {

	/**
	 * @return the user identifier
	 */
	String getId();
	
	/**
	 * @return the username/sign-on name
	 */
	String getUsername();
	
	/**
	 * @return the tenant identifier (default tenant)
	 */
	String getTenantId();
	
	/**
	 * @return the name of the user
	 */
	String getName();
	
	/**
	 * @return the email address of the user
	 */
	String getEmail();
	
	/**
	 * @return true, if the user is enabled (active)
	 */
	boolean isEnabled();
	
}

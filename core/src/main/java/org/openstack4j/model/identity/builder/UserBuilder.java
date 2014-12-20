package org.openstack4j.model.identity.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.identity.Tenant;
import org.openstack4j.model.identity.User;

/**
 * A Builder which creates an Identity User
 * 
 * @author Jeremy Unruh
 */
public interface UserBuilder extends Builder<UserBuilder, User> {
	
	/**
	 * @see User#getName()
	 */
	UserBuilder name(String name);
	
	/**
	 * ID should only ever be set if the user already exists and this is used for update based actions
	 * @param id the user id
	 * @return this for method chaining
	 */
	UserBuilder id(String id);

	/**
	 * Sets the initial password for the user
	 * @param password the password to set
	 * @return this builder
	 */
	UserBuilder password(String password);
	
	/**
	 * @see User#getEmail()
	 */
	UserBuilder email(String email);
	
	/**
	 * @see User#isEnabled()
	 */
	UserBuilder enabled(boolean enabled);
	
	/**
	 * @see User#getTenantId()
	 */
	UserBuilder tenantId(String tenantId);
	
	/**
	 * Accepts an existing tenant and uses the tenant's id
	 * @see User#getTenantId()
	 */
	UserBuilder tenant(Tenant tenant);
	
	/**
	 * The domain identifier for the user.  Only applicable if using V3
	 * 
	 * @param domainId the domain identifier
	 * @return user builder
	 */
	UserBuilder domainId(String domainId);

}

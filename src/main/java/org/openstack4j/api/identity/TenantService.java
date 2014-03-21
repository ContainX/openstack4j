package org.openstack4j.api.identity;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.identity.Tenant;

/**
 * Identity Tenant based Operations
 * 
 * @author Jeremy Unruh
 */
public interface TenantService extends RestService {

	/**
	 * Lists tenants to which the specified token has access.
	 *
	 * @return List of Tenants
	 */
	List<? extends Tenant> list();
	
	/**
	 * Gets detailed information about a specified tenant by ID
	 *
	 * @param tenantId the tenant id
	 * @return the tenant
	 */
	Tenant get(String tenantId);
	
	/**
	 * Gets detailed information about a specified tenant by name
	 *
	 * @param tenantName the tenant name
	 * @return the tenant
	 */
	Tenant getByName(String tenantName);
	
	/**
	 * Creates a new Tenant
	 *
	 * @param tenant the tenant to create
	 * @return the newly created tenant and it's assigned ID
	 */
	Tenant create(Tenant tenant);
	
	/**
	 * Deletes the specified tenant by ID
	 *
	 * @param tenantId the tenant id
	 */
	void delete(String tenantId);
	
	/**
	 * Updates the tenant (ID must be set within the inbound tenant)
	 *
	 * @param tenant the tenant
	 * @return the tenant
	 */
	Tenant update(Tenant tenant);
	
}

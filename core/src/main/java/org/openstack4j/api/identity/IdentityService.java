package org.openstack4j.api.identity;

import java.util.List;

import org.openstack4j.api.identity.v3.ProjectService;
import org.openstack4j.api.identity.v3.RoleService;
import org.openstack4j.api.identity.v3.UserService;
import org.openstack4j.common.RestService;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.identity.Endpoint;

/**
 * Identity Serivce Operations API
 * 
 * @author Jeremy Unruh
 */
public interface IdentityService extends RestService {

	/**
	 * Tenants Service API
	 *
	 * @return the tenant service
	 */
	TenantService tenants();
	
	/**
	 * Users Service API
	 *
	 * @return the user service
	 */
	org.openstack4j.api.identity.UserService users();

	/**
	 * Role Service API
	 *
	 * @return the role service
	 */
	org.openstack4j.api.identity.RoleService roles();
	
	/**
	 * Service Management API
	 *
	 * @return the service manager service
	 */
	ServiceManagerService services();
	
	/**
	 * List extensions currently available on the OpenStack instance
	 *
	 * @return List of extensions
	 */
	List<? extends Extension> listExtensions();
	
	/**
	 * Lists endpoints available for the current authenticated token
	 *
	 * @return List of endpoints
	 */
	List<? extends Endpoint> listTokenEndpoints();
	
}

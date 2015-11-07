package org.openstack4j.api.identity.v3;

import org.openstack4j.common.RestService;

/**
 * Identity Service Operations API
 * 
 */
public interface IdentityService extends RestService {

	/**
	 * Projects Service API
	 *
	 * @return the project service for v3
	 */
	ProjectService projects();
	
	/**
	 * Users Service API
	 *
	 * @return the user service for v3
	 */
	UserService users();

	/**
	 * Role Service API
	 *
	 * @return the role service for v3
	 */
	RoleService roles();

}

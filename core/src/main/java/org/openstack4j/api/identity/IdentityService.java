package org.openstack4j.api.identity;

import org.openstack4j.common.RestService;

/**
 * Identity v3 Service Operations API
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
	 * @return the user service
	 */
	UserService users();

	/**
	 * Role Service API
	 *
	 * @return the role service
	 */
	RoleService roles();

}

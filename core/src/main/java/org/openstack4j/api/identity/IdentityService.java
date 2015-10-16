package org.openstack4j.api.identity;

import org.openstack4j.common.RestService;

/**
 * Identity v3 Service Operations API
 *
 */
public interface IdentityService extends RestService {

	/**
	 * Users Service API
	 *
	 * @return the user service
	 */
	UserService users();

}

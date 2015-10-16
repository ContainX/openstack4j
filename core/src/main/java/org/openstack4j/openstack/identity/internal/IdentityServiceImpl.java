package org.openstack4j.openstack.identity.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.identity.IdentityService;
import org.openstack4j.api.identity.UserService;
import org.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * Identity V3 service implementation
 *
 */
public class IdentityServiceImpl extends BaseOpenStackService implements IdentityService {

	@Override
	public UserService users() {
		return Apis.get(UserService.class);
	}

}

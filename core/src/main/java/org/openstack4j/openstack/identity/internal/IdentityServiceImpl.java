package org.openstack4j.openstack.identity.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.identity.IdentityService;
import org.openstack4j.api.identity.ProjectService;
import org.openstack4j.api.identity.RoleService;
import org.openstack4j.api.identity.UserService;
import org.openstack4j.openstack.internal.BaseOpenStackService;

public class IdentityServiceImpl extends BaseOpenStackService implements IdentityService {

    @Override
    public ProjectService projects() {
        return Apis.get(ProjectService.class);
    }

	@Override
	public UserService users() {
		return Apis.get(UserService.class);
	}

	@Override
	public RoleService roles() {
		return Apis.get(RoleService.class);
	}

}

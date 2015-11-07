package org.openstack4j.openstack.identity.internal.v3;

import org.openstack4j.api.Apis;
import org.openstack4j.api.identity.v3.IdentityService;
import org.openstack4j.api.identity.v3.ProjectService;
import org.openstack4j.api.identity.v3.RoleService;
import org.openstack4j.api.identity.v3.UserService;
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

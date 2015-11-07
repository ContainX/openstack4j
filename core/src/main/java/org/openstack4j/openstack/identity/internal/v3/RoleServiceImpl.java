package org.openstack4j.openstack.identity.internal.v3;

import org.openstack4j.api.identity.v3.RoleService;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.identity.v3.Role;
import org.openstack4j.openstack.identity.domain.v3.KeystoneRole.Roles;
import org.openstack4j.openstack.internal.BaseOpenStackService;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Identity Role based Operations Implementation
 * 
 */
public class RoleServiceImpl extends BaseOpenStackService implements RoleService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse addRoleToUserInProject(String projectId, String userId, String roleId) {
        checkNotNull(projectId);
        checkNotNull(userId);
        checkNotNull(roleId);
        String uri = uri("/projects/%s/users/%s/roles/%s", projectId, userId, roleId);
        return request(HttpMethod.PUT, ActionResponse.class, uri).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Role> list() {
		return get(Roles.class, uri("/roles")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Role> getByName(String name) {
        checkNotNull(name);
        return get(Roles.class, "/roles").param("name", name).execute().getList();

	}

}

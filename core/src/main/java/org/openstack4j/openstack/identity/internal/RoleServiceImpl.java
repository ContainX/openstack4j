package org.openstack4j.openstack.identity.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.identity.RoleService;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.model.identity.Role;
import org.openstack4j.openstack.identity.domain.KeystoneRole;
import org.openstack4j.openstack.identity.domain.KeystoneRole.Roles;
import org.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * Identity Role based Operations Implementation
 * 
 * @author Jeremy Unruh
 */
public class RoleServiceImpl extends BaseOpenStackService implements RoleService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addUserRole(String userId, String roleId) {
		addUserRole(null, userId, roleId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addUserRole(String tenantId, String userId, String roleId) {
		addRemoveRoles(HttpMethod.PUT, tenantId, userId, roleId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeUserRole(String userId, String roleId) {
		removeUserRole(null, userId, roleId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeUserRole(String tenantId, String userId, String roleId) {
		addRemoveRoles(HttpMethod.DELETE, tenantId, userId, roleId);
	}

	/**
	 * Adds the remove roles.
	 *
	 * @param method the method
	 * @param tenantId the tenant id
	 * @param userId the user id
	 * @param roleId the role id
	 */
	private void addRemoveRoles(HttpMethod method, String tenantId, String userId, String roleId) {
		checkNotNull(userId);
		checkNotNull(roleId);
		String uri = (tenantId != null) ? uri("/tenants/%s/users/%s/roles/OS-KSADM/%s", tenantId, userId, roleId) : uri("/users/%s/roles/OS-KSADM/%s", userId, roleId);
		request(method, Void.class, uri).execute();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Role> list() {
		return get(Roles.class, uri("/OS-KSADM/roles")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Role> listRolesForUser(String userId) {
		return listRolesForUser(userId, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Role> listRolesForUser(String userId, String tenantId) {
		checkNotNull(userId);
		String uri = (tenantId != null) ? uri("/tenants/%s/users/%s/roles", tenantId, userId) : uri("/users/%s/roles", userId);
		return get(Roles.class, uri).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String roleId) {
		checkNotNull(roleId);
		delete(Void.class, uri("/OS-KSADM/roles/%s", roleId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Role get(String roleId) {
		checkNotNull(roleId);
		return get(KeystoneRole.class, uri("/OS-KSADM/roles/%s", roleId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Role create(String name) {
		checkNotNull(name);
		return post(KeystoneRole.class, uri("/OS-KSADM/roles")).entity(KeystoneRole.builder().name(name).build()).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Role getByName(String name) {
		// Due to a bug in OpenStack Rest Service (not returning documented query) we will manually match on the list until it's resolved. Since the contract of the
		// API is against the interface we can change this anytime
		checkNotNull(name);
		List<? extends Role> roles = list();
		for (Role r : roles)
		{
			if (name.equalsIgnoreCase(r.getName()))
				return r;
		}
		return null;
	}

}

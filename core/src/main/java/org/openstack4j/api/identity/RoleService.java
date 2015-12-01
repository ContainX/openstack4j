package org.openstack4j.api.identity;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.identity.Role;

/**
 * Identity Role based Operations
 *
 */
public interface RoleService extends RestService {

	/**
	 * Adds a project based role to a user
	 *
	 * @param projectId the project id
	 * @param userId the user id
	 * @param roleId the role id
	 * @return the action response
	 */
	ActionResponse addRoleToUserInProject(String projectId, String userId, String roleId);

	/**
	 * Lists the global roles
	 *
	 * @return the list<? extends role>
	 */
	List<? extends Role> list();

	/**
	 * Get Role(s) filtering by Name
	 * @param name the name of the Role to filter by
	 * @return the list<? extends Role>
	 */
    List<? extends Role> getByName(String name);

}

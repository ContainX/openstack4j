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
     * grants a role to a specified user in project context
     *
     * @param projectId the project id
     * @param userId the user id
     * @param roleId the role id
     * @return the action response
     */
    ActionResponse grantProjectUserRole(String projectId, String userId, String roleId);

    /**
     * revokes a role to a specified user in project context
     *
     * @param projectId the project id
     * @param userId the user id
     * @param roleId the role id
     * @return the action response
     */
    ActionResponse revokeProjectUserRole(String projectId, String userId, String roleId);

    /**
     * checks if a user has a specific role in a given project-context
     *
     * @param projectId the project id
     * @param userId the user id
     * @param roleId the role id
     * @return the ActionResponse
     */
    ActionResponse checkProjectUserRole(String projectId, String userId, String roleId);

    /**
     * grants a role to a specified user in domain context
     *
     * @param domainId the domain id
     * @param userId the user id
     * @param roleId the role id
     * @return the action response
     */
    ActionResponse grantDomainUserRole(String domainId, String userId, String roleId);

    /**
     * revokes a role to a specified user in domain context
     *
     * @param domainId the domain id
     * @param userId the user id
     * @param roleId the role id
     * @return the action response
     */
    ActionResponse revokeDomainUserRole(String domainId, String userId, String roleId);

    /**
     * checks if a user has a specific role in a given domain-context
     *
     * @param domainId the domain id
     * @param userId the user id
     * @param roleId the role id
     * @return the ActionResponse
     */
    ActionResponse checkDomainUserRole(String domainId, String userId, String roleId);

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

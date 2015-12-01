package org.openstack4j.openstack.identity.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.openstack4j.core.transport.ClientConstants.PATH_DOMAINS;
import static org.openstack4j.core.transport.ClientConstants.PATH_USERS;

import java.util.List;

import org.openstack4j.api.identity.UserService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.identity.Domain;
import org.openstack4j.model.identity.Group;
import org.openstack4j.model.identity.Project;
import org.openstack4j.model.identity.Role;
import org.openstack4j.model.identity.User;
import org.openstack4j.openstack.identity.domain.KeystoneDomain;
import org.openstack4j.openstack.identity.domain.KeystoneUser;
import org.openstack4j.openstack.identity.domain.KeystoneGroup.Groups;
import org.openstack4j.openstack.identity.domain.KeystoneProject.Projects;
import org.openstack4j.openstack.identity.domain.KeystoneRole.Roles;
import org.openstack4j.openstack.identity.domain.KeystoneUser.Users;
import org.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * implementation of v3 user service
 *
 */
public class UserServiceImpl extends BaseOpenStackService implements UserService {

	@Override
	public User get(String userId) {
		checkNotNull(userId);
		return get(KeystoneUser.class, PATH_USERS, "/", userId).execute();
	}

	@Override
	public List<? extends User> getByName(String userName) {
		checkNotNull(userName);
		return get(Users.class, uri(PATH_USERS)).param("name", userName).execute().getList();
	}

	@Override
	public User getByName(String userName, String domainId) {
	    checkNotNull(userName);
	    checkNotNull(domainId);
	    return get(Users.class, uri(PATH_USERS)).param("name", userName).param("domain_id", domainId).execute().getList().get(0);
	}

	@Override
	public Domain getUserDomain(String userId) {
	    return get(KeystoneDomain.class, PATH_DOMAINS, "/", get(userId).getDomainId()).execute();
	}

	@Override
	public ActionResponse delete(String userId) {
		checkNotNull(userId);
		return deleteWithResponse(PATH_USERS, "/", userId).execute();
	}

	@Override
	public User update(User user) {
	    checkNotNull(user);
		return patch(KeystoneUser.class, PATH_USERS, "/", user.getId()).entity(user).execute();
	}

	@Override
	public User create(User user) {
		checkNotNull(user);
		return post(KeystoneUser.class, uri(PATH_USERS)).entity(user).execute();
	}

	@Override
	public User create(String domainId, String name, String password, String email, boolean enabled) {
	    checkNotNull(domainId);
	    checkNotNull(name);
	    checkNotNull(password);
	    checkNotNull(email);
	    checkNotNull(enabled);
	    return create(KeystoneUser.builder().domainId(domainId).name(name).password(password).email(email).enabled(enabled).build());
	}

	@Override
	public List<? extends Group> listUserGroups(String userId) {
		checkNotNull(userId);
		return get(Groups.class, uri("/users/%s/groups", userId)).execute().getList();
	}

	@Override
	public List<? extends Project> listUserProjects(String userId) {
		checkNotNull(userId);
        return get(Projects.class, uri("/users/%s/projects", userId)).execute().getList();
	}

	@Override
	public List<? extends User> list() {
		return get(Users.class, uri(PATH_USERS)).execute().getList();
	}

	@Override
	public List<? extends Role> listProjectUserRoles(String userId, String projectId) {
		checkNotNull(userId);
		checkNotNull(projectId);
		return get(Roles.class, uri("projects/%s/users/%s/roles", projectId, userId)).execute().getList();
	}

    @Override
    public List<? extends Role> listDomainUserRoles(String userId, String domainId) {
        checkNotNull(userId);
        checkNotNull(domainId);
        return get(Roles.class, uri("domains/%s/users/%s/roles", domainId, userId)).execute().getList();
    }

    @Override
    public ActionResponse grantDomainUserRole(String domainId, String userId, String roleId) {
        checkNotNull(userId);
        checkNotNull(domainId);
        checkNotNull(roleId);
        return put(ActionResponse.class, uri("domains/%s/users/%s/roles/%s", domainId, userId, roleId)).execute();
    }

    @Override
    public ActionResponse revokeDomainUserRole(String domainId, String userId, String roleId) {
        checkNotNull(userId);
        checkNotNull(domainId);
        checkNotNull(roleId);
        return deleteWithResponse(uri("domains/%s/users/%s/roles/%s", domainId, userId, roleId)).execute();
    }

    @Override
    public ActionResponse grantProjectUserRole(String projectId, String userId, String roleId) {
        checkNotNull(userId);
        checkNotNull(projectId);
        checkNotNull(roleId);
        return put(ActionResponse.class, uri("projects/%s/users/%s/roles/%s", projectId, userId, roleId)).execute();
    }

    @Override
    public ActionResponse revokeProjectUserRole(String projectId, String userId, String roleId) {
        checkNotNull(userId);
        checkNotNull(projectId);
        checkNotNull(roleId);
        return deleteWithResponse(uri("projects/%s/users/%s/roles/%s", projectId, userId, roleId)).execute();
    }

    @Override
    public ActionResponse checkDomainUserRole(String domainId, String userId, String roleId) {
        checkNotNull(domainId);
        checkNotNull(userId);
        checkNotNull(roleId);
        return head(ActionResponse.class, uri("/domains/%s/users/%s/roles/%s", domainId, userId, roleId)).execute();
    }

    @Override
    public ActionResponse checkProjectUserRole(String projectId, String userId, String roleId) {
        checkNotNull(projectId);
        checkNotNull(userId);
        checkNotNull(roleId);
        return head(ActionResponse.class, uri("/projects/%s/users/%s/roles/%s", projectId, userId, roleId)).execute();
    }

    @Override
    public ActionResponse addUserToGroup(String groupId, String userId) {
        checkNotNull(groupId);
        checkNotNull(userId);
        return put(ActionResponse.class, uri("groups/%s/users/%s", groupId, userId)).execute();
    }

    @Override
    public ActionResponse removeUserFromGroup(String groupId, String userId) {
        checkNotNull(groupId);
        checkNotNull(userId);
        return deleteWithResponse(uri("groups/%s/users/%s", groupId, userId)).execute();
    }



}

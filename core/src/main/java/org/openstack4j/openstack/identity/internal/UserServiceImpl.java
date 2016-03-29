package org.openstack4j.openstack.identity.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.openstack4j.core.transport.ClientConstants.PATH_DOMAINS;
import static org.openstack4j.core.transport.ClientConstants.PATH_USERS;

import java.util.List;

import org.openstack4j.api.identity.UserService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.identity.Domain;
import org.openstack4j.model.identity.Group;
import org.openstack4j.model.identity.Project;
import org.openstack4j.model.identity.Role;
import org.openstack4j.model.identity.User;
import org.openstack4j.openstack.identity.domain.KeystoneDomain;
import org.openstack4j.openstack.identity.domain.KeystoneGroup.Groups;
import org.openstack4j.openstack.identity.domain.KeystoneProject.Projects;
import org.openstack4j.openstack.identity.domain.KeystoneRole.Roles;
import org.openstack4j.openstack.identity.domain.KeystoneUser;
import org.openstack4j.openstack.identity.domain.KeystoneUser.Users;
import org.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * implementation of v3 user service
 *
 */
public class UserServiceImpl extends BaseOpenStackService implements UserService {

    /**
     * {@inheritDoc}
     */
    @Override
    public User get(String userId) {
        checkNotNull(userId);
        return get(KeystoneUser.class, PATH_USERS, "/", userId).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends User> getByName(String userName) {
        checkNotNull(userName);
        return get(Users.class, uri(PATH_USERS)).param("name", userName).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getByName(String userName, String domainId) {
        checkNotNull(userName);
        checkNotNull(domainId);
        return get(Users.class, uri(PATH_USERS)).param("name", userName).param("domain_id", domainId).execute().getList().get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Domain getUserDomain(String userId) {
        return get(KeystoneDomain.class, PATH_DOMAINS, "/", get(userId).getDomainId()).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String userId) {
        checkNotNull(userId);
        return deleteWithResponse(PATH_USERS, "/", userId).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User update(User user) {
        checkNotNull(user);
        return patch(KeystoneUser.class, PATH_USERS, "/", user.getId()).entity(user).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User create(User user) {
        checkNotNull(user);
        return post(KeystoneUser.class, uri(PATH_USERS)).entity(user).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User create(String domainId, String name, String password, String email, boolean enabled) {
        checkNotNull(domainId);
        checkNotNull(name);
        checkNotNull(password);
        checkNotNull(email);
        checkNotNull(enabled);
        return create(KeystoneUser.builder().domainId(domainId).name(name).password(password).email(email).enabled(enabled).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Group> listUserGroups(String userId) {
        checkNotNull(userId);
        return get(Groups.class, uri("/users/%s/groups", userId)).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Project> listUserProjects(String userId) {
        checkNotNull(userId);
        return get(Projects.class, uri("/users/%s/projects", userId)).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends User> list() {
        return get(Users.class, uri(PATH_USERS)).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Role> listProjectUserRoles(String userId, String projectId) {
        checkNotNull(userId);
        checkNotNull(projectId);
        return get(Roles.class, uri("projects/%s/users/%s/roles", projectId, userId)).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Role> listDomainUserRoles(String userId, String domainId) {
        checkNotNull(userId);
        checkNotNull(domainId);
        return get(Roles.class, uri("domains/%s/users/%s/roles", domainId, userId)).execute().getList();
    }

}

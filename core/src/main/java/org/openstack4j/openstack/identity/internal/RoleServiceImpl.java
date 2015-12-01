package org.openstack4j.openstack.identity.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.identity.RoleService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.identity.Role;
import org.openstack4j.openstack.identity.domain.KeystoneRole.Roles;
import org.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * Identity Role based Operations Implementation
 *
 */
public class RoleServiceImpl extends BaseOpenStackService implements RoleService {

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse grantProjectUserRole(String projectId, String userId, String roleId) {
        checkNotNull(userId);
        checkNotNull(projectId);
        checkNotNull(roleId);
        return put(ActionResponse.class, uri("projects/%s/users/%s/roles/%s", projectId, userId, roleId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse revokeProjectUserRole(String projectId, String userId, String roleId) {
        checkNotNull(userId);
        checkNotNull(projectId);
        checkNotNull(roleId);
        return deleteWithResponse(uri("projects/%s/users/%s/roles/%s", projectId, userId, roleId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse checkProjectUserRole(String projectId, String userId, String roleId) {
        checkNotNull(projectId);
        checkNotNull(userId);
        checkNotNull(roleId);
        return head(ActionResponse.class, uri("/projects/%s/users/%s/roles/%s", projectId, userId, roleId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse grantDomainUserRole(String domainId, String userId, String roleId) {
        checkNotNull(userId);
        checkNotNull(domainId);
        checkNotNull(roleId);
        return put(ActionResponse.class, uri("domains/%s/users/%s/roles/%s", domainId, userId, roleId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse revokeDomainUserRole(String domainId, String userId, String roleId) {
        checkNotNull(userId);
        checkNotNull(domainId);
        checkNotNull(roleId);
        return deleteWithResponse(uri("domains/%s/users/%s/roles/%s", domainId, userId, roleId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse checkDomainUserRole(String domainId, String userId, String roleId) {
        checkNotNull(domainId);
        checkNotNull(userId);
        checkNotNull(roleId);
        return head(ActionResponse.class, uri("/domains/%s/users/%s/roles/%s", domainId, userId, roleId)).execute();
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

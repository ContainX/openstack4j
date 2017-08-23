/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.openstack.identity.v3.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import java.util.List;

import com.huawei.openstack4j.api.identity.v3.RoleService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v3.Role;
import com.huawei.openstack4j.model.identity.v3.RoleAssignment;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneRole;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneRole.Roles;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneRoleAssignment.RoleAssignments;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

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

    @Override
    public Role create(Role role) {
        checkNotNull(role);
        return post(KeystoneRole.class, uri(PATH_ROLES)).entity(role).execute();
    }

    @Override
    public Role create(String name) {
        checkNotNull(name);
        return create(KeystoneRole.builder().name(name).build());
    }

    @Override
    public Role update(Role role) {
        checkNotNull(role);
        return patch(KeystoneRole.class, PATH_ROLES, "/", role.getId()).entity(role).execute();
    }

    @Override
    public ActionResponse delete(String roleId) {
        checkNotNull(roleId);
        return deleteWithResponse(PATH_ROLES, "/", roleId).execute();
    }

    @Override
    public Role get(String roleId) {
        checkNotNull(roleId);
        return get(KeystoneRole.class, PATH_ROLES, "/", roleId).execute();
    }

    @Override
    public List<? extends RoleAssignment> listRoleAssignments(String projectId) {
        checkNotNull(projectId);
        return get(RoleAssignments.class, "role_assignments")
                .param("scope.project.id", projectId)
                .param("effective", "true")
                .execute().getList();
    }

    @Override
    public ActionResponse grantProjectGroupRole(String projectId, String groupId, String roleId) {
        checkNotNull(projectId);
        checkNotNull(groupId);
        checkNotNull(roleId);
        return put(ActionResponse.class, uri("projects/%s/groups/%s/roles/%s", projectId, groupId, roleId)).execute();
    }

    @Override
    public ActionResponse revokeProjectGroupRole(String projectId, String groupId, String roleId) {
        checkNotNull(projectId);
        checkNotNull(groupId);
        checkNotNull(roleId);
        return deleteWithResponse(uri("projects/%s/groups/%s/roles/%s", projectId, groupId, roleId)).execute();
    }

    @Override
    public ActionResponse checkProjectGroupRole(String projectId, String groupId, String roleId) {
        checkNotNull(projectId);
        checkNotNull(groupId);
        checkNotNull(roleId);
        return head(ActionResponse.class, uri("/projects/%s/groups/%s/roles/%s", projectId, groupId, roleId)).execute();
    }

    @Override
    public ActionResponse grantDomainGroupRole(String domainId, String groupId, String roleId) {
        checkNotNull(domainId);
        checkNotNull(groupId);
        checkNotNull(roleId);
        return put(ActionResponse.class, uri("domains/%s/groups/%s/roles/%s", domainId, groupId, roleId)).execute();
    }

    @Override
    public ActionResponse revokeDomainGroupRole(String domainId, String groupId, String roleId) {
        checkNotNull(domainId);
        checkNotNull(groupId);
        checkNotNull(roleId);
        return deleteWithResponse(uri("domains/%s/groups/%s/roles/%s", domainId, groupId, roleId)).execute();
    }

    @Override
    public ActionResponse checkDomainGroupRole(String domainId, String groupId, String roleId) {
        checkNotNull(domainId);
        checkNotNull(groupId);
        checkNotNull(roleId);
        return head(ActionResponse.class, uri("/domains/%s/groups/%s/roles/%s", domainId, groupId, roleId)).execute();
    }

}

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

import com.huawei.openstack4j.api.identity.v3.GroupService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v3.Group;
import com.huawei.openstack4j.model.identity.v3.Role;
import com.huawei.openstack4j.model.identity.v3.User;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneGroup;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneGroup.Groups;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneRole.Roles;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneUser.Users;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

public class GroupServiceImpl extends BaseOpenStackService implements GroupService {

    @Override
    public Group get(String groupId) {
        checkNotNull(groupId);
        return get(KeystoneGroup.class, PATH_GROUPS, "/", groupId).execute();
    }

    @Override
    public List<? extends Group> getByName(String groupName) {
        return get(Groups.class, uri(PATH_GROUPS)).param("name", groupName).execute().getList();
    }

    @Override
    public Group getByName(String groupName, String domainId) {
        checkNotNull(groupName);
        checkNotNull(domainId);
        return get(Groups.class, uri(PATH_GROUPS)).param("name", groupName).param("domain_id", domainId).execute().first();
    }

    @Override
    public ActionResponse delete(String groupId) {
        checkNotNull(groupId);
        return deleteWithResponse(PATH_GROUPS, "/", groupId).execute();
    }

    @Override
    public Group update(Group group) {
        checkNotNull(group);
        return patch(KeystoneGroup.class, PATH_GROUPS, "/", group.getId()).entity(group).execute();
    }

    @Override
    public Group create(Group group) {
        checkNotNull(group);
        return post(KeystoneGroup.class, uri(PATH_GROUPS)).entity(group).execute();
    }

    @Override
    public Group create(String domainId, String name, String description) {
        checkNotNull(domainId);
        checkNotNull(name);
        checkNotNull(description);
        return create(KeystoneGroup.builder().domainId(domainId).name(name).description(description).build());
    }

    @Override
    public List<? extends Group> list() {
        return get(Groups.class, uri(PATH_GROUPS)).execute().getList();
    }

    @Override
    public List<? extends User> listGroupUsers(String groupId) {
        checkNotNull(groupId);
        return get(Users.class, uri("/groups/%s/users", groupId)).execute().getList();
    }

    @Override
    public ActionResponse checkGroupUser(String groupId, String userId) {
        checkNotNull(groupId);
        checkNotNull(userId);
        return head(ActionResponse.class, uri("/groups/%s/users/%s", groupId, userId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse addUserToGroup(String groupId, String userId) {
        checkNotNull(groupId);
        checkNotNull(userId);
        return put(ActionResponse.class, uri("groups/%s/users/%s", groupId, userId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse removeUserFromGroup(String groupId, String userId) {
        checkNotNull(groupId);
        checkNotNull(userId);
        return deleteWithResponse(uri("groups/%s/users/%s", groupId, userId)).execute();
    }

    @Override
    public List<? extends Role> listProjectGroupRoles(String groupId, String projectId) {
        checkNotNull(groupId);
        checkNotNull(projectId);
        return get(Roles.class, uri("projects/%s/groups/%s/roles", projectId, groupId)).execute().getList();
    }

    @Override
    public List<? extends Role> listDomainGroupRoles(String groupId, String domainId) {
        checkNotNull(groupId);
        checkNotNull(domainId);
        return get(Roles.class, uri("domains/%s/groups/%s/roles", domainId, groupId)).execute().getList();
    }

}

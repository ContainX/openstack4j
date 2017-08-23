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
package com.huawei.openstack4j.api.identity.v3;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v3.Group;
import com.huawei.openstack4j.model.identity.v3.Role;
import com.huawei.openstack4j.model.identity.v3.User;


/**
 * Identity V3 Group Service
 *
 */
public interface GroupService extends RestService {

    /**
     * gets detailed information about a specified group by id
     *
     * @param groupId the group id
     * @return the group
     */
    Group get(String groupId);

    /**
     * get detailed information about groups matching specified by name and domain
     *
     * @param groupName the group name
     * @return the of list groups matching the name across all domains
     */
    List<? extends Group> getByName(String groupName);

    /**
     * get detailed information about groups matching specified by name and domain
     *
     * @param groupName the group name
     * @param domainId the domain id
     * @return the of list groups matching the name in a specific domain or null if not found
     */
    Group getByName(String groupName, String domainId);

    /**
     * delete a group by id
     *
     * @param groupId the group id
     * @return the action response
     */
    ActionResponse delete(String groupId);

    /**
     * updates an existing group
     *
     * @param group the group set to update
     * @return the updated group
     */
    Group update(Group group);

    /**
     * create a new group
     *
     * @param group the group
     * @return the newly created group
     */
    Group create(Group group);

    /**
     * creates a new group
     *
     * @param domainId the domain id
     * @param name the group name
     * @param description the description
     *
     * @return the newly created group
     */
    Group create(String domainId, String name, String description);

    /**
     * lists groups.
     *
     * @return list of groups
     */
    List<? extends Group> list();

    /**
     * lists the users that belong to a group
     *
     * @return
     */
    List<? extends User> listGroupUsers(String groupId);

    /**
     * list role for group on a project
     *
     * @param groupId the group id
     * @param projectId the project id
     * @return the list of project roles for the group
     */
    List<? extends Role> listProjectGroupRoles(String groupId, String projectId);

    /**
     * list roles for a group on a domain
     *
     * @param groupId the group id
     * @param domainId the domain id
     * @return the list of domain roles for a group
     */
    List<? extends Role> listDomainGroupRoles(String groupId, String domainId);

    /**
     * adds an existing user to a group
     *
     * @param groupId the group id
     * @param userId the user id
     * @return the ActionResponse
     */
    ActionResponse addUserToGroup(String groupId, String userId);

    /**
     * removes a user from a group
     *
     * @param groupId the group id
     * @param userId the user id
     * @return the ActionResponse
     */
    ActionResponse removeUserFromGroup(String groupId, String userId);

    /**
     * check whether a user belongs to a group
     *
     * @param groupId the group id
     * @param userId the user id
     * @return the ActionResponse
     */
    ActionResponse checkGroupUser(String groupId, String userId);


}

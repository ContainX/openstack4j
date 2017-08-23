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
import com.huawei.openstack4j.model.identity.v3.Domain;
import com.huawei.openstack4j.model.identity.v3.Group;
import com.huawei.openstack4j.model.identity.v3.Project;
import com.huawei.openstack4j.model.identity.v3.Role;
import com.huawei.openstack4j.model.identity.v3.User;


/**
 * identity/v3 User operations
 *
 */
public interface UserService extends RestService {

	/**
	 * gets detailed information about a specified user by id
	 *
	 * @param userId the user id
	 * @return the user
	 */
	User get(String userId);

	/**
	 * get detailed information about users matching specified name across all domains
	 *
	 * @param userName the user name
	 * @return the of list users matching the name across all domains
	 */
	List<? extends User> getByName(String userName);

	/**
     * get detailed information about a user specified by username and domain id
     *
     * @param userName the user name
     * @param domainId the domain identifier
     * @return the user or null if not found
     */
	User getByName(String userName, String domainId);

    /**
     * delete a user by id
     *
     * @param userid the userId
     * @return the action response
     */
	ActionResponse delete(String userId);

	/**
	 * updates the password for or enables or disables a specified user.
	 *
	 * @param user the user set to update
	 * @return the updated user
	 */
	User update(User user);

	/**
	 * create a new user
	 *
	 * @param user the user
	 * @return the newly created user
	 */
	User create(User user);

	/**
	 * creates a new user
	 *
	 * @param domainId the domain id
	 * @param name the name of the new user
	 * @param password the password of the new user
	 * @param email the email of the new user
	 * @param enabled the enabled of the new user
	 * @return the newly created user
	 */
	User create(String domainId, String name, String password, String email, boolean enabled);

	/**
	 * @param userId the user id
	 * @return the domain of the user
	 */
	Domain getUserDomain(String userId);

	/**
	 * lists groups for a specified user
	 *
	 * @param userId the user id
	 * @return list of groups for a user
	 */
	List<? extends Group> listUserGroups(String userId);

	/**
	 * lists projects for a specified user
	 *
	 * @param user the user
	 * @return list of projects for a user
	 */
	List<? extends Project> listUserProjects(String userId);

	/**
	 * list role assignments for specified user in project context
	 *
	 * @param userId the user id
	 * @param scope the scope (project,domain)
	 * @return list of role assignments for specified user
	 */
	List<? extends Role> listProjectUserRoles(String userId, String projectId);

	/**
	 * list role assignment for specified user in domain context
	 *
	 * @param userId the user identifier
	 * @param domainId the domain identifier
	 * @return list of role assignments for specified user and domain
	 */
	List<? extends Role> listDomainUserRoles(String userId, String domainId);

	/**
	 * lists users.
	 *
	 * @return list of users
	 */
	List<? extends User> list();
	
	/**
     * change password for user.
     *
     * @param userId the user identifier
     * @param originalPassword the original password
     * @param password the new password
     * @return the action response
     */
    ActionResponse changePassword(String userId, String originalPassword, String password);


}

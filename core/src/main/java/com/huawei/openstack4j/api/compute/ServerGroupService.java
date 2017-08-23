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
package com.huawei.openstack4j.api.compute;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.ServerGroup;

public interface ServerGroupService extends RestService {
	
	/**
	 * Lists serverGroups that are associated with the account making the request
	 *
	 * @return the list of serverGroups
	 */
	List<? extends ServerGroup> list();
	
	/**
	 * Gets the serverGroup by id
	 *
	 * @param name the serverGroup id
	 * @return the serverGroup
	 */
	ServerGroup get(String id);
	
	/**
	 * Deletes the serverGroup by id
	 *
	 * @param name the serverGroup id
	 * @return the action response
	 */
	ActionResponse delete(String id);
	
	/**
	 * Generates or imports a serverGroup
	 *
	 * @param name the name of the serverGroup
	 * @param publicKey the public key (optional), Null indicates one will be generated
	 * @return the newly created serverGroup
	 */
	ServerGroup create(String name, String policy);

}

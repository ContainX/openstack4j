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
package com.huawei.openstack4j.api.senlin;

import java.util.List;

import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.senlin.Policy;
import com.huawei.openstack4j.model.senlin.PolicyCreate;

/**
 * This interface defines all methods for the manipulation of Policy
 * 
 * @author lion
 * 
 */
public interface SenlinPolicyService {
	
	/**
	 * Gets a list of currently existing {@link Policy}s.
	 * 
	 * @return the list of {@link Policy}s
	 */
	List<? extends Policy> list();

	/**
	 * <code>POST /v1/policies</code><br \>
	 *
	 * Creates a new {@link Policy} out of a {@link PolicyCreate} object
	 *
	 * @param newPolicy
	 *            {@link PolicyCreate} object out of which policy is to be created
	 * @return new {@link Policy} as returned from the server
	 */
	Policy create(PolicyCreate newPolicy);

	/**
	 * returns details of a {@link Policy}.
	 *
	 * @param policyID
	 *            Id of {@link Policy}
	 * @return Policy
	 */
	Policy get(String policyID);

	/**
	 * <code>PATCH /v1/policies/​{policy_id}​</code><br \>
	 *
	 * Update a {@link Policy} out of a {@link PolicyCreate} object
	 *
	 * @param policyID
	 *             Id of {@link Policy}
	 * @param newPolicy
	 *            {@link PolicyCreate} object out of which stack is to be update
	 * @return new {@link Policy} as returned from the server
	 */
	Policy update(String policyID, PolicyCreate newPolicy);

	/**
	 * Deletes the specified {@link Policy} from the server.
	 *
	 * @param policyID
	 *            Id of {@link Policy}
	 * @return the action response
	 */
	ActionResponse delete(String policyID);
}

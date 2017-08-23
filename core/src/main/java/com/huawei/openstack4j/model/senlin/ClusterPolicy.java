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
package com.huawei.openstack4j.model.senlin;

import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.ResourceEntity;

/**
 * This interface describes the getter-methods (and thus components) of a ClusterPolicy.
 * All getters map to the possible return values of
 * <code> GET /v1/clusters/​{cluster_id}​/policies/​{policy_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-clustering-v1.html
 * 
 * @author lion
 * 
 */
public interface ClusterPolicy extends ModelEntity {

	/**
	 * Returns the id of the ClusterPolicy
	 * 
	 * @return the id of the ClusterPolicy
	 */
	String getId();

	/**
	 * Returns the policy type of the ClusterPolicy
	 *
	 * @return the policy type of the ClusterPolicy
	 */
	String getPolicyType();

	/**
	 * Returns the cluster id of the ClusterPolicy
	 *
	 * @return the cluster id of the ClusterPolicy
	 */
	String getClusterID();

	/**
	 * Returns the cluster name of the ClusterPolicy
	 *
	 * @return the cluster name of the ClusterPolicy
	 */
	String getClusterName();

	/**
	 * Returns the enabled or not of the ClusterPolicy
	 *
	 * @return the enabled or not of the ClusterPolicy
	 */
	Boolean getEnabled();

	/**
	 * Returns the policy id of the ClusterPolicy
	 *
	 * @return the policy id of the ClusterPolicy
	 */
	String getPolicyID();

	/**
	 * Returns the policy name of the ClusterPolicy
	 *
	 * @return the policy name of the ClusterPolicy
	 */
	String getPolicyName();
}

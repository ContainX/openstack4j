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
import com.huawei.openstack4j.model.senlin.ActionID;
import com.huawei.openstack4j.model.senlin.Cluster;
import com.huawei.openstack4j.model.senlin.ClusterActionCreate;
import com.huawei.openstack4j.model.senlin.ClusterCreate;

/**
 * This interface defines all methods for the manipulation of Cluster
 * 
 * @author lion
 * 
 */
public interface SenlinClusterService {
	
	/**
	 * Gets a list of currently existing {@link Cluster}s.
	 * 
	 * @return the list of {@link Cluster}s
	 */
	List<? extends Cluster> list();

	/**
	 * returns details of a {@link Cluster}.
	 *
	 * @param clusterID
	 *            Id of {@link Cluster}
	 * @return Cluster
	 */
	Cluster get(String clusterID);

	/**
	 * <code>POST /v1/clusters</code><br \>
	 *
	 * Creates a new {@link Cluster} out of a {@link ClusterCreate} object
	 *
	 * @param newCluster
	 *            {@link ClusterCreate} object out of which cluster is to be created
	 * @return new {@link Cluster} as returned from the server
	 */
	Cluster create(ClusterCreate newCluster);

	/**
	 * Deletes the specified {@link Cluster} from the server.
	 *
	 * @param clusterID
	 *            Id of {@link Cluster}
	 * @return the action response
	 */
	ActionResponse delete(String clusterID);

	/**
	 * <code>PATCH /v1/clusters/​{cluster_id}​</code><br \>
	 *
	 * Update a {@link Cluster} out of a {@link ClusterCreate} object
	 *
	 * @param clusterID
	 *             Id of {@link Cluster}
	 * @param newCluster
	 *            {@link ClusterCreate} object out of which stack is to be update
	 * @return new {@link Cluster} as returned from the server
	 */
	Cluster update(String clusterID, ClusterCreate newCluster);

	/**
	 * Service implementation which provides methods for manipulation of action
	 *
	 * @return Action
	 */
	ActionID action(String clusterID, ClusterActionCreate newClusterAction);
}

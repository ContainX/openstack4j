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
package com.huawei.openstack4j.model.senlin.builder;

import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.senlin.NodeCreate;

/**
 * This interface describes a builder for {@link NodeCreate} objects
 * 
 * @author lion
 */
public interface NodeCreateBuilder extends Buildable.Builder<NodeCreateBuilder, NodeCreate> {

	/**
	 *  Add the ID or shortID or name of the cluster the node lives in.
	 *  If not specified, the node created will be an orphaned node.
	 *
	 * @param clusterID The ID or shortID or name of the cluster
	 * @return NodeCreateBuilder
	 */
	NodeCreateBuilder clusterID(String clusterID);

	/**
	 *  Add a set of key and value pairs to associate with the node.
	 *
	 * @param metadata A set of key and value pairs
	 * @return NodeCreateBuilder
	 */
	NodeCreateBuilder metadata(Map<String, String> metadata);

	/**
	 *  Add the name of the node to be created.
	 *
	 * @param name The name of the node
	 * @return NodeCreateBuilder
	 */
	NodeCreateBuilder name(String name);

	/**
	 *  Add the ID or shortID or name of the profile for the node.
	 *
	 * @param profileID The ID or shortID or name of the profile
	 * @return NodeCreateBuilder
	 */
	NodeCreateBuilder profileID(String profileID);

	/**
	 *  Add a string indicating the role this node plays in a cluster.
	 *
	 * @param role a string indicating the role this node plays
	 * @return NodeCreateBuilder
	 */
	NodeCreateBuilder role(String role);
}

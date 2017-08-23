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
import com.huawei.openstack4j.model.senlin.ClusterCreate;

/**
 * This interface describes a builder for {@link ClusterCreate} objects
 * 
 * @author lion
 */
public interface ClusterCreateBuilder extends Buildable.Builder<ClusterCreateBuilder, ClusterCreate> {

	/**
	 *  Add the name of the cluster.
	 *
	 * @param name The name of the cluster.
	 * @return ClusterCreateBuilder
	 */
	ClusterCreateBuilder name(String name);

	/**
	 *  Add a set of key and value pairs to associate with the cluster.
	 *
	 * @param metadata A set of key and value pairs to associate with the cluster.
	 * @return ClusterCreateBuilder
	 */
	ClusterCreateBuilder metadata(Map<String, String> metadata);

	/**
	 *  Add the capacity, or initial size, of the cluster
	 *
	 * @param desiredCapacity The capacity, or initial size, of the cluster
	 * @return ClusterCreateBuilder
	 */
	ClusterCreateBuilder desiredCapacity(int desiredCapacity);

	/**
	 *  Add the maximum size of the cluster.
	 *
	 * @param maxSize The maximum size of the cluster.
	 * @return ClusterCreateBuilder
	 */
	ClusterCreateBuilder maxSize(int maxSize);

	/**
	 *  Add the minimum size of the cluster.
	 *
	 * @param minSize The minimum size of the cluster.
	 * @return ClusterCreateBuilder
	 */
	ClusterCreateBuilder minSize(int minSize);

	/**
	 *  Add the ID or name of the profile for the cluster.
	 *
	 * @param profileID The ID or name of the profile for the cluster.
	 * @return ClusterCreateBuilder
	 */
	ClusterCreateBuilder profileID(String profileID);

	/**
	 *  Add the timeout value, in minutes, for cluster creation.
	 *
	 * @param timeout The timeout value, in minutes, for cluster creation.
	 * @return ClusterCreateBuilder
	 */
	ClusterCreateBuilder timeout(int timeout);

}

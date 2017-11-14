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
package com.huawei.openstack4j.model.map.reduce.builder;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.map.reduce.Cluster;
import com.huawei.openstack4j.model.map.reduce.NodeGroup;
import com.huawei.openstack4j.model.map.reduce.ServiceConfig;

/**
 * Builder interface used for {@link Cluster} object.
 * For mapping from object builder to JSON request
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 */
public interface ClusterBuilder extends Builder<ClusterBuilder, Cluster> {

        //
        // Define setter apis for user to create a new cluster
        //
        
	/**
	 * See {@link Cluster#getName()}
	 * 
	 * @param name the name of the cluster
	 * @return ClusterBuilder
	 */
	ClusterBuilder name(String name);
	
	/**
	 * See {@link Cluster#getHadoopVersion()}
	 * 
	 * @param hadoopVersion the version of hadoop
	 * @return ClusterBuilder
	 */
	ClusterBuilder hadoopVersion(String hadoopVersion);

	/**
	 * See {@link Cluster#getPluginName()}
	 * 
	 * @param pluginName the name of the map reduce plugin
	 * @return ClusterBuilder
	 */
	ClusterBuilder pluginName(String pluginName);

	/**
	 * See {@link Cluster#getClusterTemplateId()}
	 * 
	 * @param clusterTemplateId the id of cluster template
	 * @return ClusterBuilder
	 */
	ClusterBuilder template(String clusterTemplateId);

	/**
	 * See {@link Cluster#getDefaultImageId()}
	 * 
	 * @param imageId the id of image
	 * @return ClusterBuilder
	 */
	ClusterBuilder image(String imageId);

	/**
	 * See {@link Cluster#getKeypairName()}
	 * 
	 * @param keypairName the name of key pair
	 * @return ClusterBuilder
	 */
	ClusterBuilder keypairName(String keypairName);

	/**
	 * See {@link Cluster#getManagementNetworkId()}
	 * 
	 * @param networkId the id of management network
	 * @return ClusterBuilder
	 */
	ClusterBuilder managementNetworkId(String networkId);

         /**
          * Add a cluster config
          *
          * @param name the service name
          * @param config the config
          * @return this builder
          */
        ClusterBuilder addServiceConfig(String name, ServiceConfig config);

         /**
          * Add a node group
          *
          * @param nodeGroup the node group
          * @return this builder
          */
        ClusterBuilder addNodeGroup(NodeGroup nodeGroup);

}

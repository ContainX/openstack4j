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
import com.huawei.openstack4j.model.map.reduce.ClusterTemplate;
import com.huawei.openstack4j.model.map.reduce.NodeGroup;
import com.huawei.openstack4j.model.map.reduce.ServiceConfig;

/**
 * Builder interface used for {@link ClusterTemplate} object.
 * 
 * @author Ekasit Kijsipongse
 */
public interface ClusterTemplateBuilder extends Builder<ClusterTemplateBuilder, ClusterTemplate> {

        //
        // Define setter apis for user to create a new cluster template
        //
        
	/**
	 * See {@link ClusterTemplate#getPluginName()}
	 * 
	 * @param pluginName the name of the MapReduce plugin
	 * @return ClusterTemplateBuilder
	 */
	ClusterTemplateBuilder pluginName(String pluginName);

	/**
	 * See {@link ClusterTemplate#getHadoopVersion()}
	 * 
	 * @param hadoopVersion the version of hadoop
	 * @return ClusterTemplateBuilder
	 */
	ClusterTemplateBuilder hadoopVersion(String hadoopVersion);

         /**
          * Add a node group
          *
          * @param nodeGroup the node group
          * @return this builder
          */
        ClusterTemplateBuilder addNodeGroup(NodeGroup nodeGroup);

	/**
	 * See {@link ClusterTemplate#getName()}
	 * 
	 * @param name the name of the cluster template
	 * @return ClusterTemplateBuilder
	 */
	ClusterTemplateBuilder name(String name);
	
	/**
	 * See {@link ClusterTemplate#getDescription()}
	 * 
	 * @param description the description of cluster template
	 * @return ClusterTemplateBuilder
	 */
	ClusterTemplateBuilder description(String description);


        /**
         * See {@link ClusterTemplate#getNeutronManagementNetworkId()}
         * 
         * @param  networkId the id of management network
         * @return ClusterTemplateBuilder
         */
        ClusterTemplateBuilder managementNetworkId(String networkId);

         /**
          * Add a cluster config
          *
          * @param name the service name
          * @param config the config
          * @return this builder
          */
        ClusterTemplateBuilder addServiceConfig(String name, ServiceConfig config);

}

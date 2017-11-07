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
import com.huawei.openstack4j.model.map.reduce.NodeGroup;
import com.huawei.openstack4j.model.map.reduce.ServiceConfig;
import com.huawei.openstack4j.model.map.reduce.builder.NodeGroupBuilder;

/**
 * Builder interface used for {@link NodeGroup} object.
 * 
 * @author Ekasit Kijsipongse
 */
public interface NodeGroupBuilder extends Builder<NodeGroupBuilder, NodeGroup> {

        //
        // Define setter apis for user to create a new node group
        //
        
	/**
	 * See {@link NodeGroup#getName()}
	 * 
	 * @param name the name of the node group 
	 * @return NodeGroupBuilder
	 */
	NodeGroupBuilder name(String name);
	
	/**
	 * See {@link NodeGroup#getCount()}
	 * 
	 * @param count the number of instances in the node group
	 * @return NodeGroupBuilder
	 */
	NodeGroupBuilder count(int count);

	/**
	 * See {@link NodeGroup#getNodeGroupTemplateId()}
	 * 
	 * @param templateId the id of the node group template
	 * @return NodeGroupBuilder
	 */
	NodeGroupBuilder nodeGroupTemplateId(String templateId);
	
        /**
         * See {@link NodeGroup#getFloatingNetworkId()}
         * 
         * @param  networkId the id of floating IP Pool
         * @return NodeGroupBuilder
         */
        NodeGroupBuilder floatingIpPool(String networkId);

        /**
         * See {@link NodeGroup#getFlavorId()}
         * 
         * @param flavorId the id of flavor
         * @return NodeGroupBuilder
         */
        NodeGroupBuilder flavor(String flavorId);

         /**
          * Set the security group.
          *
          * @param isAutoSecurityGroup true or false
          * @return this builder
          */
        NodeGroupBuilder setAutoSecurityGroup(boolean isAutoSecurityGroup);

         /**
          * Add the security group.
          *
          * @param id the id
          * @return this builder
          */
        NodeGroupBuilder addSecurityGroup(String id);

         /**
          * Add a node process
          *
          * @param name the name
          * @return this builder
          */
        NodeGroupBuilder addNodeProcess(String name);

         /**
          * Add a service config
          *
          * @param name the service name
          * @param config the config
          * @return this builder
          */
        NodeGroupBuilder addServiceConfig(String name, ServiceConfig config);

}

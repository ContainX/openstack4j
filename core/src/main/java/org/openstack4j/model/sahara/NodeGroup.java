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
package org.openstack4j.model.sahara;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.sahara.builder.NodeGroupBuilder;

/**
 * A Sahara Node Group 
 * 
 * @author Ekasit Kijsipongse
 */
public interface NodeGroup extends ModelEntity, Buildable<NodeGroupBuilder> {

	/**
	 * @return the name of the node group
	 */
	String getName();

        /**
         * @return the number of instances in node groups
         */
        Integer getCount();

        /**
         * @return the id of node group template
         */
        String getNodeGroupTemplateId();

	/**
	 * @return the created date 
	 */
	Date getCreatedAt();
	
	/**
	 * @return the updated date 
	 */
	Date getUpdatedAt();
	
	/**
	 * @return the id of floating IP Pool
	 */
	String getFloatingNetworkId();
	
	/**
	 * @return the number of volumes per node
	 */
	Integer getVolumesPerNode();

	/**
	 * @return the volumes size
	 */
	Integer getVolumesSize();
	
	/**
	 * @return the volume mount prefix
	 */
	String getVolumeMountPrefix();
	
	/**
	 * @return the image identifier 
	 */
	String getImageId();
	
	/**
	 * @return the flavor identifier
	 */
	String getFlavorId();
	
        /**
         * @return the security groups
         */
        List<String> getSecurityGroups();

	/**
	 * @return the auto security group
	 */
	Boolean isAutoSecurityGroup();
	
	/**
	 * @return node processes
	 */
	List<String> getNodeProcesses();
	
	/**
	 * @return map of service configurations or null
	 */
	Map<String, ? extends ServiceConfig> getServiceConfigs();

	/**
	 * @return instances in the node group
	 */
	List< ? extends Instance> getInstances();
}

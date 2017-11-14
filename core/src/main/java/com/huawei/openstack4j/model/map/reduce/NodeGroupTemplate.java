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
package com.huawei.openstack4j.model.map.reduce;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.map.reduce.builder.NodeGroupTemplateBuilder;

/**
 * A map reduce Node Group Template
 * 
 * @author Ekasit Kijsipongse
 */
public interface NodeGroupTemplate extends ModelEntity, Buildable<NodeGroupTemplateBuilder> {

	/**
	 * @return the identifier 
	 */
	String getId();
	
	/**
	 * @return the name of the node group template
	 */
	String getName();
	
	/**
	 * @return the description of the node group template
	 */
	String getDescription();
	
	/**
	 * @return the hadoop version 
	 */
	String getHadoopVersion();
	
	/**
	 * @return the plugin name 
	 */
	String getPluginName();
	
	/**
	 * @return the tenant ID 
	 */
	String getTenantId();
	
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

}

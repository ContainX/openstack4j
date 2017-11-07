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
import com.huawei.openstack4j.model.map.reduce.builder.ClusterTemplateBuilder;

/**
 * A map reduce Cluster Template
 * 
 * @author Ekasit Kijsipongse
 */
public interface ClusterTemplate extends ModelEntity, Buildable<ClusterTemplateBuilder> {

	/**
	 * @return the hadoop version 
	 */
	String getHadoopVersion();

	/**
	 * @return the default image identifier 
	 */
	String getDefaultImageId();

	/**
	 * @return the name of the node group template
	 */
	String getName();

	/**
	 * @return the updated date 
	 */
	Date getUpdatedAt();

	/**
	 * @return the tenant ID 
	 */
	String getTenantId();

	/**
	 * @return the plugin name 
	 */
	String getPluginName();

	/**
	 * @return the list of anti affinity 
	 */
        List<String> getAntiAffinity();

	/**
	 * @return the description of the node group template
	 */
	String getDescription();

	/**
	 * @return the identifier 
	 */
	String getId();
	
	/**
	 * @return the list of node groups 
	 */
	List<? extends NodeGroup> getNodeGroups();

	/**
	 * @return the id of neutron management network
	 */
	String getManagementNetworkId();
	
	/**
	 * @return the created date 
	 */
	Date getCreatedAt();
	
	/**
	 * @return map of cluster configurations or null
	 */
	Map<String, ? extends ServiceConfig> getClusterConfigs();

}

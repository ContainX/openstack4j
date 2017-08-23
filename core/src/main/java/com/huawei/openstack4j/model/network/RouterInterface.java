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
package com.huawei.openstack4j.model.network;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * An interface data model which is returned during interface association with a router
 * 
 * @author Jeremy Unruh
 */
public interface RouterInterface extends ModelEntity {

	/**
	 * @return the router identifier
	 */
	String getId();
	
	/**
	 * @return the subnet identifier or null if no subnet is associated
	 */
	String getSubnetId();
	
	/**
	 * @return the port identifier or null if no port is associated
	 */
	String getPortId();
	
	/**
	 * @return the tenant identifier or null
	 */
	String getTenantId();
}

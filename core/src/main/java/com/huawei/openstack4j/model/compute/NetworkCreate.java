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
package com.huawei.openstack4j.model.compute;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * A network which allows an association to a network UUID and a specified Fix IP Address
 * 
 * @author Jeremy Unruh
 */
public interface NetworkCreate extends ModelEntity {
	
	/**
	 * @return the network UUID
	 */
	String getId();

	/**
	 * @return the fixed IP Address
	 */
	String getFixedIp();
	
	/**
	 * port id of a pre-made port
	 * @return 
	 */
	String getPort();

}

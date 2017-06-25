/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
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
package org.openstack4j.model.loadbalance;

import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.IdResourceEntity;

public interface Server extends ModelEntity {
	
	/**
	 * @return server id
	 */
	String getId();

	/**
	 * @return server name
	 */
	String getServerName();
	
	/**
	 * @return original server id
	 */
	String getServerId();
	
	/**
	 * @return server address
	 */
	String getServerAddress();
	
	/**
	 * @return server float address
	 */
	String getAddress();
	
	/**
	 * @return server status
	 */
	String getStatus();
	
	/**
	 * @return health check status
	 */
	String getHealthStatus();
	
	/**
	 * @return server listeners
	 */
	List<IdResourceEntity> getListeners();
	
}

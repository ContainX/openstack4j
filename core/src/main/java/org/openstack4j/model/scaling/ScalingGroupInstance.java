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
package org.openstack4j.model.scaling;

import java.util.Date;

import org.openstack4j.model.ModelEntity;

public interface ScalingGroupInstance extends ModelEntity {
	/**
	 * @return instance id
	 */
	String getInstanceId();
	
	/**
	 * @return instance name
	 */
	String getInstanceName();
	
	/**
	 * @return scaling group id
	 */
	String getGroupId();
	
	/**
	 * @return scaling group name
	 */
	String getGroupName();
	
	/**
	 * @return life cycle state
	 */
	String getLifeCycleState();
	
	/**
	 * @return health status
	 */
	String getHealthStatus();
	
	/**
	 * @return configuration name
	 */
	String getConfigName();
	
	/**
	 * @return configuration id
	 */
	String getConfigId();
	
	/**
	 * @return create time of instance
	 */
	Date getCreateTime();
}

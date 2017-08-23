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
package com.huawei.openstack4j.model.magnum;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;

public interface Mservice extends ModelEntity, Buildable<MserviceBuilder>{
	
	/**
	 * Identifier of Magnum Service
	 * 
	 * @return the of Magnum Service
	 */
	String getId();
	
	/**
	 * Binary of the service
	 * 
	 * @return the binary of the service
	 */
	String getBinary();
	
	/**
	 * Creation date
	 * 
	 * @return the date of creation
	 */
	String getCreatedAt();
	
	/**
	 * Current state if it is up or not
	 * 
	 * @return the state of the service
	 */
	String getState();
	
	/**
	 * Report count
	 * 
	 * @return the report count of the service
	 */
	int getReportCount();
	
	/**
	 * The date of last updation
	 * 
	 * @return the last updated time
	 */
	String getUpdatedAt();
	
	/** 
	 * The host name
	 * 
	 * @return the host name
	 */
	String getHost();
	
	/**
	 * The disabled reason
	 * 
	 * @return the disabled reason
	 */
	String getDisabledReason();
}

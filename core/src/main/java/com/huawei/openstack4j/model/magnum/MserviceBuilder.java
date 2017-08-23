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

import com.huawei.openstack4j.common.Buildable.Builder;

/**
 * Builder which crates Magnum Service
 * 
 * @author Sohan Sangwan
 *
 */
		
public interface MserviceBuilder extends Builder<MserviceBuilder, Mservice> {
	 /**
     * @see Mservice#getId()
     */
	MserviceBuilder id(String id);
	
	 /**
     * @see Mservice#getBinary()
     */
	MserviceBuilder binary(String binary);
	
	 /**
     * @see Mservice#getCreatedAt()
     */
	MserviceBuilder createdAt(String createdAt);
	
	 /**
     * @see Mservice#getState()
     */
	MserviceBuilder state(String state);
	
	 /**
     * @see Mservice#getReportCount()
     */
	MserviceBuilder reportCount(int reportCount);
	
	 /**
     * @see Mservice#getUpdatedAt()
     */
	MserviceBuilder updatedAt(String updatedAt);
	
	 /**
     * @see Mservice#getHost()
     */
	MserviceBuilder host(String host);
	
	 /**
     * @see Mservice#getDisabledReason()
     */
	MserviceBuilder disabledReason(String disabledReason);
}

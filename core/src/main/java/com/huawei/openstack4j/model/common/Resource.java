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
package com.huawei.openstack4j.model.common;

/**
 * A Resource is a common model which contains an Id, TenantId and Name
 * 
 * @author Jeremy Unruh
 */
public interface Resource extends BasicResource {

	/**
	 * @return the tenant identifier for this resource
	 */
	String getTenantId();
	
	/**
	 * Sets the tenant identifier
	 * 
	 * @param tenantId the tenant id to set
	 */
	void setTenantId(String tenantId);
	
}

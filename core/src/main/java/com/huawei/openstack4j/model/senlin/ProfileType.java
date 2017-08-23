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
package com.huawei.openstack4j.model.senlin;

import java.util.Map;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * This interface describes the getter-methods (and thus components) of a ProfileType.
 * All getters map to the possible return values of
 * <code> GET /v1/profile-types/​{profile_type}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-clustering-v1.html
 * 
 * @author lion
 * 
 */
public interface ProfileType extends ModelEntity {

	/**
	 * Returns the name of the ProfileType
	 * 
	 * @return the name of the ProfileType
	 */
	String getName();

	/**
	 * Returns the schema of the ProfileType
	 *
	 * @return the schema of the ProfileType
	 */
	Map<String, Map> getSchema();
}

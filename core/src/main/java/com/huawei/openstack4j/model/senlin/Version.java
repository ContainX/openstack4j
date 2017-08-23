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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * This interface describes the getter-methods (and thus components) of the version of senlin.
 * All getters map to the possible return values of
 * <code> GET /</code>
 * 
 * @see http://developer.openstack.org/api-ref-clustering-v1.html
 * 
 * @author lion
 * 
 */
public interface Version extends ModelEntity {

	/**
	 * Returns the status of the senlin version
	 * 
	 * @return the status of the senlin version
	 */
	String getStatus();

	/**
	 * Returns the id of the senlin version
	 *
	 * @return the id of the senlin version
	 */
	String getId();

	/**
	 * Returns the links of the senlin version
	 *
	 * @return the links of the senlin version
	 */
	List<Map<String, String>> getLinks();
}

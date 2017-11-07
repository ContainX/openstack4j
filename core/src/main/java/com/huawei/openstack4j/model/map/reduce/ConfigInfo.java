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

import java.util.List;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * A map reduce Config Information
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 */
public interface ConfigInfo extends ModelEntity {

	/**
	 * @return the default value
	 */
	String getDefaultValue();

	/**
	 * @return the name
	 */
	String getName();

	/**
	 * @return the priority
	 */
	Integer getPriority();

	/**
	 * @return the type (string, int, bool, enum)
	 */
	String getType();

	/**
	 * @return the applicable target
	 */
	String getApplicableTarget();

	/**
	 * @return true if this config is optional
	 */
	Boolean isOptional();

	/**
	 * @return the scope
	 */
	String getScope();

	/**
	 * @return the description
	 */
	String getDescription();

	/**
	 * @return the list of valid config values (if type is enum)
	 */
	List<String> getConfigValues();
}

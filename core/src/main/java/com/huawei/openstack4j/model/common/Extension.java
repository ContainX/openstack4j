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

import java.net.URI;
import java.util.Date;
import java.util.List;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * Represents an Extension which adds additional functionality to the OpenStack API
 * 
 * @author Jeremy Unruh
 */
public interface Extension extends ModelEntity {

	/**
	 * @return the name of the extension
	 */
	String getName();
	
	/**
	 * @return the namespace of this extension
	 */
	URI getNamespace();
	
	/**
	 * @return the alias which is used within RestFul and other operational calls
	 */
	String getAlias();
	
	/**
	 * @return when the extension was last updated
	 */
	Date getUpdated();
	
	/**
	 * @return the description of this extension
	 */
	String getDescription();
	
	/**
	 * @return the additional information and documentation based links for this extension
	 */
	List<? extends Link> getLinks();
}

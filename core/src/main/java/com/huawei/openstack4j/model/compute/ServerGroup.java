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

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * An OpenStack ServerGroup which is a group that the server in it must be Located on different hosts
 * 
 * @author Octopus Zhang
 */
public interface ServerGroup extends ModelEntity {

	/**
	 * @return the id of this group
	 */
	String getId();
	
	/**
	 * @return the name of this group
	 */
	String getName();
	
	/**
	 * @return the servers in this group
	 */
	List<String> getMembers();
	
	/**
	 * @return the metadata of this group
	 */
	Map<String, String> getMetadata();
	
	/**
	 * @return the polices of this group
	 */
	List<String> getPolicies();
}

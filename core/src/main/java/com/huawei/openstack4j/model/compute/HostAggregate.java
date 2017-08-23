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

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.model.ModelEntity;
/**
 * Represents the HostAggregates
 * @author liujunpeng
 *
 */
public interface HostAggregate extends ModelEntity {

	/**
	 * Availability zone of aggregate
	 * @return Availability zone
	 */
	String getAvailabilityZone();
	/**
	 * 
	 * @return created time
	 */
	Date getCreate();
	/**
	 * 
	 * @return true:deleted;false:no
	 */
	boolean isDeleted();
	/**
	 * 
	 * @return deleted time
	 */
	Date getDeletedAt();
	
	/**
	 * Host ID to add to an aggregate, which is a collection of multiple groups
	 * of hosts that share common resources like storage and network.
	 * 
	 * @return hosts list
	 */
	List<String> getHosts();

	/**
	 * The ID associated with an aggregate
	 * 
	 * @return id
	 */
	String getId();
	/**
	 * Metadata value
	 * @return Map
	 */
	Map<String, String> getMetadata();
	/**
	 * 
	 * @return name
	 */
	String getName();
	/**
	 * 
	 * @return last updated time
	 */
	Date getUpdatedAt();
	
}

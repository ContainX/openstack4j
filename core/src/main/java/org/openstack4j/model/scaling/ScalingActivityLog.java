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

public interface ScalingActivityLog extends ModelEntity {
	/**
	 * @return status of activity log
	 */
	String getStatus();
	
	/**
	 * @return start time of activity log
	 */
	Date getStartTime();
	
	/**
	 * @return end time of activity log
	 */
	Date getEndTime();
	
	/**
	 * @return activity log id
	 */
	String getId();
	
	/**
	 * @return removed instance list
	 */
	String getInstanceRemovedList();
	
	/**
	 * @return deleted instance list 
	 */
	String getInstanceDeletedList();
	
	/**
	 * @return added instance list
	 */
	String getInstanceAddedList();
	
	/**
	 * @return scaling value
	 */
	String getScalingValue();
	
	/**
	 * @return description of activity log
	 */
	String getDescription();
	
	/**
	 * @return instance value
	 */
	Integer getInstanceValue();
	
	/**
	 * @return desire value
	 */
	Integer getDesireValue();
}

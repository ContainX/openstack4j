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
package com.huawei.openstack4j.model.telemetry;

import java.util.Date;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * Computed Statistics for a Query against a Meter
 * 
 * @author Jeremy Unruh
 */
public interface Statistics extends ModelEntity {

	/**
	 * @return The average of all of the volume values seen in the data
	 */
	Double getAvg();

	/**
	 * @return The number of samples seen
	 */
	Integer getCount();

	/**
	 * @return The difference, in seconds, between the oldest and newest timestamp
	 */
	Double getDuration();

	/**
	 * @return UTC date and time of the earliest timestamp, or the query start time
	 */
	Date getDurationStart();

	/**
	 * @return UTC date and time of the oldest timestamp, or the query end time
	 */
	Date getDurationEnd();

	/**
	 * @return The maximum volume seen in the data
	 */
	Double getMax();

	/**
	 * @return The minimum volume seen in the data
	 */
	Double getMin();
	
	/**
	 * @return The total of all of the volume values seen in the data
	 */
	Double getSum();

	/**
	 * @return The difference, in seconds, between the period start and end
	 */
	Integer getPeriod();

	/**
	 * @return UTC date and time of the period start
	 */
	Date getPeriodStart();

	/**
	 * @return UTC date and time of the period end
	 */
	Date getPeriodEnd();
	
	/**
	 * @return The unit type of the data set
	 */
	String getUnit();
	
	/**
   * @return The group-by of the data set
   */
	String getGroupBy();
}

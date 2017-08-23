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

import java.util.Map;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * A single measurement for a given meter and resource.
 * 
 * @author Jeremy Unruh
 */
public interface MeterSample extends ModelEntity {

	/**
	 * @return the name of the meter
	 */
	String getCounterName();
	
	/**
	 * @return the type of meter
	 */
	Meter.Type getCounterType();

	/**
	 * @return The unit of measure for the value in the counter volume
	 */
	String getCounterUnit();

	/**
	 * @return the actual measured value
	 */
	Float getCounterVolume();

	/**
	 * @return The ID of the source that identifies where the sample comes from
	 */
	String getSource();

	/**
	 * @return he ID of the project or tenant that owns the resource
	 */
	String getProjectId();

	/**
	 * @return The ID of the user who last triggered an update to the resource
	 */
	String getUserId();

	/**
	 * @return The ID of the Resource for which the measurements are taken
	 */
	String getResourceId();

	/**
	 * @return UTC date and time when the measurement was made
	 */
	String getTimestamp();
	
	/**
   * @return UTC date and time when the sample was recorded
   */
	String getRecordedAt();

	/**
	 * @return A unique identifier for the sample
	 */
	String getMessageId();

	/**
	 * @return Arbitrary metadata associated with the resource
	 */
	Map<String, Object> getMetadata();

	/**
	 * @return the name of the meter
	 */
	void setCounterName(String counterName);
	
	/**
	 * @return the type of meter
	 */
	void setCounterType(Meter.Type meterType);

	/**
	 * @return The unit of measure for the value in the counter volume
	 */
	void setCounterUnit(String counterUnit);

	/**
	 * @return the actual measured value
	 */
	void setCounterVolume(Float counterVolume);

	/**
	 * @return The ID of the source that identifies where the sample comes from
	 */
	void setSource(String source);

	/**
	 * @return he ID of the project or tenant that owns the resource
	 */
	void  setProjectId(String projectId);

	/**
	 * @return The ID of the user who last triggered an update to the resource
	 */
	void setUserId(String userId);

	/**
	 * @return The ID of the Resource for which the measurements are taken
	 */
	void setResourceId(String resourceId);

	/**
	 * @return UTC date and time when the measurement was made
	 */
	void setTimestamp(String timestamp);
	
	/**
   * @return UTC date and time when the sample was recorded
   */
	void setRecordedAt(String date);

	/**
	 * @return A unique identifier for the sample
	 */
	void  setMessageId(String messageId);

	/**
	 * @return Arbitrary metadata associated with the resource
	 */
	void setMetadata(Map<String, Object> metadata);
}

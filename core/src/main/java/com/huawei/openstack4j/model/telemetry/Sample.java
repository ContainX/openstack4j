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
 * A single measurement for Sample.
 * 
 * @author Shital Patil
 */

public interface Sample extends ModelEntity {

    /**
     * @return the idof the meter
     */
    String getId();

    /**
     * @return Arbitrary metadata associated with the resource
     */
    Map<String, Object> getMetadata();

    /**
     * @return Arbitrary metadata associated with the resource
     */
    void setMetadata(Map<String, Object> metadata);

    /**
     * @return the meter
     */
    String getMeter();

    /**
     * @return the actual measured value
     */
    Float getVolume();

    /**
     * @return The ID of the source that identifies where the sample comes from
     */
    String getSource();

    /**
     * @return The ID of the source that identifies where the sample comes from
     */
    void setSource(String source);

    /**
     * @return The ID of the Resource for which the measurements are taken
     */
    String getResourceId();

    /**
     * @return The ID of the Resource for which the measurements are taken
     */
    void setResourceId(String resourceId);

    /**
     * @return UTC date and time when the measurement was made
     */
    String getTimestamp();

    /**
     * @return UTC date and time when the measurement was made
     */
    void setTimestamp(String timestamp);

    /**
     * @return UTC date and time when the sample was recorded
     */
    String getRecordedAt();

    /**
     * @return UTC date and time when the sample was recorded
     */
    void setRecordedAt(String date);

    /**
     * @return he ID of the project or tenant that owns the resource
     */
    String getProjectId();

    /**
     * @return he ID of the project or tenant that owns the resource
     */
    void setProjectId(String projectId);

    /**
     * @return the type of meter
     */
    Meter.Type getType();

    /**
     * @return The ID of the user who last triggered an update to the resource
     */
    String getUserId();

    /**
     * @return Returns unit
     */
    String getUnit();

}

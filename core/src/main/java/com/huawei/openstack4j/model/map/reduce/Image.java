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

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.huawei.openstack4j.model.ModelEntity;

/**
 * A map reduce image
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 */
public interface Image extends ModelEntity {

        enum Status {
            UNRECOGNIZED, ACTIVE, SAVING, QUEUED, KILLED, PENDING_DELETE, DELETED, ERROR; // Use Glance Image Status 

            @JsonCreator
            public static Status forValue(String v) {
                if (v == null) return UNRECOGNIZED;
                try {
                    return valueOf(v.toUpperCase());
                } catch (IllegalArgumentException e) {
                    return UNRECOGNIZED;
                }
            }
        }

	/**
	 * @return the status of this image
	 */
	Status getStatus();

	/**
	 * @return the username of this image
	 */
	String getUsername();

	/**
	 * @return the date the image was last updated
	 */
	Date getUpdated();
	
	/**
	 * @return the size in bytes
	 */
	long getSize();
	
	/**
	 * @return the descriptive name of the image
	 */
	String getName();
	
	/**
	 * @return the date the image was created
	 */
	Date getCreated();
	
	/**
	 * @return the tags associated with this image
	 */
	List<String> getTags();
	
	/**
	 * @return the minimum disk in bytes
	 */
	int getMinDisk();

	/**
	 * @return the progress of the image during upload or setup
	 */
	int getProgress();
	
	/**
	 * @return the minimum ram in bytes
	 */
	int getMinRam();
	
	/**
	 * @return extra metadata/specs associated with the image
	 */
	Map<String, Object> getMetaData();
	
	/**
	 * @return the identifier of this image
	 */
	String getId();
	
	/**
	 * @return the description of this image
	 */
	String getDescription();
	
}

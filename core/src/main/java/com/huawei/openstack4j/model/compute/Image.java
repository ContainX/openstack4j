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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.common.Link;

/**
 * An OpenStack image is a collection of files used to create a Server.  Users provide pre-built OS images by default and or custom
 * images can be built
 * 
 * @author Jeremy Unruh
 */
public interface Image extends ModelEntity {

	/**
	 * Status can be used while an image is being saved.  It provides state of the progress indicator.  Images with ACTIVE status
	 * are available for install.
	 */
	enum Status {
		UNRECOGNIZED, UNKNOWN, ACTIVE, SAVING, ERROR, DELETED;
		
		@JsonCreator
		public static Status forValue(String value) {
			if (value != null)
			{
				for (Status s : Status.values()) {
					if (s.name().equalsIgnoreCase(value))
						return s;
				}
			}
			return Status.UNKNOWN;
		}
	}

	/**
	 * @return the identifier of this image
	 */
	String getId();
	
	/**
	 * @return the descriptive name of the image
	 */
	String getName();
	
	/**
	 * @return the size in bytes
	 */
	long getSize();
	
	/**
	 * @return the minimum disk in bytes
	 */
	int getMinDisk();

	/**
	 * @return the minimum ram in bytes
	 */
	int getMinRam();
	
	/**
	 * @return the progress of the image during upload or setup
	 */
	int getProgress();
	
	/**
	 * @return the status of this image
	 */
	Status getStatus();
	
	/**
	 * @return the date the image was created
	 */
	Date getCreated();
	
	/**
	 * @return the date the image was last updated
	 */
	Date getUpdated();
	
	/**
	 * @return external reference links for the image
	 */
	List<? extends Link> getLinks();
	
	/**
	 * @return extra metadata/specs associated with the image
	 */
	Map<String, Object> getMetaData();
	
	/**
	 * Determines if this image is a snapshot
	 * 
	 * @return true if this image is a snapshot
	 */
	boolean isSnapshot();
	
}

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
package com.huawei.openstack4j.model.storage.block;

import com.huawei.openstack4j.model.ModelEntity;



/**
 * Provides volume attachment result
 * 
 * @author Octopus Zhang
 */
public interface VolumeAttachment extends ModelEntity {
	
	/**
	 * the device name in the server,like /dev/vdd
	 * @return device name
	 */
	String getDevice();
	/**
	 * return the host where volume is on
	 * @return hostname
	 */
	String getHostname();
    /**
	 * Gets the id of this volume attachment
	 * @return the id
	 */
	String getId();
	
	/**
	 * the server's id in this volume attachment 
	 * @return the  id of a server
	 */
	String getServerId();
	
	/**
	 * the volume's id in this volume attachment 
	 * @return the id of a volume
	 */
	String getVolumeId();
}
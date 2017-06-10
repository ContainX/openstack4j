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
package org.openstack4j.model.sahara;

import java.util.Date;
import java.util.List;

import org.openstack4j.model.ModelEntity;

/**
 * An Openstack Sahara Instance
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 */
public interface Instance extends ModelEntity {

	/**
	 * @return the name of the instance
	 */
	String getName();

	/**
	 * @return the created date of the instance
	 */
	Date getCreatedAt();

	/**
	 * @return the updated date of the instance
	 */
	Date getUpdatedAt();

	/**
	 * @return the NOVA instance identifier 
	 */
	String getInstanceId();
	
	/**
	 * @return the management IP of the instance
	 */
	String getManagementIp();
	
	/**
         * TODO: how volumes are presented?
	 * @return the volumes of the instance
	 */
	List<String> getVolumes();
	
	/**
	 * @return the internal IP of the instance
	 */
	String getInternalIp();
	
	/**
	 * @return the Sahara instance identifier 
	 */
	String getId();
	
}

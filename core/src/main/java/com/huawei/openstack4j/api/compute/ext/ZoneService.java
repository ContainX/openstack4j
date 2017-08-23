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
package com.huawei.openstack4j.api.compute.ext;


import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.compute.ext.AvailabilityZone;

/**
 * API which supports the "os-availability-zone" extension.  
 * 
 * @author octopus zhang
 */
public interface ZoneService extends RestService {

	/**
	 * List availability zone info .
	 *  
	 * NOTE: This is an extension and not all deployments support os-availability-zone
	 *  
	 * @return the available zones in brief form
	 */
	List<? extends AvailabilityZone> list();
	
	/**
     * List availability zone info .
     *  
     * NOTE: This is an extension and not all deployments support os-availability-zone
     *  
     * @param detailed if true (admin only) details information will be populated
     * @return the available zones resolved to the specified {@code detailed} param
     */
    List<? extends AvailabilityZone> list(boolean detailed);
}

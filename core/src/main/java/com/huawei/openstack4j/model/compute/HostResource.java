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

import com.huawei.openstack4j.model.ModelEntity;

/**
 * OS Host describes capabilities of each compute host where Nova servers are running on
 * 
 * @author Qin An
 */
public interface HostResource extends ModelEntity {
    /**
     * @return the number of CPUs of the compute host
     */
    public int getCpu();

    /**
     * @return the size of Disk the compute host has, in GB
     */
    public int getDiskInGb();

    /**
     * @return the Hostname of the compute host
     */
    public String getHost();

    /**
     * @return the size of Memory of the compute host has, in MB
     */
    public int getMemoryInMb();

    /**
     * @return the project id (or special name like total, used_now, used_max)
     */
    public String getProject();
    

	/**
	 * @return service
	 */
	String getService();
	
	/**
	 * @return zone
	 */
	String getZone();
	
	/**
	 * @return host name
	 */
	String getHostName();

}

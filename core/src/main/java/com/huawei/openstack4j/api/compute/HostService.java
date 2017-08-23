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
package com.huawei.openstack4j.api.compute;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.compute.HostResource;

/**
 * Nova OS Host Service
 * 
 * @author Qin An
 *
 */
public interface HostService extends RestService {

    /**
	 * Shows details for a specified host
	 *
	 * @param hostName
	 * @return the Resource of the Host specified
	 */
	public List<? extends HostResource> hostDescribe(String hostName);
	
	/**
     * List all host that the current tenant has access to
     * 
     * @return list of all hosts
     * @author Wang Ting/王婷
     */
    List<? extends HostResource> list();

    /**
     * Returns list of hosts filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     * @author Wang Ting/王婷
     */
    List<? extends HostResource> list(Map<String, String> filteringParams);

}

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
package com.huawei.openstack4j.api.networking.ext;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.HealthMonitorV2;
import com.huawei.openstack4j.model.network.ext.HealthMonitorV2Update;

/**
 * Networking (Neutron Lbaas) V2 healthmonitor Extention API
 * @author emjburns
 */
public interface HealthMonitorV2Service extends RestService {
    /**
     * List all healthMonitor  that the current tenant has access to
     *
     * @return list of all healthMonitorV2
     */
    List<? extends HealthMonitorV2> list();

    /**
     * Returns list of healthMonitorV2 filtered by parameters.
     *
     * @param filteringParams map (name, value) of filtering parameters
     * @return
     */
    List<? extends HealthMonitorV2> list(Map<String, String> filteringParams);

    /**
     * Get the specified healthMonitorV2 by ID
     *
     * @param healthMonitorId the healthMonitorV2 identifier
     * @return the healthMonitorV2 or null if not found
     */
    HealthMonitorV2 get(String healthMonitorId);

    /**
     * Delete the specified healthMonitor by ID
     * @param healthMonitorId the healthMonitorV2 identifier
     * @return the action response
     */
    ActionResponse delete(String healthMonitorId);

    /**
     * Create a healthMonitorV2
     * @param healthMonitor
     * @return HealthMonitorV2
     */
    HealthMonitorV2 create(HealthMonitorV2 healthMonitor);

    /**
     * Update a healthMonitorV2
     * @param healthMonitorId the healthMonitorV2 identifier
     * @param healthMonitor HealthMonitorV2Update
     * @return HealthMonitorV2
     */
    HealthMonitorV2 update(String healthMonitorId, HealthMonitorV2Update healthMonitor);
}

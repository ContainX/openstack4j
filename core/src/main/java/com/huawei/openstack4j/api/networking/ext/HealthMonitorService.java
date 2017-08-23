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
import com.huawei.openstack4j.model.network.ext.HealthMonitor;
import com.huawei.openstack4j.model.network.ext.HealthMonitorUpdate;
/**
 * Networking (Neutron) Lbaas healthmonitor Extension API
 * @author liujunpeng
 *
 */
public interface HealthMonitorService extends RestService {
    /**
     * List all healthMonitor  that the current tenant has access to
     *
     * @return list of all healthMonitor
     */
    List<? extends HealthMonitor> list();

    /**
     * Returns list of healthMonitor filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     */
    List<? extends HealthMonitor> list(Map<String, String> filteringParams);


    /**
     * Get the specified healthMonitor by ID
     *
     * @param healthMonitorId the healthMonitor identifier
     * @return the healthMonitor or null if not found
     */
    HealthMonitor get(String healthMonitorId);
    
    /**
     * Delete the specified healthMonitor by ID
     * @param healthMonitorId the healthMonitor identifier
     * @return the action response
     */
    ActionResponse delete(String healthMonitorId);
    /**
     * Create a healthMonitor
     * @param healthMonitor 
     * @return HealthMonitor
     */
    HealthMonitor create(HealthMonitor healthMonitor);
    /**
     * Update a healthMonitor
     * @param healthMonitorId the healthMonitor identifier
     * @param healthMonitor HealthMonitorUpdate
     * @return HealthMonitor
     */
    HealthMonitor update(String healthMonitorId,HealthMonitorUpdate healthMonitor);
}

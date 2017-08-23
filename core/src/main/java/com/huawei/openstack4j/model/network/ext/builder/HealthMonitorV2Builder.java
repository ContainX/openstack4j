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
package com.huawei.openstack4j.model.network.ext.builder;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.network.ext.HealthMonitorType;
import com.huawei.openstack4j.model.network.ext.HealthMonitorV2;

public interface HealthMonitorV2Builder extends Buildable.Builder<HealthMonitorV2Builder, HealthMonitorV2> {
    /**
     *
     * @param tenantId
     *            Owner of the VIP. Only an administrative user can specify a
     *            tenant ID other than its own.
     * @return HealthMonitorV2Builder
     */
    HealthMonitorV2Builder tenantId(String tenantId);

    /**
     *
     * @param type
     *            The type of probe, which is TCP, HTTP, or HTTPS, that is
     *            sent by the health monitor to verify the member state.
     * @return HealthMonitorV2Builder
     */
    HealthMonitorV2Builder type(HealthMonitorType type);

    /**
     *
     * @param delay
     *            The time, in seconds, between sending probes to members.
     * @return HealthMonitorV2Builder
     */
    HealthMonitorV2Builder delay(Integer delay);

    /**
     *
     * @param timeout
     *            Time in seconds to timeout each probe.
     * @return HealthMonitorV2Builder
     */
    HealthMonitorV2Builder timeout(Integer timeout);

    /**
     *
     * @param maxRetries
     *            Maximum consecutive health probe tries.
     * @return HealthMonitorV2Builder
     */
    HealthMonitorV2Builder maxRetries(Integer maxRetries);

    /**
     *
     * @param poolId
     *             Pool that this health monitor is assigned to
     * @return HealthMonitorV2Builder
     */
    HealthMonitorV2Builder poolId(String poolId);

    /**
     * Optional
     *
     * @param httpMethod
     *            GET/PUT/POST
     * @return HealthMonitorV2Builder
     */
    HealthMonitorV2Builder httpMethod(String httpMethod);

    /**
     * Optional
     *
     * @param urlPath
     *            Path portion of URI that will be probed if type is HTTP(S).
     * @return HealthMonitorV2Builder
     */
    HealthMonitorV2Builder urlPath(String urlPath);

    /**
     * Optional
     *
     * @param expectedCodes
     *            Expected HTTP codes for a passing HTTP(S) monitor.
     * @return HealthMonitorV2Builder
     */
    HealthMonitorV2Builder expectedCodes(String expectedCodes);

    /**
     * Optional
     *
     * @param adminStateUp
     *            The administrative state of the VIP. A valid value is true
     *            (UP) or false (DOWN). Default is true
     * @return HealthMonitorV2Builder
     */
    HealthMonitorV2Builder adminStateUp(boolean adminStateUp);
}

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

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.network.ext.HealthMonitorV2Update;

/**
 *  A builder to update a health monitor
 * @author ashleykasim
 *
 */
public interface HealthMonitorV2UpdateBuilder extends Builder<HealthMonitorV2UpdateBuilder,HealthMonitorV2Update> {
    /**
     * @param delay
     *            The time, in seconds, between sending probes to members.
     * @return HealthMonitorV2UpdateBuilder
     */
    HealthMonitorV2UpdateBuilder delay(Integer delay);

    /**
     *
     * @param urlPath
     *            Path portion of URI that will be probed if type is HTTP(S).
     * @return HealthMonitorUpdateBuilder
     */
    HealthMonitorV2UpdateBuilder urlPath(String urlPath);

    /**
     *
     * @param expectedCodes
     *            Expected HTTP codes for a passing HTTP(S) monitor.
     * @return HealthMonitorV2UpdateBuilder
     */
    HealthMonitorV2UpdateBuilder expectedCodes(String expectedCodes);

    /**
     *
     * @param httpMethod
     *            GET/PUT/POST
     * @return HealthMonitorV2UpdateBuilder
     */
    HealthMonitorV2UpdateBuilder httpMethod(String httpMethod);

    /**
     *
     * @param maxRetries
     *            Maximum consecutive health probe tries.
     * @return HealthMonitorV2UpdateBuilder
     */
    HealthMonitorV2UpdateBuilder maxRetries(Integer maxRetries);

    /**
     *
     * @param adminStateUp
     *            The administrative state of the VIP. A valid value is true
     *            (UP) or false (DOWN).
     * @return HealthMonitorV2UpdateBuilder
     */
    HealthMonitorV2UpdateBuilder adminStateUp(boolean adminStateUp);

    /**
     *
     * @param timeout
     *            Time in seconds to timeout each probe.
     * @return HealthMonitorV2UpdateBuilder
     */
    HealthMonitorV2UpdateBuilder timeout(Integer timeout);
}

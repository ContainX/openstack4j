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
package com.huawei.openstack4j.api.telemetry;

import com.huawei.openstack4j.common.RestService;

/**
 * Telemetry allows collection of Alarms and Measurements.
 * 
 * @author Jeremy Unruh
 */
public interface TelemetryService extends RestService {

    /**
     * Access to Metering (Measurements) API
     * 
     * @return the Meter Service API
     */
    MeterService meters();

    /**
     * Access to Alarms API
     *
     * @return the Alarm Service API
     */
    AlarmService alarms();

    /**
     * Access to Events API
     *
     * @return the Event Service API
     */
    EventService events();

    /**
     * Access to Samples API
     * 
     * @return the Sample Service API
     */
    SampleService samples();

    /**
     * Access to resource API
     * 
     * @return the Resource service API
     */
    ResourceService resources();

    /**
     * Access to capabilities service API
     * 
     * @return the capabilities service API
     */
    CapabilitiesService capabilities();
}

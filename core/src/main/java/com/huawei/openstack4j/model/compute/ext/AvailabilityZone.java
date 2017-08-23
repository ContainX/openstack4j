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
package com.huawei.openstack4j.model.compute.ext;

import java.util.Date;
import java.util.Map;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * Availability Zone Model Entity
 * 
 * @author octopus zhang
 */
public interface AvailabilityZone extends ModelEntity {

    /**
     * @return zone's state
     */
    ZoneState getZoneState();

    /**
     * @return hosts in the zone and nova services in each host, not really implemented yet
     */
    Map<String, Map<String, ? extends NovaService>> getHosts();

    /**
     * @return zone's name
     */
    String getZoneName();
    
    
    public interface ZoneState extends ModelEntity {

        /**
         * @return the state of zone
         */
        boolean getAvailable();
    }

    public interface NovaService extends ModelEntity {
        /**
         * @return whether the service is available 
         */
        boolean getAvailable();

        /**
         * @return the service's status
         */
        String getStatusActive();

        /**
         * @return the service's latest update time
         */
        Date getUpdateTime();
    }

}


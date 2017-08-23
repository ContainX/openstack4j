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
package com.huawei.openstack4j.openstack.storage.block.domain;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.openstack.common.ListResult;

public class ExtAvailabilityZone implements AvailabilityZone {

    private static final long serialVersionUID = 1L;

    ExtZoneState zoneState;
    String zoneName;

    /**
     * {@inheritDoc}
     */
    @Override
    public ZoneState getZoneState() {
        return zoneState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getZoneName() {
        return zoneName;
    }


    @JsonRootName("zoneState")
    static class ExtZoneState implements ZoneState {
        private static final long serialVersionUID = 1L;

        boolean available;

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean getAvailable() {
            return available;
        }

        @Override
        public String toString() {
            return toStringHelper(this).omitNullValues()
                     .add("available", available)
                     .toString();
        }
    }


    public static class AvailabilityZones extends ListResult<ExtAvailabilityZone> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("availabilityZoneInfo")
        private List<ExtAvailabilityZone> result;

        @Override
        protected List<ExtAvailabilityZone> value() {
            return result;
        }


    }
}

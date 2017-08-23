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
package com.huawei.openstack4j.openstack.compute.domain.ext;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.compute.ext.AvailabilityZone;
import com.huawei.openstack4j.openstack.common.ListResult;

public class ExtAvailabilityZone implements AvailabilityZone {

    private static final long serialVersionUID = 1L;

    ExtZoneState zoneState;
    String zoneName;
    Map<String, Map<String, ExtNovaService>> hosts;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Map<String, ? extends NovaService>> getHosts() {
        Map<String, Map<String, ? extends NovaService>> map = new HashMap<String, Map<String, ? extends NovaService>>();
        if (hosts != null)
        {
            for (Entry<String, Map<String, ExtNovaService>> entry : hosts.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    @Override
    public String toString() {
        return toStringHelper(this).omitNullValues()
                 .add("zoneState", zoneState).add("zoneName", zoneName).add("hosts", hosts)
                 .toString();
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

    static class ExtNovaService implements NovaService {
        private static final long serialVersionUID = 1L;

        boolean available;
        @JsonProperty("active")
        String statusActive;
        @JsonProperty("updated_at")
        Date updateTime;

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean getAvailable() {
            return available;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getStatusActive() {
            return statusActive;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Date getUpdateTime() {
            return updateTime;
        }

        @Override
        public String toString() {
            return toStringHelper(this).omitNullValues()
                     .add("available", available).add("active", statusActive).add("updateTime", updateTime)
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

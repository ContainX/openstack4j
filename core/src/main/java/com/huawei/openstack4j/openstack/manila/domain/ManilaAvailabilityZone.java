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
package com.huawei.openstack4j.openstack.manila.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.manila.AvailabilityZone;
import com.huawei.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * Represents an availability zone.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public class ManilaAvailabilityZone implements AvailabilityZone {
    private String name;
    private String id;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String getUpdatedAt() {
        return updatedAt;
    }

    public static class AvailabilityZones extends ListResult<ManilaAvailabilityZone> {
        private static final long serialVersionUID = 1L;

        @JsonProperty("availability_zones")
        private List<ManilaAvailabilityZone> availabilityZones;

        @Override
        protected List<ManilaAvailabilityZone> value() {
            return availabilityZones;
        }
    }
}

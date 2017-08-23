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
package com.huawei.openstack4j.openstack.networking.domain.ext.LoadBalancerV2StatusTree;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.network.ext.status.HealthMonitorV2Status;
import com.huawei.openstack4j.model.network.ext.status.LbPoolV2Status;
import com.huawei.openstack4j.model.network.ext.status.MemberV2Status;

import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * An object to hold status of lbaas v2 loadbalancer pool
 * @author emjburns
 */
@JsonRootName("pools")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NeutronLbPoolV2Status extends Status implements LbPoolV2Status {

    @JsonProperty("name")
    private String name;

    @JsonProperty("members")
    private List<MemberV2Status> memberStatuses;

    @JsonProperty("healthmonitor")
    private HealthMonitorV2Status healthMonitorStatus;

    @Override
    public List<MemberV2Status> getMemberStatuses(){
        return memberStatuses;
    }

    @Override
    public HealthMonitorV2Status getHeathMonitorStatus(){
        return healthMonitorStatus;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("memberStatuses", memberStatuses)
                .add("healthMonitorStatus", healthMonitorStatus)
                .add("operatingStatus", operatingStatus)
                .add("provisioningStatus", provisioningStatus)
                .toString();
    }
}

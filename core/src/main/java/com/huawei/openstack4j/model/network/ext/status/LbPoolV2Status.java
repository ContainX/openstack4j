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
package com.huawei.openstack4j.model.network.ext.status;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.networking.domain.ext.LoadBalancerV2StatusTree.NeutronLbPoolV2Status;

import java.util.List;

/**
 * The status of an lbaas v2 loadbalancer pool
 * @author emjburns
 */
@JsonDeserialize(as = NeutronLbPoolV2Status.class)
public interface LbPoolV2Status extends ModelEntity {
    /**
     * The id of the loadbalancer pool
     * @return id
     */
    public String getId();

    /**
     * The name of the loadbalancer pool
     * @return name
     */
    public String getName();

    /**
     * The operating status of the loadbalancer pool
     * @return operating status
     */
    public String getOperatingStatus();

    /**
     * The provisioning status of the loadbalancer pool
     * @return provisioning status
     */
    public String getProvisioningStatus();

    /**
     * The status of the healthmonitor associated with this pool
     * @return HealthMonitorV2Status
     */
    public HealthMonitorV2Status getHeathMonitorStatus();

    /**
     * The status of the members of the loadbalancer pool
     * @return list of MemberV2Status
     */
    public List<MemberV2Status> getMemberStatuses();
}

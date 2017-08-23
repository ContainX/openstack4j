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
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2;
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2Stats;
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2StatusTree;
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2Update;

/**
 * Networking (Neutron Lbaas) V2 loadbalancer Extention API
 * @author emjburns
 */
public interface LoadBalancerV2Service extends RestService {
    /**
     * List all loadbalancers  that the current tenant has access to
     *
     * @return list of all loadbalancers
     */
    List<? extends LoadBalancerV2> list();

    /**
     * Returns list of loadbalancers filtered by parameters.
     *
     * @param filteringParams map (name, value) of filtering parameters
     * @return list of loadbalancer fitered by filteringParams
     */
    List<? extends LoadBalancerV2> list(Map<String, String> filteringParams);

    /**
     * Get the specified loadbalancer by ID
     *
     * @param loadbalancerId the loadbalancer identifier
     * @return the loadbalancer or null if not found
     */
    LoadBalancerV2 get(String loadbalancerId);

    /**
     * Delete the specified loadbalancer by ID
     * @param loadbalancerId the loadbalancer identifier
     * @return the action response
     */
    ActionResponse delete(String loadbalancerId);
    /**
     * Create a loadbalancer
     * @param loadbalancer
     * @return loadbalancer
     */
    LoadBalancerV2 create(LoadBalancerV2 loadbalancer);
    /**
     * Update a loadbalancer
     * @param loadbalancerId the loadbalancer identifier
     * @param loadbalancer LoadBalancerV2Update
     * @return loadbalancer
     */
    LoadBalancerV2 update(String loadbalancerId, LoadBalancerV2Update loadbalancer);

    /**
     * Retrieve statistics on a loadbalancer
     * @param loadbalancerId
     * @return LoadBalancerV2Stats
     */
    LoadBalancerV2Stats stats(String loadbalancerId);

    /**
     * Retrieve the status tree of a loadbalancer
     * @param loadbalancerId
     * @return status
     */
    LoadBalancerV2StatusTree statusTree(String loadbalancerId);
}

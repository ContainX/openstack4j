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
package com.huawei.openstack4j.model.network.ext;

import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.network.ext.status.LoadBalancerV2Status;

/**
 * The status tree of an lbaas v2 loadbalancer
 * @author emjburns
 */
public interface LoadBalancerV2StatusTree extends ModelEntity {
    /**
     * Get the status tree of a loadbalancer
     * @return the loadbalancer status tree
     */
    public LoadBalancerV2Status getLoadBalancerV2Status();
}

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

/**
 * LBaaS (Load Balancer as a Service) V2 support
 * @author emjburns
 */
public interface LbaasV2Service {

    /**
     * @return the Lbaas loadbalancer Service API
     */
    LoadBalancerV2Service loadbalancer();

    /**
     * @return the Lbaas healthmonitor V2 Service API
     */
    HealthMonitorV2Service healthMonitor();

    /**
     * @return the Lbaas pool Service API
     */
    LbPoolV2Service lbPool();

    /**
     * @return the Lbaas V2 listener Service API
     */
    ListenerV2Service listener();
}

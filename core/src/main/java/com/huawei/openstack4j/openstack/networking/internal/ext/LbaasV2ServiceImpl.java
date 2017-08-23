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
package com.huawei.openstack4j.openstack.networking.internal.ext;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.networking.ext.HealthMonitorV2Service;
import com.huawei.openstack4j.api.networking.ext.LbPoolV2Service;
import com.huawei.openstack4j.api.networking.ext.LbaasV2Service;
import com.huawei.openstack4j.api.networking.ext.ListenerV2Service;
import com.huawei.openstack4j.api.networking.ext.LoadBalancerV2Service;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * OpenStack (Neutron) lbaas v2 service implementation
 * @author emjburns
 */
public class LbaasV2ServiceImpl extends BaseNetworkingServices implements LbaasV2Service {
    /**
     * {@inheritDoc}
     */
    @Override
    public ListenerV2Service listener(){
        return Apis.get(ListenerV2Service.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LbPoolV2Service lbPool(){
        return Apis.get(LbPoolV2Service.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HealthMonitorV2Service healthMonitor(){
        return Apis.get(HealthMonitorV2Service.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoadBalancerV2Service loadbalancer(){
        return Apis.get(LoadBalancerV2Service.class);
    }
}

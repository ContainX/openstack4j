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
import com.huawei.openstack4j.api.networking.ext.HealthMonitorService;
import com.huawei.openstack4j.api.networking.ext.LbPoolService;
import com.huawei.openstack4j.api.networking.ext.LoadBalancerService;
import com.huawei.openstack4j.api.networking.ext.MemberService;
import com.huawei.openstack4j.api.networking.ext.VipService;

/**
 * LBaaS Service Implementation
 */
public class LoadBalancerServiceImpl implements LoadBalancerService {

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberService member(){
        return Apis.get(MemberService.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public VipService vip() {
        return Apis.get(VipService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HealthMonitorService healthMonitor() {
        return Apis.get(HealthMonitorService.class);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public LbPoolService lbPool() {
        return Apis.get(LbPoolService.class);
    }

}

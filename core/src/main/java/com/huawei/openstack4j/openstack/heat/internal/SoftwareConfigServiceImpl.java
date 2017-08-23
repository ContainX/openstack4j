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
package com.huawei.openstack4j.openstack.heat.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import com.huawei.openstack4j.api.heat.SoftwareConfigService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.heat.SoftwareConfig;
import com.huawei.openstack4j.openstack.heat.domain.HeatSoftwareConfig;

/**
 * Software Configuration Service for HEAT Orchestration
 * 
 * @author Jeremy Unruh
 */
public class SoftwareConfigServiceImpl extends BaseHeatServices implements SoftwareConfigService {

    private static final String BASE_URI = "/software_configs";
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SoftwareConfig create(SoftwareConfig sc) {
        checkNotNull(sc);
        return post(HeatSoftwareConfig.class, BASE_URI).entity(sc).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SoftwareConfig show(String configId) {
        checkNotNull(configId);
        return get(HeatSoftwareConfig.class, uri(BASE_URI+"/%s", configId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String configId) {
        checkNotNull(configId);
        return deleteWithResponse(uri(BASE_URI+"/%s", configId)).execute();
    }

}

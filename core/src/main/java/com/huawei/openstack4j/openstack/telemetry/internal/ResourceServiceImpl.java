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
package com.huawei.openstack4j.openstack.telemetry.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.huawei.openstack4j.api.telemetry.ResourceService;
import com.huawei.openstack4j.model.telemetry.Resource;
import com.huawei.openstack4j.openstack.telemetry.domain.CeilometerResource;

/**
 * Provides Measurements for Telemetry resources within an OpenStack deployment
 * 
 * @author Shital Patil
 */
public class ResourceServiceImpl extends BaseTelemetryServices implements ResourceService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Resource> list() {
        CeilometerResource[] resources = get(CeilometerResource[].class, uri("/resources")).execute();
        return wrapList(resources);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Resource get(String resourceId) {
        checkNotNull(resourceId);
        CeilometerResource resource = get(CeilometerResource.class, uri("/resources/%s", resourceId)).execute();
        return resource;
    }

}

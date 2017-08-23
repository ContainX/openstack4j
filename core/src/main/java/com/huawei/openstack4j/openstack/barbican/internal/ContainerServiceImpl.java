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
package com.huawei.openstack4j.openstack.barbican.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableMap;

import com.huawei.openstack4j.api.barbican.ContainerService;
import com.huawei.openstack4j.model.barbican.Container;
import com.huawei.openstack4j.model.barbican.ContainerSecret;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.barbican.domain.BarbicanContainer;
import com.huawei.openstack4j.openstack.barbican.domain.BarbicanContainer.Containers;

import java.util.List;
import java.util.Map;

/**
 * {@inheritDoc}
 */
public class ContainerServiceImpl extends BaseBarbicanServices implements ContainerService {

    private static final String RESOURCE_PATH = "/containers";
    private static final String SPECIFIC_RESOURCE_PATH = RESOURCE_PATH + "/%s";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Container> list(Map<String, String> filteringParams) {
        Invocation<Containers> req = get(Containers.class, uri(RESOURCE_PATH));
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                req = req.param(entry.getKey(), entry.getValue());
            }
        }
        return req.execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Container> list(final String name) {
        return list(ImmutableMap.of("name", name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Container get(final String containerId) {
        checkNotNull(containerId);
        return get(BarbicanContainer.class, uri(SPECIFIC_RESOURCE_PATH, containerId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(final String containerId) {
        checkNotNull(containerId);
        return deleteWithResponse(uri(SPECIFIC_RESOURCE_PATH, containerId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Container create(final Container container) {
        checkNotNull(container);
        return post(BarbicanContainer.class, uri(RESOURCE_PATH)).entity(container).execute();
    }
}

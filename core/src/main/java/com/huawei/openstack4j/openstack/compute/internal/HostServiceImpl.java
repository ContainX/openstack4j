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
package com.huawei.openstack4j.openstack.compute.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.compute.HostService;
import com.huawei.openstack4j.model.compute.HostResource;
import com.huawei.openstack4j.openstack.compute.domain.NovaHost;
import com.huawei.openstack4j.openstack.compute.domain.NovaHostResource.NovaHostResources;

/**
 * OS Host Service
 * 
 * @author Qin An
 *
 */
public class HostServiceImpl extends BaseComputeServices implements HostService {

    @Override
	public List<? extends HostResource> hostDescribe(String hostName) {
		checkNotNull(hostName);
        return get(NovaHost.class, uri("/os-hosts/%s", hostName)).execute().getList();
    }
    
    /**
     * List all host that the current tenant has access to
     * {@inheritDoc}
     * @author Wang Ting/王婷
     */
	@Override
	public List<? extends HostResource> list() {
		Invocation<NovaHostResources> req = get(NovaHostResources.class, uri("/os-hosts"));
		 return req.execute().getList();
		
	}

    /**
     *  Returns list of hosts filtered by parameters.
     *  @param filteringParams map (name, value) of filtering parameters
     *  Now supports query by zone name 
     * {@inheritDoc}
     * @author Wang Ting/王婷
     */
	@Override
	public List<? extends HostResource> list(Map<String, String> filteringParams) {
		Invocation<NovaHostResources> req = get(NovaHostResources.class, uri("/os-hosts"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	req = req.param(entry.getKey(), entry.getValue());
            }
        }
		return req.execute().getList();
	}
}

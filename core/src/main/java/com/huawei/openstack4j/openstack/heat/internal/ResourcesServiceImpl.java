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

import com.google.common.net.*;

import com.huawei.openstack4j.api.heat.ResourcesService;
import com.huawei.openstack4j.model.common.*;
import com.huawei.openstack4j.model.heat.Resource;
import com.huawei.openstack4j.model.heat.ResourceHealth;
import com.huawei.openstack4j.openstack.heat.domain.HeatResource;
import com.huawei.openstack4j.openstack.heat.domain.HeatResource.Resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class implements some methods for manipulation of {@link HeatResource} objects. The
 * non-exhaustive list of methods is oriented along
 * http://developer.openstack.org/api-ref-orchestration-v1.html
 * 
 * @author Octopus Zhang
 * 
 */
public class ResourcesServiceImpl extends BaseHeatServices implements ResourcesService {

	@Override
	public List<? extends Resource> list(String stackName, String stackId) {
	    checkNotNull(stackName);
        checkNotNull(stackId);
		return get(Resources.class, uri("/stacks/%s/%s/resources", stackName, stackId)).execute().getList();
	}
	
	@Override
	public List<? extends Resource> list(String stackNameOrId) {
		return list(stackNameOrId, 1);
	}

	@Override
	public List<? extends Resource> list(String stackNameOrId, int depth) {
		checkNotNull(stackNameOrId);
		return get(HeatResource.Resources.class, uri("/stacks/%s/resources", stackNameOrId))
				.param("nested_depth", depth).execute().getList();
	}
	
	@Override
	public Resource show(String stackName, String stackId, String resourceName) {
	    checkNotNull(stackName);
        checkNotNull(stackId);
        checkNotNull(resourceName);
		return get(HeatResource.class, uri("/stacks/%s/%s/resources/%s", stackName, stackId, resourceName)).execute();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getMetadata(String stackName, String stackId, String resourceName) {
		checkNotNull(stackName);
		checkNotNull(stackId);
		checkNotNull(resourceName);
		return get(HashMap.class, uri("/stacks/%s/%s/resources/%s/metadata", stackName, stackId, resourceName)).execute();
	}

	@Override
	public ActionResponse signal(String stackName, String stackId, String resourceName) {
		checkNotNull(stackName);
		checkNotNull(stackId);
		checkNotNull(resourceName);
		return postWithResponse(uri("/stacks/%s/%s/resources/%s/signal", stackName, stackId, resourceName)).execute();
	}

	@Override
	public ActionResponse markUnhealthy(String stackName, String stackId, String resourceName, ResourceHealth resourceHealth) {
		checkNotNull(stackName);
		checkNotNull(stackId);
		return patchWithResponse(uri("/stacks/%s/%s/resources/%s", stackName, stackId, resourceName))
				.entity(resourceHealth)
				.execute();
	}
}

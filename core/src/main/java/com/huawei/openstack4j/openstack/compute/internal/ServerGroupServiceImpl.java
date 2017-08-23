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

import com.huawei.openstack4j.api.compute.ServerGroupService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.ServerGroup;
import com.huawei.openstack4j.openstack.compute.domain.NovaServerGroup;
import com.huawei.openstack4j.openstack.compute.domain.NovaServerGroup.ServerGroups;

public class ServerGroupServiceImpl extends BaseComputeServices implements ServerGroupService {

	@Override
	public List<? extends ServerGroup> list() {
		return get(ServerGroups.class, uri("/os-server-groups")).execute().getList();
	}

	@Override
	public ServerGroup get(String id) {
		checkNotNull(id);
		return get(NovaServerGroup.class, uri("/os-server-groups/%s", id)).execute();
	}

	@Override
	public ActionResponse delete(String id) {
		checkNotNull(id);
		return deleteWithResponse(uri("/os-server-groups/%s", id)).execute();
	}

	@Override
	public ServerGroup create(String name, String policy) {
		NovaServerGroup nsg = NovaServerGroup.create(name, policy);
		return  post(NovaServerGroup.class, uri("/os-server-groups")).entity(nsg).execute();
	}

}

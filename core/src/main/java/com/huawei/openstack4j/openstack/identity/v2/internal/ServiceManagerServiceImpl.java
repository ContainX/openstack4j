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
package com.huawei.openstack4j.openstack.identity.v2.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.huawei.openstack4j.api.identity.v2.ServiceManagerService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v2.Service;
import com.huawei.openstack4j.model.identity.v2.ServiceEndpoint;
import com.huawei.openstack4j.openstack.identity.v2.domain.KeystoneService;
import com.huawei.openstack4j.openstack.identity.v2.domain.KeystoneServiceEndpoint;
import com.huawei.openstack4j.openstack.identity.v2.domain.KeystoneService.Services;
import com.huawei.openstack4j.openstack.identity.v2.domain.KeystoneServiceEndpoint.ServiceEndpoints;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * Manages OpenStack service(s), such as Compute (Nova), Object Storage (Swift), or Image Service (Glance).
 * 
 * @author Jeremy Unruh
 */
public class ServiceManagerServiceImpl extends BaseOpenStackService implements ServiceManagerService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Service> list() {
		return get(Services.class, uri("/OS-KSADM/services")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Service get(String serviceId) {
		checkNotNull(serviceId);
		return get(KeystoneService.class, uri("/OS-KSADM/services/%s", serviceId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Service create(String name, String type, String description) {
		checkNotNull(name);
		checkNotNull(type);
		checkNotNull(description);
		return post(KeystoneService.class, uri("/OS-KSADM/services"))
						.entity(KeystoneService.builder().name(name).type(type).description(description).build())
						.execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String serviceId) {
		checkNotNull(serviceId);
		return deleteWithResponse(uri("/OS-KSADM/services/%s", serviceId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends ServiceEndpoint> listEndpoints() {
		return get(ServiceEndpoints.class, uri("/endpoints")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceEndpoint createEndpoint(String region, String serviceId, String publicURL, String adminURL, String internalURL) {
		checkNotNull(region);
		checkNotNull(serviceId);
		checkNotNull(publicURL);
		checkNotNull(adminURL);
		checkNotNull(internalURL);
		
		return post(KeystoneServiceEndpoint.class, uri("/endpoints"))
				    .entity(KeystoneServiceEndpoint.builder().region(region).serviceId(serviceId).publicURL(publicURL).adminURL(adminURL).internalURL(internalURL).build())
				    .execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse deleteEndpoint(String endpointId) {
		checkNotNull(endpointId);
		return deleteWithResponse(uri("/endpoints/%s", endpointId)).execute();
	}

}

/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
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
package org.openstack4j.openstack.loadbalance.internal;

import static com.google.common.base.Preconditions.checkArgument;

import org.openstack4j.api.loadbalance.ELBHealthCheckService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.loadbalance.HealthCheck;
import org.openstack4j.model.loadbalance.HealthCheckCreate;
import org.openstack4j.model.loadbalance.HealthCheckUpdate;
import org.openstack4j.openstack.loadbalance.domain.ELBHealthCheck;

import com.google.common.base.Strings;

public class ELBHealthCheckServiceImpl extends BaseELBServices implements ELBHealthCheckService {
	private static final String API_PATH = "/elbaas/healthcheck";

	@Override
	public HealthCheck create(HealthCheckCreate healthCheck) {
		checkArgument(healthCheck != null, "healthCheck is reuquired");
		checkArgument(!Strings.isNullOrEmpty(healthCheck.getListenerId()), "listenerId is required");

		return post(ELBHealthCheck.class, uri(API_PATH)).entity(healthCheck).execute();
	}

	@Override
	public ActionResponse delete(String healthCheckId) {
		checkArgument(!Strings.isNullOrEmpty(healthCheckId), "healthCheckId is required");
		return deleteWithResponse(uri("%s/%s", API_PATH, healthCheckId)).execute();
	}

	@Override
	public HealthCheck update(String healthCheckId, HealthCheckUpdate healthCheck) {
		checkArgument(!Strings.isNullOrEmpty(healthCheckId), "healthCheckId is required");
		checkArgument(healthCheck != null, "healthCheck is reuquired");

		return put(ELBHealthCheck.class, uri("%s/%s", API_PATH, healthCheck)).entity(healthCheck).execute();
	}

	@Override
	public HealthCheck get(String healthCheckId) {
		checkArgument(!Strings.isNullOrEmpty(healthCheckId), "healthCheckId is required");
		return get(ELBHealthCheck.class, uri("%s/%s", API_PATH, healthCheckId)).execute();
	}

}

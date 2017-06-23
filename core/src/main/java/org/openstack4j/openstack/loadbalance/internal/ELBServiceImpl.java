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

import org.openstack4j.api.Apis;
import org.openstack4j.api.loadbalance.AsyncJobService;
import org.openstack4j.api.loadbalance.ELBHealthCheckService;
import org.openstack4j.api.loadbalance.ELBListenerService;
import org.openstack4j.api.loadbalance.ELBService;
import org.openstack4j.api.loadbalance.ELBLoadBalancerService;

public class ELBServiceImpl extends BaseELBServices implements ELBService {
	
	@Override
	public ELBLoadBalancerService loadBalancers() {
		return Apis.get(ELBLoadBalancerService.class);
	}
	
	@Override
	public ELBListenerService listeners() {
		return Apis.get(ELBListenerService.class);
	}
	
	@Override
	public ELBHealthCheckService healthchecks() {
		return Apis.get(ELBHealthCheckService.class);
	}

	@Override
	public AsyncJobService jobs() {
		return Apis.get(AsyncJobService.class);
	}
}

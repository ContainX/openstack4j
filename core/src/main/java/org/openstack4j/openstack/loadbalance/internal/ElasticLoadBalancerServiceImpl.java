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

import java.util.List;

import org.openstack4j.api.loadbalance.ElasticLoadBalancerService;
import org.openstack4j.model.loadbalance.ElasticLoadBalancer;
import org.openstack4j.model.loadbalance.ElasticLoadBalancerCreate;
import org.openstack4j.model.loadbalance.ElasticLoadBalancerUpdate;
import org.openstack4j.openstack.loadbalance.domain.ELBJob;
import org.openstack4j.openstack.loadbalance.domain.ELBLoadBalancer;
import org.openstack4j.openstack.loadbalance.domain.ELBLoadBalancer.ELBLoadBalancers;
import org.openstack4j.openstack.loadbalance.options.ELBLoadBalancerListOptions;

import com.google.common.base.Strings;

public class ElasticLoadBalancerServiceImpl extends BaseElasticLoadBalanceServices
		implements ElasticLoadBalancerService {
	private static final String API_PATH = "/elbaas/loadbalancers";

	@Override
	public ELBJob create(ElasticLoadBalancerCreate loadBalancer) {
//		checkArgument(loadBalancer != null, "loadBalancer is required");
//		checkArgument(!Strings.isNullOrEmpty(loadBalancer.getName()), "name is required");
//		checkArgument(!Strings.isNullOrEmpty(loadBalancer.getVpcId()), "vpcId is required");
//		checkArgument(!Strings.isNullOrEmpty(loadBalancer.getType()), "type is required");
//		checkArgument(loadBalancer.getAdminStateUp() != null, "adminStateUp is required");

		return post(ELBJob.class, uri(API_PATH)).entity(loadBalancer).execute();
	}

	@Override
	public ELBJob delete(String loadBalancerId) {
		checkArgument(!Strings.isNullOrEmpty(loadBalancerId), "loadBalancerId is required");
		return delete(ELBJob.class, uri("%s/%s", API_PATH, loadBalancerId)).execute();
	}

	@Override
	public ELBJob update(String loadBalancerId, ElasticLoadBalancerUpdate loadBalancer) {
		checkArgument(!Strings.isNullOrEmpty(loadBalancerId), "loadBalancerId is required");
		checkArgument(loadBalancer != null, "loadBalancer is required");

		return put(ELBJob.class, uri("%s/%s", API_PATH, loadBalancerId)).execute();
	}

	@Override
	public ElasticLoadBalancer get(String loadBalancerId) {
		checkArgument(!Strings.isNullOrEmpty(loadBalancerId), "loadBalancerId is required");
		return get(ELBLoadBalancer.class, uri("%s/%s", API_PATH, loadBalancerId)).execute();
	}

	@Override
	public List<? extends ElasticLoadBalancer> list() {
		return get(ELBLoadBalancers.class, uri(API_PATH)).execute().getList();
	}

	@Override
	public List<? extends ElasticLoadBalancer> list(ELBLoadBalancerListOptions options) {
		checkArgument(options != null, "options is required");
		return get(ELBLoadBalancers.class, uri(API_PATH)).params(options.getOptions()).execute().getList();
	}

}

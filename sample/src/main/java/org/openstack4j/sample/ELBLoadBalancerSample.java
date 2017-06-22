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
package org.openstack4j.sample;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openstack4j.model.loadbalance.ElasticLoadBalancer;
import org.openstack4j.openstack.loadbalance.domain.ELBAsyncJob;
import org.openstack4j.openstack.loadbalance.domain.ELBJob;
import org.openstack4j.openstack.loadbalance.domain.ELBLoadBalancerCreate;
import org.openstack4j.openstack.loadbalance.domain.ELBLoadBalancerCreate.Type;
import org.openstack4j.openstack.loadbalance.domain.ELBLoadBalancerUpdate;
import org.openstack4j.openstack.loadbalance.options.ELBLoadBalancerListOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.google.common.base.Strings;

//TODO bill: test
public class ELBLoadBalancerSample extends AbstractSample {
	private static final Logger logger = LoggerFactory.getLogger(ELBLoadBalancerSample.class);

	@Test
	public void testCreateLoadBalancer() {
		String vpcId = "31d158b8-e7d7-4b4a-b2a7-a5240296b267";
		ELBLoadBalancerCreate loadBalancer = ELBLoadBalancerCreate.builder()
				.name("elb-4-test")
				.vpcId(vpcId)
				.type(Type.External.name()).adminStateUp(1).build();
		ELBJob job = osclient.elasticLoadBalance().loadBalancers().create(loadBalancer);
		logger.info("create load balancer: {}", job);
		assertTrue(!Strings.isNullOrEmpty(job.getJobId()));
	}

	@Test
	public void testDeleteLoadBalancer() {
		String loadBalancerId = "";
		ELBJob job = osclient.elasticLoadBalance().loadBalancers().delete(loadBalancerId);
		logger.info("delete load balancer: {}", job);
		assertTrue(!Strings.isNullOrEmpty(job.getJobId()));
	}

	@Test
	public void testUpdateLoadBalancer() {
		String loadBalancerId = "";
		ElasticLoadBalancer loadBalancer = osclient.elasticLoadBalance().loadBalancers().get(loadBalancerId);
		String after = new StringBuilder(loadBalancer.getName()).reverse().toString();
		ELBLoadBalancerUpdate update = ELBLoadBalancerUpdate.fromLoadBalancer(loadBalancer).toBuilder().name(after)
				.build();
		ELBJob updateJob = osclient.elasticLoadBalance().loadBalancers().update(loadBalancerId, update);
		logger.info("update load balancer{}", updateJob);
		assertTrue(!Strings.isNullOrEmpty(updateJob.getJobId()));
	}

	@Test
	public void testGetLoadBalancer() {
		String loadBalancerId = "";
		ElasticLoadBalancer loadBalancer = osclient.elasticLoadBalance().loadBalancers().get(loadBalancerId);
		logger.info("get load balancer: {}", loadBalancer);
		assertTrue(loadBalancer.getId().equals(loadBalancerId));
	}

	@Test
	public void testListLoadBalancer() {
		List<? extends ElasticLoadBalancer> all = osclient.elasticLoadBalance().loadBalancers().list();
		logger.info("list load balancer all: {}", all);

		String name = "elb-4-test";
		ELBLoadBalancerListOptions options = ELBLoadBalancerListOptions.create().name(name);
		List<? extends ElasticLoadBalancer> list = osclient.elasticLoadBalance().loadBalancers().list(options);
		logger.info("list load balancer with options: {}", list);
		if (list != null) {
			for (ElasticLoadBalancer loadBalancer : list) {
				assertTrue(name.equals(loadBalancer.getName()));
			}
		}
	}
	
	@Test
	public void testGetJob() {
		String jobId = "2c9eb2c05cbc6a07015cc9b371ee3be3";
		ELBAsyncJob job = osclient.elasticLoadBalance().jobs().get(jobId);
	}

}

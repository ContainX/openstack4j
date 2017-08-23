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
package com.huawei.openstack4j.sample.loadbalancer;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.google.common.base.Strings;

import com.huawei.openstack4j.model.loadbalance.LoadBalancer;
import com.huawei.openstack4j.model.loadbalance.LoadBalancer.Type;
import com.huawei.openstack4j.openstack.loadbalance.domain.AsyncJob;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBJob;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBLoadBalancerCreate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBLoadBalancerUpdate;
import com.huawei.openstack4j.openstack.loadbalance.options.ELBLoadBalancerListOptions;
import com.huawei.openstack4j.sample.AbstractSample;

public class ELBLoadBalancerSample extends AbstractSample {
	private static final Logger logger = LoggerFactory.getLogger(ELBLoadBalancerSample.class);

	@Test
	public void testCreateLoadBalancer() {
		String vpcId = "31d158b8-e7d7-4b4a-b2a7-a5240296b267";
		ELBLoadBalancerCreate loadBalancer = ELBLoadBalancerCreate.builder()
				.name("SDK-elb-4-test")
				.vpcId(vpcId)
				.bandwidth(1)
				.type(Type.EXTERNAL).adminStateUp(1).build();
		ELBJob job = osclient.loadBalancer().loadBalancers().create(loadBalancer);
		logger.info("create load balancer: {}", job);
		assertTrue(!Strings.isNullOrEmpty(job.getJobId()));
	}

	@Test
	public void testDeleteLoadBalancer() {
		String loadBalancerId = "336b38dd37a3420dbb797b44e96d4ebc";
		ELBJob job = osclient.loadBalancer().loadBalancers().delete(loadBalancerId);
		logger.info("delete load balancer: {}", job);
		assertTrue(!Strings.isNullOrEmpty(job.getJobId()));
	}

	@Test
	public void testUpdateLoadBalancer() {
		String loadBalancerId = "a650695bb9344a3fa24dec344116d261";
		LoadBalancer loadBalancer = osclient.loadBalancer().loadBalancers().get(loadBalancerId);
		String after = new StringBuilder(loadBalancer.getDescription()).reverse().toString();
		ELBLoadBalancerUpdate update = ELBLoadBalancerUpdate.fromLoadBalancer(loadBalancer).toBuilder().description(after)
				.build();
		ELBJob updateJob = osclient.loadBalancer().loadBalancers().update(loadBalancerId, update);
		logger.info("update load balancer{}", updateJob);
		assertTrue(!Strings.isNullOrEmpty(updateJob.getJobId()));
	}

	@Test
	public void testGetLoadBalancer() {
		String loadBalancerId = "336b38dd37a3420dbb797b44e96d4ebc";
		LoadBalancer loadBalancer = osclient.loadBalancer().loadBalancers().get(loadBalancerId);
		logger.info("get load balancer: {}", loadBalancer);
		assertTrue(loadBalancer.getId().equals(loadBalancerId));
	}

	@Test
	public void testListLoadBalancer() {
		List<? extends LoadBalancer> all = osclient.loadBalancer().loadBalancers().list();
		logger.info("list load balancer all: {}", all);

		String name = "elb-4-test";
		ELBLoadBalancerListOptions options = ELBLoadBalancerListOptions.create().name(name);
		List<? extends LoadBalancer> list = osclient.loadBalancer().loadBalancers().list(options);
		logger.info("list load balancer with options: {}", list);
		if (list != null) {
			for (LoadBalancer loadBalancer : list) {
				assertTrue(name.equals(loadBalancer.getName()));
			}
		}
	}
	
	@Test
	public void testGetJob() {
		String jobId = "2c9eb2c05cbc6a07015cde1091b918f9";
		AsyncJob job = osclient.loadBalancer().jobs().get(jobId);
	}

}

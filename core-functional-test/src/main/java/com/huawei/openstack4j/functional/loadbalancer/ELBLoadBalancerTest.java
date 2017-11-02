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
package com.huawei.openstack4j.functional.loadbalancer;

import static org.testng.Assert.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Strings;

import com.huawei.openstack4j.functional.Retry;
import com.huawei.openstack4j.model.loadbalance.LoadBalancer;
import com.huawei.openstack4j.model.loadbalance.LoadBalancer.Type;
import com.huawei.openstack4j.model.network.Router;
import com.huawei.openstack4j.openstack.loadbalance.domain.AsyncJob;
import com.huawei.openstack4j.openstack.loadbalance.domain.AsyncJob.Status;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBJob;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBLoadBalancerUpdate;
import com.huawei.openstack4j.openstack.loadbalance.options.ELBLoadBalancerListOptions;

public class ELBLoadBalancerTest extends BaseELBTest {

	String name = randomName();
	LoadBalancer lb = null;

	@BeforeClass
	public void testCreateLoadBalancer() {
		Router router = this.getFirstRouter();
		lb = createLoadBalancer(name, router.getId(), 1);
	}

	@AfterClass
	public void testDeleteLoadBalancer() {
		deleteLoadBalancer(lb.getId());
	}

	@Test
	public void testListLoadBalancer() {
		ELBLoadBalancerListOptions options = ELBLoadBalancerListOptions.create().name(name);
		List<? extends LoadBalancer> list = osclient.loadBalancer().loadBalancers().list(options);
		Assert.assertEquals(list.size(), 1);
		LoadBalancer loadBalancer = list.get(0);
		Assert.assertEquals(loadBalancer.getId(), lb.getId());
		Assert.assertEquals(loadBalancer.getDescription(), lb.getDescription());
		Assert.assertEquals(loadBalancer.getName(), lb.getName());
		Assert.assertEquals(loadBalancer.getBandwidth(), lb.getBandwidth());
		Assert.assertEquals(loadBalancer.getType(), Type.EXTERNAL);
	}

	@Test
	public void testGetLoadBalancer() {
		LoadBalancer get = osclient.loadBalancer().loadBalancers().get(lb.getId());
		Assert.assertEquals(get.getId(), lb.getId());
		Assert.assertEquals(get.getDescription(), lb.getDescription());
		Assert.assertEquals(get.getName(), lb.getName());
		Assert.assertEquals(get.getBandwidth(), lb.getBandwidth());
		Assert.assertEquals(get.getType(), Type.EXTERNAL);
	}

	@Test(dependsOnMethods = { "testGetLoadBalancer" })
	public void testUpdateLoadBalancer() {
		ELBLoadBalancerUpdate update = ELBLoadBalancerUpdate.fromLoadBalancer(lb).toBuilder()
				.description("sdk unittest update").bandwidth(2).adminStateUp(1).build();
		ELBJob updateJob = osclient.loadBalancer().loadBalancers().update(lb.getId(), update);
		assertTrue(!Strings.isNullOrEmpty(updateJob.getJobId()));

		this.retry(new Retry() {
			@Override
			public Integer maxRetryTimes() {
				return 40;
			}

			@Override
			public Integer delay() {
				return 5;
			}

			@Override
			public Object run() {
				AsyncJob asyncJob = osclient.loadBalancer().jobs().get(updateJob.getJobId());
				if (asyncJob.getStatus().equals(Status.SUCCESS)) {
					return asyncJob;
				}
				if (asyncJob.getStatus().equals(Status.FAIL)) {
					throw new RuntimeException("failed to update load balancer");
				}
				return null;
			}
		});

		LoadBalancer get = osclient.loadBalancer().loadBalancers().get(lb.getId());
		Assert.assertEquals(get.getId(), lb.getId());
		Assert.assertEquals(get.getDescription(), "sdk unittest update");
		Assert.assertEquals(get.getName(), lb.getName());
		Assert.assertEquals(get.getBandwidth().intValue(), 2);
		Assert.assertEquals(get.getType(), Type.EXTERNAL);

		lb = get;
	}

}

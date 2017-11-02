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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import com.huawei.openstack4j.functional.Retry;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.Address;
import com.huawei.openstack4j.model.loadbalance.Listener;
import com.huawei.openstack4j.model.loadbalance.Listener.BackendProtocol;
import com.huawei.openstack4j.model.loadbalance.Listener.LbAlgorithm;
import com.huawei.openstack4j.model.loadbalance.Listener.Protocol;
import com.huawei.openstack4j.model.loadbalance.Listener.StickySessionType;
import com.huawei.openstack4j.model.loadbalance.ListenerCreate;
import com.huawei.openstack4j.model.loadbalance.LoadBalancer;
import com.huawei.openstack4j.model.loadbalance.Server;
import com.huawei.openstack4j.model.loadbalance.ServerCreate;
import com.huawei.openstack4j.model.network.Router;
import com.huawei.openstack4j.openstack.loadbalance.domain.AsyncJob;
import com.huawei.openstack4j.openstack.loadbalance.domain.AsyncJob.Status;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBJob;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBListenerCreate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBListenerUpdate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBServerCreate;
import com.huawei.openstack4j.openstack.loadbalance.options.ELBListenerListOptions;

public class ELBListenerTest extends BaseELBTest {

	String name = randomName();
	LoadBalancer lb;
	Listener listener = null;

	@BeforeClass
	public void testCreateListener() {
		Router firstRouter = this.getFirstRouter();
		lb = createLoadBalancer(name, firstRouter.getId(), 1);
		ListenerCreate create = ELBListenerCreate.builder().name(name).loadBalancerId(lb.getId())
				.protocol(Protocol.HTTP).port(10086).backendProtocol(BackendProtocol.HTTP).backendPort(80)
				.lbAlgorithm(LbAlgorithm.ROUND_ROBIN).sessionSticky(true).stickySessionType(StickySessionType.INSERT)
				.cookieTimeout(60).build();
		listener = osclient.loadBalancer().listeners().create(create);
		assertTrue(!Strings.isNullOrEmpty(listener.getId()));
	}

	@AfterClass
	public void testDeleteListener() {
		// delete listener
		ActionResponse resp = osclient.loadBalancer().listeners().delete(listener.getId());
		assertTrue(resp.isSuccess(), resp.getFault());

		// delete load balancer
		deleteLoadBalancer(lb.getId());
	}

	@Test
	public void testListListener() {
		ELBListenerListOptions options = ELBListenerListOptions.create().name(name);
		Listener[] list = osclient.loadBalancer().listeners().list(options);
		Assert.assertEquals(list.length, 1);
	}

	@Test
	public void testGetListener() {
		Listener get = osclient.loadBalancer().listeners().get(listener.getId());
		Assert.assertEquals(get.getId(), listener.getId());
		Assert.assertEquals(get.getDescription(), listener.getDescription());
		Assert.assertEquals(get.getName(), listener.getName());
		Assert.assertEquals(get.getPort(), listener.getPort());
		Assert.assertEquals(get.getProtocol(), listener.getProtocol());
		Assert.assertEquals(get.getBackendPort(), listener.getBackendPort());
	}

	@Test(dependsOnMethods = { "testGetListener" })
	public void testUpdateListener() {
		ELBListenerUpdate update = ELBListenerUpdate.fromListener(listener).toBuilder()
				.description("sdk unittest update").port(10000).build();
		Listener updated = osclient.loadBalancer().listeners().update(listener.getId(), update);

		Assert.assertEquals(updated.getId(), listener.getId());
		Assert.assertEquals(updated.getDescription(), "sdk unittest update");
		Assert.assertEquals(updated.getName(), listener.getName());
		Assert.assertEquals(updated.getPort().intValue(), 10000);
		Assert.assertEquals(updated.getProtocol(), listener.getProtocol());
		Assert.assertEquals(updated.getBackendPort(), listener.getBackendPort());
	}

	@Test(dependsOnMethods = { "testUpdateListener" })
	public void testCreateServer() {
		ArrayList<ServerCreate> members = Lists.newArrayList();
		// 从已有的服务器里找到两台在同一个vpc
		List<? extends com.huawei.openstack4j.model.compute.Server> list = osclient.compute().servers().list();
		for (com.huawei.openstack4j.model.compute.Server server : list) {
			Set<String> routers = server.getAddresses().getAddresses().keySet();
			if (routers.contains(this.getFirstRouter().getId())) {
				List<? extends Address> addresses = server.getAddresses().getAddresses()
						.get(this.getFirstRouter().getId());
				String addr = addresses.get(0).getAddr();
				members.add(ELBServerCreate.builder().serverId(server.getId()).address(addr).build());
			}

			if (members.size() == 2) {
				break;
			}
		}

		// add member
		ELBJob job = osclient.loadBalancer().servers().create(listener.getId(), members);
		assertTrue(!Strings.isNullOrEmpty(job.getJobId()));

		// waiting for add member job done
		this.retry(new Retry() {

			@Override
			public Integer maxRetryTimes() {
				return 20;
			}

			@Override
			public Integer delay() {
				return 10;
			}

			@Override
			public Object run() {
				AsyncJob asyncJob = osclient.loadBalancer().jobs().get(job.getJobId());
				if (asyncJob.getStatus().equals(Status.SUCCESS)) {
					return asyncJob;
				}
				if (asyncJob.getStatus().equals(Status.FAIL)) {
					throw new RuntimeException("failed to create load balancer");
				}
				return null;
			}

		});

	}

	@Test(dependsOnMethods = { "testCreateServer" })
	public void testDeleteServer() {
		// try to list member
		Server[] all = osclient.loadBalancer().servers().list(listener.getId());
		Assert.assertEquals(all.length, 2);

		ArrayList<String> servers = Lists.newArrayList(all[0].getId(), all[1].getId());
		ELBJob job = osclient.loadBalancer().servers().delete(listener.getId(), servers);
		assertTrue(!Strings.isNullOrEmpty(job.getJobId()));

		// waiting for add member job done
		this.retry(new Retry() {

			@Override
			public Integer maxRetryTimes() {
				return 20;
			}

			@Override
			public Integer delay() {
				return 10;
			}

			@Override
			public Object run() {
				AsyncJob asyncJob = osclient.loadBalancer().jobs().get(job.getJobId());
				if (asyncJob.getStatus().equals(Status.SUCCESS)) {
					return asyncJob;
				}
				if (asyncJob.getStatus().equals(Status.FAIL)) {
					throw new RuntimeException("failed to remove listener member");
				}
				return null;
			}

		});
		
		Server[] deleted = osclient.loadBalancer().servers().list(lb.getId());
		Assert.assertEquals(deleted, null);
	}

}

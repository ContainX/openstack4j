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

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Strings;

import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.loadbalance.HealthCheck;
import com.huawei.openstack4j.model.loadbalance.HealthCheck.HealthCheckProtocol;
import com.huawei.openstack4j.model.loadbalance.Listener;
import com.huawei.openstack4j.model.loadbalance.Listener.BackendProtocol;
import com.huawei.openstack4j.model.loadbalance.Listener.LbAlgorithm;
import com.huawei.openstack4j.model.loadbalance.Listener.Protocol;
import com.huawei.openstack4j.model.loadbalance.Listener.StickySessionType;
import com.huawei.openstack4j.model.loadbalance.ListenerCreate;
import com.huawei.openstack4j.model.loadbalance.LoadBalancer;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBHealthCheckCreate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBHealthCheckUpdate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBListenerCreate;

public class ELBHealthCheckTest extends BaseELBTest {

	String name = randomName();
	LoadBalancer lb = null;
	Listener listener = null;
	HealthCheck healthCheck = null;

	@BeforeClass
	public void testCreateHealthCheck() {
		// create load balancer
		lb = this.createLoadBalancer(name, this.getFirstRouter().getId(), 1);

		// create listener
		ListenerCreate listenerCreate = ELBListenerCreate.builder().name(name).loadBalancerId(lb.getId())
				.protocol(Protocol.HTTP).port(10086).backendProtocol(BackendProtocol.HTTP).backendPort(80)
				.lbAlgorithm(LbAlgorithm.ROUND_ROBIN).sessionSticky(true).stickySessionType(StickySessionType.INSERT)
				.cookieTimeout(60).build();
		listener = osclient.loadBalancer().listeners().create(listenerCreate);

		ELBHealthCheckCreate create = ELBHealthCheckCreate.builder().listenerId(listener.getId())
				.healthCheckProtocol(HealthCheckProtocol.HTTP).healthCheckConnectPort(80).healthCheckInterval(5)
				.healthCheckTimeout(10).healthCheckUri("/ok").healthyThreshold(3).unhealthyThreshold(3).build();
		healthCheck = osclient.loadBalancer().healthchecks().create(create);

		assertTrue(!Strings.isNullOrEmpty(healthCheck.getId()));
	}

	@AfterClass
	public void testDeleteHealthCheck() {
		ActionResponse resp = osclient.loadBalancer().healthchecks().delete(healthCheck.getId());
		assertTrue(resp.isSuccess(), resp.getFault());

		// delete listener
		ActionResponse response = osclient.loadBalancer().listeners().delete(listener.getId());
		assertTrue(response.isSuccess(), response.getFault());

		// delete load balancer
		deleteLoadBalancer(lb.getId());
	}

	@Test
	public void testGetHealthCheck() {
		HealthCheck get = osclient.loadBalancer().healthchecks().get(healthCheck.getId());

		Assert.assertEquals(get.getId(), healthCheck.getId());
		Assert.assertEquals(get.getListenerId(), healthCheck.getListenerId());
		Assert.assertEquals(get.getHealthCheckConnectPort(), healthCheck.getHealthCheckConnectPort());
		Assert.assertEquals(get.getHealthCheckInterval(), healthCheck.getHealthCheckInterval());
		Assert.assertEquals(get.getHealthCheckProtocol(), healthCheck.getHealthCheckProtocol());
		Assert.assertEquals(get.getHealthCheckTimeout(), healthCheck.getHealthCheckTimeout());
		Assert.assertEquals(get.getHealthCheckUri(), healthCheck.getHealthCheckUri());
		Assert.assertEquals(get.getHealthyThreshold(), healthCheck.getHealthyThreshold());
		Assert.assertEquals(get.getUnhealthyThreshold(), healthCheck.getUnhealthyThreshold());

		healthCheck = get;
	}

	@Test(dependsOnMethods = { "testGetHealthCheck" })
	public void testUpdateHealthCheck() {
		ELBHealthCheckUpdate update = ELBHealthCheckUpdate.fromHealthCheck(healthCheck).toBuilder()
				.healthCheckProtocol(HealthCheckProtocol.TCP).healthCheckConnectPort(88).healthCheckInterval(6)
				.healthCheckTimeout(30).healthCheckUri("/ok2").healthyThreshold(2).unhealthyThreshold(2).build();

		HealthCheck updated = osclient.loadBalancer().healthchecks().update(healthCheck.getId(), update);
		Assert.assertEquals(updated.getId(), healthCheck.getId());
		Assert.assertEquals(updated.getListenerId(), healthCheck.getListenerId());
		Assert.assertEquals(updated.getHealthCheckConnectPort().intValue(), 88);
		Assert.assertEquals(updated.getHealthCheckInterval().intValue(), 6);
		Assert.assertEquals(updated.getHealthCheckProtocol(), HealthCheckProtocol.TCP);
		Assert.assertEquals(updated.getHealthCheckTimeout().intValue(), 30);
		Assert.assertEquals(updated.getHealthCheckUri(), "/ok2");
		Assert.assertEquals(updated.getHealthyThreshold().intValue(), 2);
		Assert.assertEquals(updated.getUnhealthyThreshold().intValue(), 2);
	}

}

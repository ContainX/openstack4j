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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.google.common.base.Strings;

import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.loadbalance.Listener;
import com.huawei.openstack4j.model.loadbalance.ListenerCreate;
import com.huawei.openstack4j.model.loadbalance.Listener.BackendProtocol;
import com.huawei.openstack4j.model.loadbalance.Listener.LbAlgorithm;
import com.huawei.openstack4j.model.loadbalance.Listener.Protocol;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBListenerCreate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBListenerUpdate;
import com.huawei.openstack4j.openstack.loadbalance.options.ELBListenerListOptions;
import com.huawei.openstack4j.sample.AbstractSample;

public class ELBListenerSample extends AbstractSample {
	private static final Logger logger = LoggerFactory.getLogger(ELBListenerSample.class);

	@Test
	public void testCreateListener() {
		String loadBalancerId = "a650695bb9344a3fa24dec344116d261";
		ListenerCreate listener = ELBListenerCreate.builder().name("SDK-test-listener").loadBalancerId(loadBalancerId)
				.protocol(Protocol.TCP).port(12345).backendProtocol(BackendProtocol.TCP)
				.backendPort(54321).lbAlgorithm(LbAlgorithm.ROUND_ROBIN).build();
		ListenerCreate create = osclient.loadBalancer().listeners().create(listener);
		logger.info("create: {}", create);
		assertTrue(!Strings.isNullOrEmpty(create.getId()));
	}

	@Test
	public void testDeleteListener() {
		String listenerId = "f5c566e27ebb4d5d8708fca77915a04b";
		ActionResponse resp = osclient.loadBalancer().listeners().delete(listenerId);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	@Test
	public void testUpdateListener() {
		String listenerId = "f5c566e27ebb4d5d8708fca77915a04b";
		Listener listener = osclient.loadBalancer().listeners().get(listenerId);

		String after = new StringBuilder(listener.getName()).reverse().toString();
		ELBListenerUpdate update = ELBListenerUpdate.fromListener(listener).toBuilder().name(after).build();

		Listener afterUpdate = osclient.loadBalancer().listeners().update(listenerId, update);
		logger.info("update:{}", afterUpdate);
		assertTrue(after.equals(afterUpdate.getName()));
	}

	@Test
	public void testGetListener() {
		String listenerId = "f5c566e27ebb4d5d8708fca77915a04b";
		Listener listener = osclient.loadBalancer().listeners().get(listenerId);
		logger.info("get:{}", listener);
		assertTrue(listener.getId().equals(listenerId));
	}

	@Test
	public void testListListener() {
		Listener[] all = osclient.loadBalancer().listeners().list();
		logger.info("all:{}", all);

		String name = "SDK";
		ELBListenerListOptions options = ELBListenerListOptions.create().name(name);
		Listener[] list = osclient.loadBalancer().listeners().list(options);
		logger.info("list:{}", list);
		if (list != null) {
			for (Listener listener : list) {
				assertTrue(listener.getName().contains(name));
			}
		}
	}
}

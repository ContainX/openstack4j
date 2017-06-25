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
package org.openstack4j.api.loadbalance;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.loadbalance.Listener;
import org.openstack4j.model.loadbalance.ListenerCreate;
import org.openstack4j.openstack.loadbalance.domain.ELBListenerCreate;
import org.openstack4j.openstack.loadbalance.domain.ELBListenerCreate.BackendProtocol;
import org.openstack4j.openstack.loadbalance.domain.ELBListenerCreate.LbAlgorithm;
import org.openstack4j.openstack.loadbalance.domain.ELBListenerCreate.Protocol;
import org.openstack4j.openstack.loadbalance.domain.ELBListenerUpdate;
import org.openstack4j.openstack.loadbalance.options.ELBListenerListOptions;
import org.testng.annotations.Test;

@Test(suiteName = "ELBLoadBalancer/ListenerV1")
public class ELBListenerV1Tests extends AbstractTest {

	private static final String JSON_LISTENER_CREATE = "/loadbalance/elb_listener_create.json";
	private static final String JSON_LISTENER = "/loadbalance/elb_listener.json";
	private static final String JSON_LISTENER_UPDATE = "/loadbalance/elb_listener_update.json";
	private static final String JSON_LISTENER_LIST = "/loadbalance/elb_listener_list.json";
	private static final String JSON_LISTENER_LIST2 = "/loadbalance/elb_listener_list2.json";

	public void testCreateListener() throws IOException {
		respondWith(JSON_LISTENER_CREATE);
		String loadBalancerId = "a650695bb9344a3fa24dec344116d261";
		ListenerCreate listener = ELBListenerCreate.builder().name("SDK-test-listener").loadBalancerId(loadBalancerId)
				.protocol(Protocol.TCP.name()).port(12345).backendProtocol(BackendProtocol.TCP.name())
				.backendPort(54321).lbAlgorithm(LbAlgorithm.roundrobin.name()).build();
		ListenerCreate create = osv3().elasticLoadBalance().listeners().create(listener);
		assertTrue("f5c566e27ebb4d5d8708fca77915a04b".equals(create.getId()));
	}

	public void testDeleteListener() {
		respondWith(204);
		String listenerId = "f5c566e27ebb4d5d8708fca77915a04b";
		ActionResponse resp = osv3().elasticLoadBalance().listeners().delete(listenerId);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	public void testUpdateListener() throws IOException {
		respondWith(JSON_LISTENER);
		respondWith(JSON_LISTENER_UPDATE);
		String listenerId = "f5c566e27ebb4d5d8708fca77915a04b";
		Listener listener = osv3().elasticLoadBalance().listeners().get(listenerId);
		assertTrue("f5c566e27ebb4d5d8708fca77915a04b".equals(listener.getId()));

		String after = new StringBuilder(listener.getName()).reverse().toString();
		ELBListenerUpdate update = ELBListenerUpdate.fromListener(listener).toBuilder().name(after).build();

		Listener afterUpdate = osv3().elasticLoadBalance().listeners().update(listenerId, update);
		assertTrue(after.equals(afterUpdate.getName()));
	}

	public void testGetListener() throws IOException {
		respondWith(JSON_LISTENER);
		String listenerId = "f5c566e27ebb4d5d8708fca77915a04b";
		Listener listener = osv3().elasticLoadBalance().listeners().get(listenerId);
		assertTrue(listener.getId().equals(listenerId));
	}

	public void testListListener() throws IOException {
		respondWith(JSON_LISTENER_LIST);
		respondWith(JSON_LISTENER_LIST2);
		Listener[] all = osv3().elasticLoadBalance().listeners().list();
		assertTrue(all.length == 5);

		String name = "SDK-test-listener";
		ELBListenerListOptions options = ELBListenerListOptions.create().name(name);
		Listener[] list = osv3().elasticLoadBalance().listeners().list(options);
		assertTrue(list.length == 1);
		if (list != null) {
			for (Listener listener : list) {
				assertTrue(listener.getName().contains(name));
			}
		}
	}

	@Override
	protected Service service() {
		return Service.LOAD_BALANCER;
	}
}

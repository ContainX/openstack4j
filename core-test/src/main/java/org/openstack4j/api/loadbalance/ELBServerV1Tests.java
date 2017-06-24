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
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.loadbalance.Server;
import org.openstack4j.model.loadbalance.ServerCreate;
import org.openstack4j.model.loadbalance.ServerDelete;
import org.openstack4j.openstack.common.IdResourceEntity;
import org.openstack4j.openstack.loadbalance.domain.ELBJob;
import org.openstack4j.openstack.loadbalance.domain.ELBServerCreate;
import org.openstack4j.openstack.loadbalance.domain.ELBServerDelete;
import org.openstack4j.openstack.loadbalance.options.ELBServerListOptions;
import org.openstack4j.openstack.loadbalance.options.ELBServerListOptions.HealthStatus;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

@Test(suiteName = "ELBLoadBalancer/ServerV1")
public class ELBServerV1Tests extends AbstractTest {

	private static final String JSON_SERVER_JOB = "/loadbalance/elb_server_job.json";
	private static final String JSON_SERVER_LIST = "/loadbalance/elb_server_list.json";
	private static final String JSON_SERVER_LIST2 = "/loadbalance/elb_server_list2.json";
	
	public void testCreateServer() throws IOException {
		respondWith(JSON_SERVER_JOB);
		String listenerId = "3a9fe2c9703c43e1ab761552a022c11e";
		String serverId = "a43d86c8-c2af-4c50-9b14-d32f99874885";
		ServerCreate server = ELBServerCreate.builder().serverId(serverId).address("127.0.0.1").build();
		List<ServerCreate> servers = Lists.newArrayList(server);
		ELBJob job = osv3().elasticLoadBalance().servers().create(listenerId, servers);
		assertTrue("2c9eb2c05cbc6a07015cd8c817925b98".equals(job.getJobId()));
	}

	public void testDeleteServer() throws IOException {
		respondWith(JSON_SERVER_JOB);
		String listenerId = "3a9fe2c9703c43e1ab761552a022c11e";
		String serverId = "b235e7d0-8f6d-4777-b9ea-1b1596241996";
		IdResourceEntity server = new IdResourceEntity();
		server.setId(serverId);
		List<IdResourceEntity> removeMember = Lists.newArrayList(server);
		ServerDelete servers = ELBServerDelete.builder().removeMember(removeMember).build();
		ELBJob job = osv3().elasticLoadBalance().servers().delete(listenerId, servers);
		assertTrue("2c9eb2c05cbc6a07015cd8c817925b98".equals(job.getJobId()));
	}

	public void testListServer() throws IOException {
		respondWith(JSON_SERVER_LIST);
		respondWith(JSON_SERVER_LIST2);
		String listenerId = "3a9fe2c9703c43e1ab761552a022c11e";
		Server[] all = osv3().elasticLoadBalance().servers().list(listenerId);
		assertTrue(all.length == 2);

		ELBServerListOptions options = ELBServerListOptions.create().healthStatus(HealthStatus.NORMAL);
		Server[] list = osv3().elasticLoadBalance().servers().list(listenerId, options);
		if (list != null) {
			for (Server server : list) {
				assertTrue(HealthStatus.NORMAL.name().equalsIgnoreCase(server.getHealthStatus()));
			}
		}
	}
	
	@Override
	protected Service service() {
		return Service.LOAD_BALANCER;
	}
}

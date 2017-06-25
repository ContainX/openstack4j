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

import org.openstack4j.model.loadbalance.Server;
import org.openstack4j.model.loadbalance.ServerCreate;
import org.openstack4j.model.loadbalance.ServerDelete;
import org.openstack4j.openstack.common.IdResourceEntity;
import org.openstack4j.openstack.loadbalance.domain.ELBJob;
import org.openstack4j.openstack.loadbalance.domain.ELBServerCreate;
import org.openstack4j.openstack.loadbalance.domain.ELBServerDelete;
import org.openstack4j.openstack.loadbalance.options.ELBServerListOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class ELBServerSample extends AbstractSample {
	private static final Logger logger = LoggerFactory.getLogger(ELBServerSample.class);

	@Test
	public void testCreateServer() {
		String listenerId = "3a9fe2c9703c43e1ab761552a022c11e";
		String serverId = "a4d346cd-eead-47b2-a26a-35fa05eaebbf";
		ServerCreate server = ELBServerCreate.builder().serverId(serverId).address("192.168.1.6").build();
		List<ServerCreate> servers = Lists.newArrayList(server);
		ELBJob job = osclient.loadBalancer().servers().create(listenerId, servers);
		logger.info("create job{}", job);
		assertTrue(!Strings.isNullOrEmpty(job.getJobId()));
	}

	@Test
	public void testDeleteServer() {
		String listenerId = "3a9fe2c9703c43e1ab761552a022c11e";
		String memberId = "6f829313333240aa9cbbaf241673dbe7";
		IdResourceEntity server = new IdResourceEntity();
		server.setId(memberId);
		List<IdResourceEntity> removeMember = Lists.newArrayList(server);
		ServerDelete servers = ELBServerDelete.builder().removeMember(removeMember).build();
		ELBJob job = osclient.loadBalancer().servers().delete(listenerId, servers);
		logger.info("delete job:{}", job);
		assertTrue(!Strings.isNullOrEmpty(job.getJobId()));
	}

	@Test
	public void testListServer() {
		String listenerId = "3a9fe2c9703c43e1ab761552a022c11e";
		Server[] all = osclient.loadBalancer().servers().list(listenerId);
		logger.info("all:{}", all);

		String address = "192.168.1.6";
		ELBServerListOptions options = ELBServerListOptions.create().address(address);
		Server[] list = osclient.loadBalancer().servers().list(listenerId, options);
		logger.info("list:{}", list);
		if (list != null) {
			for (Server server : list) {
				assertTrue(address.equals(server.getAddress()));
			}
		}
	}
}

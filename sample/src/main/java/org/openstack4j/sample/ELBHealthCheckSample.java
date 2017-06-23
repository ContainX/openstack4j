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

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.loadbalance.HealthCheck;
import org.openstack4j.model.loadbalance.HealthCheckCreate;
import org.openstack4j.openstack.loadbalance.domain.ELBHealthCheck.HealthCheckProtocol;
import org.openstack4j.openstack.loadbalance.domain.ELBHealthCheckCreate;
import org.openstack4j.openstack.loadbalance.domain.ELBHealthCheckUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.google.common.base.Strings;

public class ELBHealthCheckSample extends AbstractSample {
	private static final Logger logger = LoggerFactory.getLogger(ELBHealthCheckSample.class);

	@Test
	public void testCreateHealthCheck() {
		String listenerId = "cd9dc55344fd41b8b1aad5190d2b8dba";
		HealthCheckCreate healthCheck = ELBHealthCheckCreate.builder().listenerId(listenerId).build();
		HealthCheck create = osclient.elasticLoadBalance().healthchecks().create(healthCheck);

		logger.info("create: {}", create);
		assertTrue(!Strings.isNullOrEmpty(create.getId()));
	}

	@Test
	public void testDeleteHealthCheck() {
		String healthCheckId = "dbaad61e9d5341d7924ceac6bd2d4a77";
		ActionResponse resp = osclient.elasticLoadBalance().healthchecks().delete(healthCheckId);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	@Test
	public void testUpdateHealthCheck() {
		String healthCheckId = "dbaad61e9d5341d7924ceac6bd2d4a77";
		HealthCheck healthCheck = osclient.elasticLoadBalance().healthchecks().get(healthCheckId);

		String before = healthCheck.getHealthCheckProtocol();
		String after = HealthCheckProtocol.HTTP.name().equalsIgnoreCase(before) ? HealthCheckProtocol.TCP.name()
				: HealthCheckProtocol.HTTP.name();
		ELBHealthCheckUpdate update = ELBHealthCheckUpdate.fromHealthCheck(healthCheck).toBuilder()
				.healthCheckProtocol(after).healthCheckUri("/test").build();

		HealthCheck afterUpdate = osclient.elasticLoadBalance().healthchecks().update(healthCheckId, update);
		logger.info("update, before:{}, after:{}, {}", before, after, afterUpdate);
		assertTrue(afterUpdate.getHealthCheckProtocol().equalsIgnoreCase(after));
	}

	@Test
	public void testGetHealthCheck() {
		String healthCheckId = "dbaad61e9d5341d7924ceac6bd2d4a77";
		HealthCheck healthCheck = osclient.elasticLoadBalance().healthchecks().get(healthCheckId);
		logger.info("get:{}", healthCheck);
		assertTrue(healthCheck.getId().equals(healthCheckId));
	}
}

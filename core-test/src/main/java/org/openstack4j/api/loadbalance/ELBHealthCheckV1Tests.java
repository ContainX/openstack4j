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
import org.openstack4j.model.loadbalance.HealthCheck;
import org.openstack4j.model.loadbalance.HealthCheck.HealthCheckProtocol;
import org.openstack4j.model.loadbalance.HealthCheckCreate;
import org.openstack4j.openstack.loadbalance.domain.ELBHealthCheckCreate;
import org.openstack4j.openstack.loadbalance.domain.ELBHealthCheckUpdate;
import org.testng.annotations.Test;

@Test(suiteName = "ELBLoadBalancer/HealthCheckV1")
public class ELBHealthCheckV1Tests extends AbstractTest {

	private static final String JSON_HEALTH_CHECK_CREATE = "/loadbalance/elb_healthcheck_create.json";
	private static final String JSON_HEALTH_CHECK_UPDATE = "/loadbalance/elb_healthcheck_update.json";
	private static final String JSON_HEALTH_CHECK = "/loadbalance/elb_healthcheck.json";
	
	public void testCreateHealthCheck() throws IOException {
		respondWith(JSON_HEALTH_CHECK_CREATE);
		String listenerId = "cd9dc55344fd41b8b1aad5190d2b8dba";
		HealthCheckCreate healthCheck = ELBHealthCheckCreate.builder().listenerId(listenerId).build();
		HealthCheck create = osv3().loadBalancer().healthchecks().create(healthCheck);

		assertTrue("dbaad61e9d5341d7924ceac6bd2d4a77".equals(create.getId()));
	}

	public void testDeleteHealthCheck() {
		respondWith(204);
		String healthCheckId = "dbaad61e9d5341d7924ceac6bd2d4a77";
		ActionResponse resp = osv3().loadBalancer().healthchecks().delete(healthCheckId);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	public void testUpdateHealthCheck() throws IOException {
		respondWith(JSON_HEALTH_CHECK);
		respondWith(JSON_HEALTH_CHECK_UPDATE);
		String healthCheckId = "dbaad61e9d5341d7924ceac6bd2d4a77";
		HealthCheck healthCheck = osv3().loadBalancer().healthchecks().get(healthCheckId);

		HealthCheckProtocol before = healthCheck.getHealthCheckProtocol();
		HealthCheckProtocol after = HealthCheckProtocol.HTTP.equals(before) ? HealthCheckProtocol.TCP
				: HealthCheckProtocol.HTTP;
		ELBHealthCheckUpdate update = ELBHealthCheckUpdate.fromHealthCheck(healthCheck).toBuilder()
				.healthCheckProtocol(after).healthCheckUri("/test").build();

		HealthCheck afterUpdate = osv3().loadBalancer().healthchecks().update(healthCheckId, update);
		assertTrue(afterUpdate.getHealthCheckProtocol().equals(after));
	}

	public void testGetHealthCheck() throws IOException {
		respondWith(JSON_HEALTH_CHECK);
		String healthCheckId = "dbaad61e9d5341d7924ceac6bd2d4a77";
		HealthCheck healthCheck = osv3().loadBalancer().healthchecks().get(healthCheckId);
		assertTrue(healthCheck.getId().equals(healthCheckId));
	}
	@Override
	protected Service service() {
		return Service.LOAD_BALANCER;
	}
}

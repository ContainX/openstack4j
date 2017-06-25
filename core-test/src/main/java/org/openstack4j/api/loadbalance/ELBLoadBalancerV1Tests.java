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
import org.openstack4j.model.loadbalance.LoadBalancer;
import org.openstack4j.openstack.loadbalance.domain.ELBJob;
import org.openstack4j.openstack.loadbalance.domain.ELBLoadBalancerCreate;
import org.openstack4j.openstack.loadbalance.domain.ELBLoadBalancerCreate.Type;
import org.openstack4j.openstack.loadbalance.domain.ELBLoadBalancerUpdate;
import org.testng.annotations.Test;

@Test(suiteName = "ELBLoadBalancer/LoadBalancerV1")
public class ELBLoadBalancerV1Tests extends AbstractTest {

	private static final String JSON_LOAD_BLANACER_JOB = "/loadbalance/elb_loadbalancer_job.json";
	private static final String JSON_LOAD_BALANCER = "/loadbalance/elb_loadbalancer.json";
	private static final String JSON_LOAD_BALANCER_LIST = "/loadbalance/elb_loadbalancer_list.json";

	public void testCreateLoadBalancer() throws IOException {
		respondWith(JSON_LOAD_BLANACER_JOB);
		String vpcId = "31d158b8-e7d7-4b4a-b2a7-a5240296b267";
		ELBLoadBalancerCreate loadBalancer = ELBLoadBalancerCreate.builder().name("SDK-elb-4-test").vpcId(vpcId)
				.type(Type.External.name()).bandwidth(1).adminStateUp(1).build();
		ELBJob job = osv3().loadBalancer().loadBalancers().create(loadBalancer);
		assertTrue("2c9eb2c15cbc6bfd015cd3e250af1bde".equals(job.getJobId()));
	}

	public void testDeleteLoadBalancer() throws IOException {
		respondWith(JSON_LOAD_BLANACER_JOB);
		String loadBalancerId = "loadBalancerId";
		ELBJob job = osv3().loadBalancer().loadBalancers().delete(loadBalancerId);
		assertTrue("2c9eb2c15cbc6bfd015cd3e250af1bde".equals(job.getJobId()));
	}

	public void testUpdateLoadBalancer() throws IOException {
		respondWith(JSON_LOAD_BALANCER);
		respondWith(JSON_LOAD_BLANACER_JOB);
		String loadBalancerId = "a650695bb9344a3fa24dec344116d261";
		LoadBalancer loadBalancer = osv3().loadBalancer().loadBalancers().get(loadBalancerId);
		String after = new StringBuilder(loadBalancer.getDescription()).reverse().toString();
		ELBLoadBalancerUpdate update = ELBLoadBalancerUpdate.fromLoadBalancer(loadBalancer).toBuilder()
				.description(after).build();
		ELBJob job = osv3().loadBalancer().loadBalancers().update(loadBalancerId, update);
		assertTrue("2c9eb2c15cbc6bfd015cd3e250af1bde".equals(job.getJobId()));
	}

	public void testGetLoadBalancer() throws IOException {
		respondWith(JSON_LOAD_BALANCER);
		String loadBalancerId = "a650695bb9344a3fa24dec344116d261";
		LoadBalancer loadBalancer = osv3().loadBalancer().loadBalancers().get(loadBalancerId);
		assertTrue(loadBalancer.getId().equals(loadBalancerId));
	}

	public void testListLoadBalancer() throws IOException {
		respondWith(JSON_LOAD_BALANCER_LIST);
		List<? extends LoadBalancer> all = osv3().loadBalancer().loadBalancers().list();
		assertTrue(all.size() == 5);
	}

	@Override
	protected Service service() {
		return Service.LOAD_BALANCER;
	}
}

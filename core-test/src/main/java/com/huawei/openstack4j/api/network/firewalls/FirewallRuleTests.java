/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package com.huawei.openstack4j.api.network.firewalls;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.google.common.base.Preconditions;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.IPVersionType;
import com.huawei.openstack4j.model.network.ext.FirewallRule;
import com.huawei.openstack4j.model.network.ext.FirewallRuleUpdate;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewallRule;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewallRule.FirewallRuleAction;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewallRule.IPProtocol;

/**
 * Test suite for FirewallRule As a Service : FirewallRule Rule {@link FirewallRule} (FwaaS)
 * 
 * @author Vishvesh Deshmukh
 */
@Test(suiteName="Network/FirewallRule", enabled = false)
public class FirewallRuleTests extends AbstractTest {
	
	private static final String FIREWALL_RULE = "/network/firewalls/firewallrule.json";
	private static final String FIREWALL_RULES = "/network/firewalls/firewallrules.json";
	private static final String FIREWALL_RULE_UPDATE = "/network/firewalls/firewallruleupdate.json";
	
	public void testListFirewallRules() throws IOException {
	    respondWith(FIREWALL_RULES);
		List<? extends FirewallRule> list = osv3().networking().firewalls().firewallrule().list();
		assertEquals(1, list.size());
		Preconditions.checkNotNull(list.get(0));
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : FirewallRule from List : "+list.get(0));
		assertEquals(list.get(0).getId(), "8722e0e0-9cc9-4490-9660-8c9a5732fbb0");
	}
	
	public void testGetFirewallRule() throws IOException {
	    respondWith(FIREWALL_RULE);
		String id = "8722e0e0-9cc9-4490-9660-8c9a5732fbb0";
		FirewallRule firewallRule = osv3().networking().firewalls().firewallrule().get(id);
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : FirewallRule by ID : "+firewallRule);
		assertNotNull(firewallRule);
		assertEquals(firewallRule.getId(), id);
		assertEquals(firewallRule.getAction(), NeutronFirewallRule.FirewallRuleAction.ALLOW);
	}
	
	public void testCreateFirewallRule() throws IOException {
		respondWith(FIREWALL_RULE);
		FirewallRule create = Builders.firewallRule()
				  .description("Sample-Description").name("Sample-Firewall-Rule-Create")
				  .shared(Boolean.TRUE).action(FirewallRuleAction.ALLOW)
				  .sourceIpAddress("50.0.0.5").destinationIpAddress("5.0.0.10")
				  .sourcePort("50").destinationPort("80").name("ALLOW_HTTP")
				  .enabled(Boolean.TRUE).protocol(IPProtocol.TCP).ipVersion(IPVersionType.V4)
				  .tenantId("45977fa2dbd7482098dd68d0d8970117").build();
		FirewallRule result = osv3().networking().firewalls().firewallrule().create(create);
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created FirewallRule : "+result);
		
		assertEquals(result.getName(), "ALLOW_HTTP");
		assertEquals(result.getAction(), NeutronFirewallRule.FirewallRuleAction.ALLOW);
	}
	
	public void testUpdateFirewallRule() throws IOException {
	    respondWith(FIREWALL_RULE_UPDATE);
		FirewallRuleUpdate update = Builders.firewallRuleUpdate()
				.shared(Boolean.FALSE).enabled(Boolean.FALSE)
				.description("Test-Firewall-Update").build();
		
		FirewallRule result = osv3().networking().firewalls().firewallrule().update("8722e0e0-9cc9-4490-9660-8c9a5732fbb0", update);
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated FirewallRule : "+result);
		
		assertEquals("Sample-Firewall-Rule-Update", result.getDescription());
		assertEquals(result.getAction(), NeutronFirewallRule.FirewallRuleAction.DENY);
	}
	
	public void testDeleteFirewallRule() {
	    respondWith(200);
		ActionResponse result = osv3().networking().firewalls().firewallrule().delete("8722e0e0-9cc9-4490-9660-8c9a5732fbb0");
		assertTrue(result.isSuccess());
	}

	@Override
	protected Service service() {
		return Service.NETWORK;
	}

}

package org.openstack4j.api.network.firewalls;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.FirewallPolicy;
import org.openstack4j.model.network.ext.FirewallPolicyUpdate;
import org.openstack4j.openstack.networking.domain.ext.FirewallRuleStrategy.RuleInsertStrategyType;
import org.testng.annotations.Test;

import com.google.common.base.Preconditions;

/**
 * Test suite for FirewallPolicy As a Service : FirewallPolicy Policy {@link FirewallPolicy} (FwaaS)
 * 
 * @author Vishvesh Deshmukh
 */
@Test(suiteName="Network/FirewallPolicy", enabled = false)
public class FirewallPolicyTests extends AbstractTest {
	
	private static final String FIREWALL_POLICY = "/network/firewalls/firewallpolicy.json";
	private static final String FIREWALL_POLICIES = "/network/firewalls/firewallpolicies.json";
	private static final String FIREWALL_POLICY_RULE = "/network/firewalls/firewallpolicyrule.json";
	private static final String FIREWALL_POLICY_UPDATE = "/network/firewalls/firewallpolicyupdate.json";
	
	public void testListFirewallPolicies() throws IOException {
	    respondWith(FIREWALL_POLICIES);
		List<? extends FirewallPolicy> list = osv3().networking().firewalls().firewallpolicy().list();
		assertEquals(1, list.size());
		Preconditions.checkNotNull(list.get(0));
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : FirewallPolicy from List : "+list.get(0));
		assertEquals(list.get(0).getId(), "c69933c1-b472-44f9-8226-30dc4ffd454c");
	}
	
	public void testGetFirewallPolicy() throws IOException {
	    respondWith(FIREWALL_POLICY);
		String id = "c69933c1-b472-44f9-8226-30dc4ffd454c";
		FirewallPolicy firewallPolicy = osv3().networking().firewalls().firewallpolicy().get(id);
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : FirewallPolicy by ID : "+firewallPolicy);
		assertNotNull(firewallPolicy);
		assertEquals(firewallPolicy.getId(), id);
		
		List<String> firewallRules = firewallPolicy.getFirewallRuleIds();
		assertNotNull(firewallRules);
		assertNotNull(firewallRules.get(0));
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : FirewallRule from PolicyList : "+firewallRules.get(0));
		assertEquals(firewallRules.get(0), "8722e0e0-9cc9-4490-9660-8c9a5732fbb0");
	}
		
	public void testCreateFirewallPolicy() throws IOException {
		respondWith(FIREWALL_POLICY);
		FirewallPolicy create = Builders.firewallPolicy()
				  .name("Test-Firewall-Policy").description("Test-Firewall-Policy")
				  .shared(Boolean.TRUE).audited(Boolean.FALSE)
				  .tenantId("45977fa2dbd7482098dd68d0d8970117").build();
		FirewallPolicy result = osv3().networking().firewalls().firewallpolicy().create(create);
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created FirewallPolicy : "+result);
		
		assertEquals(result.getName(), "Test-Firewall-Policy");
		assertEquals(result.getFirewallRuleIds().size(), 1);
	}
	
	public void testUpdateFirewallPolicy() throws IOException {
	    respondWith(FIREWALL_POLICY_UPDATE);
		FirewallPolicyUpdate update = Builders.firewallPolicyUpdate()
				.name("Test-Firewall-Policy-Update").shared(Boolean.FALSE).audited(Boolean.FALSE)
				.firewallRules(Arrays.asList("8722e0e0-9cc9-4490-9660-8c9a5732fbb0"))
				.description("Test-Firewall-Policy-Update").build();
		
		FirewallPolicy result = osv3().networking().firewalls().firewallpolicy().update("c69933c1-b472-44f9-8226-30dc4ffd454c", update);
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated FirewallPolicy : "+result);
		
		assertEquals("Test-Firewall-Policy-Update", result.getDescription());
	}
	
	public void testInsertRuleInFirewallPolicy() throws IOException {
		respondWith(FIREWALL_POLICY_RULE);
		
		FirewallPolicy result = osv3().networking().firewalls().firewallpolicy()
				.insertFirewallRuleInPolicy(
						"c69933c1-b472-44f9-8226-30dc4ffd454c", "7bc34b8c-8d3b-4ada-a9c8-1f4c11c65692",
						RuleInsertStrategyType.AFTER, "a08ef905-0ff6-4784-8374-175fffe7dade");
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Inserted Rule FirewallPolicy : "+result);
		
		assertEquals(result.getId(), "c69933c1-b472-44f9-8226-30dc4ffd454c");
	}
	
	public void testRemoveRuleFromFirewallPolicy() throws IOException {
		respondWith(FIREWALL_POLICY_RULE);
		
		FirewallPolicy result = osv3().networking().firewalls().firewallpolicy()
				.removeFirewallRuleFromPolicy("c69933c1-b472-44f9-8226-30dc4ffd454c", "7bc34b8c-8d3b-4ada-a9c8-1f4c11c65692");
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Rule Removed from FirewallPolicy : "+result);
		
		assertEquals(result.getId(), "c69933c1-b472-44f9-8226-30dc4ffd454c");
	}
	
	public void testDeleteFirewallPolicy() {
	    respondWith(200);
		ActionResponse result = osv3().networking().firewalls().firewallpolicy().delete("c69933c1-b472-44f9-8226-30dc4ffd454c");
		assertTrue(result.isSuccess());
	}

	@Override
	protected Service service() {
		return Service.NETWORK;
	}

}

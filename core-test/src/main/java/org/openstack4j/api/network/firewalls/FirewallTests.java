package org.openstack4j.api.network.firewalls;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.Firewall;
import org.openstack4j.model.network.ext.FirewallUpdate;
import org.openstack4j.openstack.networking.domain.ext.NeutronFirewall.FirewallStatus;
import org.testng.annotations.Test;

import com.google.common.base.Preconditions;

/**
 * Test suite for Firewall As a Service : Firewall {@link Firewall} (FwaaS)
 * 
 * @author Vishvesh Deshmukh
 */
@Test(suiteName="Network/Firewall", enabled = false)
public class FirewallTests extends AbstractTest {
	
	private static final String FIREWALL = "/network/firewalls/firewall.json";
	private static final String FIREWALLS = "/network/firewalls/firewalls.json";
	private static final String FIREWALL_UPDATE = "/network/firewalls/firewallupdate.json";
	
	public void testListFirewalls() throws IOException {
	    respondWith(FIREWALLS);
		List<? extends Firewall> list = osv3().networking().firewalls().firewall().list();
		assertEquals(1, list.size());
		Preconditions.checkNotNull(list.get(0));
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Firewall from List : "+list.get(0));
		assertEquals(list.get(0).getId(), "3b0ef8f4-82c7-44d4-a4fb-6177f9a21977");
	}
	
	public void testGetFirewall() throws IOException {
	    respondWith(FIREWALL);
		String id = "3b0ef8f4-82c7-44d4-a4fb-6177f9a21977";
		Firewall firewall = osv3().networking().firewalls().firewall().get(id);
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Firewall by ID : "+firewall);
		assertNotNull(firewall);
		assertEquals(id, firewall.getId());
		assertEquals(FirewallStatus.ACTIVE, firewall.getStatus());
	}
	
	public void testCreateFirewall() throws IOException {
		respondWith(FIREWALL);
		Firewall create = Builders.firewall().adminStateUp(true)
				  .description("Sample-Description").name("Sample-Firewall")
				  .policy("c69933c1-b472-44f9-8226-30dc4ffd454c").shared(true)
				  .tenantId("45977fa2dbd7482098dd68d0d8970117").build();
		Firewall result = osv3().networking().firewalls().firewall().create(create);
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created Firewall : "+result);
		
		assertEquals("Sample-Firewall", result.getName());
		assertEquals(Boolean.TRUE, result.isAdminStateUp());
	}
	
	public void testUpdateFirewall() throws IOException {
	    respondWith(FIREWALL_UPDATE);
		FirewallUpdate update = Builders.firewallUpdate().adminStateUp(false)
				.description("Test-Firewall-Update").build();
		
		Firewall result = osv3().networking().firewalls().firewall().update("3b0ef8f4-82c7-44d4-a4fb-6177f9a21977", update);
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated Firewall : "+result);
		
		assertEquals("Test-Firewall-Update", result.getDescription());
		assertFalse(result.isAdminStateUp());
	}
	
	public void testDeleteFirewall() {
	    respondWith(200);
		ActionResponse result = osv3().networking().firewalls().firewall().delete("3b0ef8f4-82c7-44d4-a4fb-6177f9a21977");
		assertTrue(result.isSuccess());
	}

	@Override
	protected Service service() {
		return Service.NETWORK;
	}

}

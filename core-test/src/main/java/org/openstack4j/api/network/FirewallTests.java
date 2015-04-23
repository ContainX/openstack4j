package org.openstack4j.api.network;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.network.ext.Firewall;
import org.openstack4j.openstack.networking.domain.ext.NeutronFirewall.FirewallStatus;
import org.testng.annotations.Test;

/**
 * Test suite for Firewall As a Service (FwaaS)
 * 
 * @author Vishvesh Deshmukh
 */
@Test(suiteName="Network/Firewall", enabled = true)
public class FirewallTests extends AbstractTest {
	
	private static final String FIREWALL = "/network/firewall.json";
	private static final String FIREWALLS = "/network/firewalls.json";
	
	public void testListFirewalls() throws IOException {
	    respondWith(FIREWALLS);
		List<? extends Firewall> list = os().networking().firewalls().firewall().list();
		for (Firewall firewall : list) {
			System.out.println(firewall);
		}
		assertEquals(1, list.size());
		assertEquals(list.get(0).getId(), "3b0ef8f4-82c7-44d4-a4fb-6177f9a21977");
	}
	
	public void testGetFirewall() throws IOException {
	    respondWith(FIREWALL);
		String id = "3b0ef8f4-82c7-44d4-a4fb-6177f9a21977";
		Firewall firewall = os().networking().firewalls().firewall().get(id);
		assertNotNull(firewall);
		assertEquals(id, firewall.getId());
		assertEquals(FirewallStatus.ACTIVE, firewall.getStatus());
	}
	
	public void testDeleteFirewall() {
	    respondWith(200);
		ActionResponse result = os().networking().firewalls().firewall().delete("3b0ef8f4-82c7-44d4-a4fb-6177f9a21977");
		assertTrue(result.isSuccess());
	}

	@Override
	protected Service service() {
		return Service.NETWORK;
	}

}

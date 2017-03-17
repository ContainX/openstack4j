package org.openstack4j.api.storage;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.storage.block.ext.Service.State;
import org.openstack4j.model.storage.block.ext.Service.Status;
import org.testng.annotations.Test;

/**
 * Test cases for Block Storage Services function
 * 
 * @author Taemin
 */

@Test(suiteName = "BlockStorageService")
public class ServiceTests extends AbstractTest {

	private static final String JSON_SERVICES = "/storage/ext/services.json";

	public void serviceListingTest() throws Exception {
		respondWith(JSON_SERVICES);

		List<? extends org.openstack4j.model.storage.block.ext.Service> services = osv3().blockStorage().services()
				.list();

		org.openstack4j.model.storage.block.ext.Service s = services.get(0);
		assertEquals("cinder-scheduler", s.getBinary());
		assertEquals("host1", s.getHost());
		assertEquals(Status.ENABLED, s.getStatus());
		assertEquals(State.UP, s.getState());        
	}

	@Override
	protected Service service() {
		return Service.BLOCK_STORAGE;
	}

}
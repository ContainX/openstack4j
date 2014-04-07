package org.openstack4j.api.compute;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.compute.Flavor;
import org.testng.annotations.Test;

/**
 * Tests the Compute -> Flavors API against the mock webserver and spec based json responses
 * 
 * @author Jeremy Unruh
 */
@Test(suiteName="Compute")
public class FlavorTests extends AbstractTest {

	private static final String JSON_FLAVOR = "/compute/flavor.json";
	private static final String JSON_FLAVORS = "/compute/flavors.json";
	
	public void getFlavor() throws Exception {
		respondWith(JSON_FLAVOR);
		Flavor f = os().compute().flavors().get("1");
		
		assertEquals(512, f.getRam());
		assertEquals(1, f.getDisk());
		assertEquals("m1.tiny", f.getName());
	}
	
	
	public void listFlavors() throws Exception {
		respondWith(JSON_FLAVORS);
		List<? extends Flavor> flavors = os().compute().flavors().list();
		assertEquals(5, flavors.size());
	}
	
	@Override
	protected Service service() {
		return Service.COMPUTE;
	}
}

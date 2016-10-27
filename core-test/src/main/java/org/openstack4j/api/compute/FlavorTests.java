package org.openstack4j.api.compute;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.compute.Flavor;
import org.testng.annotations.Test;

/**
 * Tests the Compute -> Flavors API against the mock webserver and spec based json responses
 * 
 * @author Jeremy Unruh
 * @author Qin An
 */
@Test(suiteName="Compute")
public class FlavorTests extends AbstractTest {

	private static final String JSON_FLAVOR = "/compute/flavor.json";
	private static final String JSON_FLAVORS = "/compute/flavors.json";
	private static final String JSON_FLAVORS_DETAILED = "/compute/flavors_detailed.json";
	private static final String JSON_FLAVOR_CREATE = "/compute/flavor_create.json";
	
	public void getFlavor() throws Exception {
		respondWith(JSON_FLAVOR);
		Flavor f = osv3().compute().flavors().get("1");
		assertEquals(1, f.getDisk());
		assertEquals("m1.tiny", f.getName());
		assertEquals(512, f.getRam());
		assertTrue(f.isPublic());
		assertEquals(0, f.getEphemeral());
		assertFalse(f.isDisabled());
		assertEquals(2.0f, f.getRxtxFactor());
		assertEquals(1, f.getVcpus());
	}
	
	
	public void listDetailedAllFlavors() throws Exception {
		respondWith(JSON_FLAVORS_DETAILED);
		List<? extends Flavor> flavors = osv3().compute().flavors().list();
		assertEquals(2, flavors.size());
	}
	
	public void listDetailedParamFlavors() throws Exception {
		respondWith(JSON_FLAVORS_DETAILED);
		Map<String, String> map = new HashMap<String, String>();
		map.put("sort_key", "name");
		map.put("sort_dir", "asc");
		List<? extends Flavor> flavors = osv3().compute().flavors().list(map);
		assertEquals(2, flavors.size());
	}
	
	public void listSimpleAllFlavors() throws Exception {
		respondWith(JSON_FLAVORS);
		List<? extends Flavor> flavors = osv3().compute().flavors().list(false);
		assertEquals(5, flavors.size());
	}
	
	public void listComplexFlavors() throws Exception {
		respondWith(JSON_FLAVORS_DETAILED);
		Map<String, String> map = new HashMap<String, String>();
		map.put("sort_key", "name");
		map.put("sort_dir", "asc");
		List<? extends Flavor> flavors = osv3().compute().flavors().list(true, map);
		assertEquals(2, flavors.size());
	}
	
	public void createFlavor() throws IOException {
		respondWith(JSON_FLAVOR_CREATE);
		Flavor builtF = Builders.flavor().name("safe_to_delete_flavor").vcpus(1).disk(2)
				.isPublic(true).rxtxFactor(2).ephemeral(1).ram(128).id("delete_1").swap(1).build();
		Flavor f = osv3().compute().flavors().create(builtF);
		assertEquals(1, f.getVcpus());
		assertEquals(2, f.getDisk());
		assertEquals("m1.tiny", f.getName());
		assertTrue(f.isPublic());
		assertEquals(2.0f, f.getRxtxFactor());
		assertEquals(1, f.getEphemeral());
		assertEquals(128, f.getRam());
		assertEquals("1", f.getId());
		assertEquals(1, f.getSwap());
	}
	
	@Override
	protected Service service() {
		return Service.COMPUTE;
	}
}

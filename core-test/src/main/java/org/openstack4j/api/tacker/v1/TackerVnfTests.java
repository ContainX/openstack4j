package org.openstack4j.api.tacker.v1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.tacker.Vnf;
import org.openstack4j.model.tacker.VnfUpdate;
import org.openstack4j.openstack.tacker.domain.TackerVnfStatus;
import org.openstack4j.openstack.tacker.domain.VnfUpdateAttributes;
import org.testng.annotations.Test;

import com.google.common.base.Preconditions;

/**
 *
 * @author Vishvesh Deshmukh
 * @date Sep 6, 2016
 */
@Test(suiteName = "tacker/vnf")
public class TackerVnfTests extends AbstractTest {

	private static final String TACKER_VNFS = "/tacker/v1/vnfs.json";
	private static final String TACKER_VNF_GET = "/tacker/v1/vnf-get.json";
	private static final String TACKER_VNF_CREATE = "/tacker/v1/vnf-create.json";
	private static final String TACKER_VNF_UPDATE = "/tacker/v1/vnf-update.json";

	@Override
	protected Service service() {
		return Service.TACKER;
	}

	@Test
	public void testListVnfs() throws Exception {
		respondWith(TACKER_VNFS);
		List<? extends Vnf> vnfs = osv3().tacker().vnf().list();
		assertEquals(1, vnfs.size());
		Preconditions.checkNotNull(vnfs.get(0));
		Logger.getLogger(getClass().getName())
				.info(getClass().getName() + " : Tacker VNF from List : " + vnfs.get(0));
		assertEquals(vnfs.get(0).getName(), "test-vnf");
	}
	
	public void testGetVnf() throws IOException {
	    respondWith(TACKER_VNF_GET);
		String id = "afbbf7f4-59c2-45ed-b158-8c4e2e1d9104";
		Vnf vnf = osv3().tacker().vnf().get(id);
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Tacker VNF by ID : "+vnf);
		assertNotNull(vnf);
		assertEquals(id, vnf.getId());
		assertEquals("test-vnf", vnf.getName());
	}
	
	public void testCreateVnf() throws IOException {
		respondWith(TACKER_VNF_CREATE);
		Vnf vnf = Builders.tacker().vnf()
				.name("test-vnf")
				.description("test-vnf-description")
				.vnfdId("1363e776-6c79-4e53-8074-4e32e49f156a")
				.build();
		
		Vnf newVnf = osv3().tacker().vnf().create(vnf);
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created Tacker Vnf : "+newVnf);
		
		assertEquals("test-vnf", newVnf.getName());
	}
	
	public void testUpdateVnf() throws Exception {
		respondWith(TACKER_VNF_UPDATE);
		String vnfId = "4043f4bd-a728-4ee3-91d6-a11a6bb89030";
		String vnfUpdateConfig = "vdus:\n  vdu1:\n    id: vdu1\n    vm_image: cirros-0.3.4-x86_64-uec\n    instance_type: m1.tiny\n\n    network_interfaces:\n      management:\n        network: net_mgmt\n        management: true\n      pkt_in:\n        network: net0\n      pkt_out:\n        network: net1\n\n    placement_policy:\n      availability_zone: nova\n\n    auto-scaling: noop\n\n";
		VnfUpdateAttributes vnfUpdateAttributes = VnfUpdateAttributes.create().config(vnfUpdateConfig);
		VnfUpdate vnfUpdate = Builders.tacker().vnfUpdate().attributes(vnfUpdateAttributes).build();
		Vnf updatedVnf = osv3().tacker().vnf().update(vnfId, vnfUpdate);
		
		assertEquals("test-update-vnf-description", updatedVnf.getDescription());
		assertEquals(TackerVnfStatus.PENDING_UPDATE, updatedVnf.getStatus());
	}
	
	public void testDeleteVnf() throws IOException {
		respondWith(200);
		ActionResponse result = osv3().tacker().vnf().delete("afbbf7f4-59c2-45ed-b158-8c4e2e1d9104");
		assertTrue(result.isSuccess());
	}
}
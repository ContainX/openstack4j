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
import org.openstack4j.model.tacker.Vnfd;
import org.openstack4j.openstack.tacker.domain.VnfdAttributes;
import org.openstack4j.openstack.tacker.domain.VnfdServiceTypes;
import org.testng.annotations.Test;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 *
 * @author Vishvesh Deshmukh
 * @date Sep 6, 2016
 */
@Test(suiteName = "tacker/vnfd")
public class TackerVnfdTests extends AbstractTest {

	private static final String TACKER_VNFD = "/tacker/v1/vnfd.json";
	private static final String TACKER_VNFDS = "/tacker/v1/vnfds.json";

	@Override
	protected Service service() {
		return Service.TACKER;
	}

	@Test
	public void testListVnfds() throws Exception {
		respondWith(TACKER_VNFDS);
		List<? extends Vnfd> vnfds = osv3().tacker().vnfd().list();
		assertEquals(1, vnfds.size());
		Preconditions.checkNotNull(vnfds.get(0));
		Logger.getLogger(getClass().getName())
				.info(getClass().getName() + " : Tacker VNFD from List : " + vnfds.get(0));
		assertEquals(vnfds.get(0).getName(), "test-vnfd");
	}
	
	public void testGetVnfd() throws IOException {
	    respondWith(TACKER_VNFD);
		String id = "1363e776-6c79-4e53-8074-4e32e49f156a";
		Vnfd vnfd = osv3().tacker().vnfd().get(id);
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Tacker VNFD by ID : "+vnfd);
		assertNotNull(vnfd);
		assertEquals(id, vnfd.getId());
		assertEquals("test-vnfd", vnfd.getName());
	}
	
	public void testCreateVnfd() throws IOException {
		respondWith(TACKER_VNFD);
		String vnfdTemplate = "template_name: sample-vnfd\ndescription: admin-example\n\nservice_properties:\n  Id: sample-vnfd\n  vendor: tacker\n  version: 1\n\nvdus:\n  vdu1:\n    id: vdu1\n    vm_image: cirros-0.3.4-x86_64-uec\n    instance_type: m1.tiny\n\n    network_interfaces:\n      management:\n        network: net_mgmt\n        management: true\n      pkt_in:\n        network: net0\n      pkt_out:\n        network: net1\n\n    placement_policy:\n      availability_zone: nova\n\n    auto-scaling: noop\n\n    config:\n      param0: key0\n      param1: key1\n";
		
		VnfdAttributes attributes = VnfdAttributes.create().vnfd(vnfdTemplate);
		
		VnfdServiceTypes serviceTypes = VnfdServiceTypes.create().serviceType("vnfd");
		
		List<VnfdServiceTypes> serviceTypesList = Lists.newArrayList(serviceTypes);
		
		Vnfd vnfd = Builders.tacker().vnfd()
		.name("test-vnfd")
		.description("test-vnfd-description")
		.infrastructureDriver("heat")
		.managementDriver("noop")
		.attributes(attributes)
		.serviceTypes(serviceTypesList)
		.build();
		
		Vnfd newVnfd = osv3().tacker().vnfd().create(vnfd);
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created Tacker Vnfd : "+newVnfd);
		
		assertEquals("test-vnfd", newVnfd.getName());
	}
	
	public void testDeleteVnfd() throws IOException {
		respondWith(200);
		ActionResponse result = osv3().tacker().vnfd().delete("1363e776-6c79-4e53-8074-4e32e49f156a");
		assertTrue(result.isSuccess());
	}
}
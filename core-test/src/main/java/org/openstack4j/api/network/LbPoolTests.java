package org.openstack4j.api.network;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.network.ext.HealthMonitor;
import org.openstack4j.model.network.ext.HealthMonitorAssociate;
import org.openstack4j.model.network.ext.LbPool;
import org.openstack4j.model.network.ext.LbPoolUpdate;
import org.openstack4j.openstack.networking.domain.ext.LbMethod;
import org.openstack4j.openstack.networking.domain.ext.Protocol;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

/**
 * 
 * @author liujunpeng
 *
 */
@Test(suiteName="Network/lbpool")
public class LbPoolTests extends AbstractTest{
	public void testListPool() {
		List<? extends LbPool> list = os().networking().lbPool().list();
		System.out.println("test lb pool List" + list);
		assertEquals(1, list.size());
	}

	public void testListPoolFilter() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "pool");
		List<? extends LbPool> list = os().networking().lbPool().list(map);
		System.out.println("test lb pool List filter" + list);
		assertEquals(1, list.size());
	}

	public void testGetPool() {
		String id = "f6f1a5bb-2c5d-4c41-941c-024eb39414a6";
		LbPool pool = os().networking().lbPool().get(id);
		System.out.println("test get a pool" + pool);
		assertEquals(id, pool.getId());
	}

	public void testCreatePool() {
		String name = "create";
		String protocol = Protocol.HTTP.toString();
		LbPool create = Builders.lbPool().adminStateUp(true)
				.description("pool").lbMethod(LbMethod.ROUND_ROBIN.toString())
				.name(name).provider("haproxy")
				.subnetId("7d1dab60-cf8a-4f75-af5c-44aab98b0c42")
				.tenantId("d7fd03242ffa4933863bc528ed884fb6")
				.protocol(protocol).build();
		LbPool result = os().networking().lbPool().create(create);
		assertEquals(name, result.getName());
		assertEquals(LbMethod.ROUND_ROBIN.toString(), result.getLbMethod());
		assertEquals(protocol, result.getProtocol());
	}

	public void testUpdatePool() {
		String poolId = "db083bf7-c455-4758-b1ad-203cf441a73a";
		String name = "update";
		LbPoolUpdate update = Builders.lbPoolUpdate().adminStateUp(false)
				.description("update")
				.lbMethod(LbMethod.LEAST_CONNECTIONS.toString()).name(name)
				.build();
		LbPool result = os().networking().lbPool().update(poolId, update);
		assertEquals(name, result.getName());
		assertEquals(LbMethod.LEAST_CONNECTIONS.toString(),
				result.getLbMethod());
		assertFalse(result.isAdminStateUp());
	}

	public void testAssociateHealthMonitor() {
		String poolId = "db083bf7-c455-4758-b1ad-203cf441a73a";
		String healthId = "8767284a-b542-4db6-8393-0a404a959c1d";
		HealthMonitorAssociate associate = Builders
				.lbPoolAssociateHealthMonitor().id(healthId).build();
		HealthMonitor result = os().networking().lbPool()
				.associateHealthMonitor(poolId, associate);
	}

	public void testDisAssociateHealthMonitor() {
		String poolId = "db083bf7-c455-4758-b1ad-203cf441a73a";
		String healthId = "8767284a-b542-4db6-8393-0a404a959c1d";
		ActionResponse result = os().networking().lbPool()
				.disAssociateHealthMonitor(poolId, healthId);
		assertTrue(result.isSuccess());
	}

	public void testDeletePool() {
		String id = "02ae87ec-5502-469e-8c41-f2c57d185054";
		ActionResponse result = os().networking().lbPool().delete(id);
		System.out.println("delete a pool" + result);
		assertTrue(result.isSuccess());
	}
	@Override
	protected Service service() {
		return Service.NETWORK;
	}

}

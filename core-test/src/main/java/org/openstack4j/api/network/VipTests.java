package org.openstack4j.api.network;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.Protocol;
import org.openstack4j.model.network.ext.SessionPersistenceType;
import org.openstack4j.model.network.ext.Vip;
import org.openstack4j.model.network.ext.VipUpdate;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * 
 * @author liujunpeng
 *
 */
@Test(suiteName="Network/vip", enabled=false)
public class VipTests extends AbstractTest{
	public void testListVip(){
		List<? extends Vip> list = osv3().networking().loadbalancers().vip().list();
		System.out.println("test lb vip List"+list);
		assertEquals(1, list.size());
	}
	public void testListVipFilter(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "vip");
		List<? extends Vip> list = osv3().networking().loadbalancers().vip().list(map);
		System.out.println("test lb vip List filter"+list);
		assertEquals(1, list.size());
	}
	public void testGetVip(){
		String id = "dfc5c198-dceb-4f99-8ed7-5ebfdf46946d";
		Vip vip = osv3().networking().loadbalancers().vip().get(id);
		System.out.println("test get a vip"+vip);
		assertEquals(id, vip.getId());

	}
	
	public void testCreateVip(){
		String address = "100.2.12.48";
		String name = "createVip";
		String poolId = "db083bf7-c455-4758-b1ad-203cf441a73a";
		String subnetId = "7d1dab60-cf8a-4f75-af5c-44aab98b0c42";
		String tenantId = "d7fd03242ffa4933863bc528ed884fb6";
		Integer port = 80;
		Vip create = Builders.vip().address(address).adminStateUp(true)
				.connectionLimit(100)
				.description("vip")
				.name(name)
				.poolId(poolId)
				.protocol(Protocol.HTTP)
				.protocolPort(port)
				.sessionPersistence(Builders
						.sessionPersistence()
						.cookieName("cookie")
						.type(SessionPersistenceType.APP_COOKIE)
						.build())
				.subnetId(subnetId)
				.tenantId(tenantId)
				.build();
		Vip result = osv3().networking().loadbalancers().vip().create(create);
		System.out.println(result);
		assertEquals(address, result.getAddress());
		assertEquals(name, result.getName());
		assertEquals(Protocol.HTTP, result.getProtocol());
		assertEquals(port, result.getProtocolPort());
	}
	
	public void testUpdateVip(){
		String vipId = "cb1d7958-232d-4daa-a5f5-16ba91a6362b";
		String name = "updateVip";
		String poolId = "db083bf7-c455-4758-b1ad-203cf441a73a";
		Integer connectionLimit = 101;
		VipUpdate update = Builders.vipUpdate().adminStateUp(true)
				.connectionLimit(connectionLimit)
				.description("vip")
				.name(name)
				.poolId(poolId)
				.sessionPersistence(Builders
						.sessionPersistence()
						.type(SessionPersistenceType.SOURCE_IP)
						.build())
				.description("description update")
				.build();
		Vip result = osv3().networking().loadbalancers().vip().update(vipId, update);
		System.out.println(result);
		assertEquals(poolId, result.getPoolId());
		assertEquals(connectionLimit, result.getConnectionLimit());
		assertEquals(name, result.getName());
		assertEquals(SessionPersistenceType.SOURCE_IP, result
				.getSessionPersistence().getType());
	
	}
	
	public void testDeleteVip(){
		String id = "50cbd265-fe4f-4c9c-b25c-bb6c773d0366";
		ActionResponse result = osv3().networking().loadbalancers().vip().delete(id);
		assertTrue(result.isSuccess());
		
	}
	@Override
	protected Service service() {
		return Service.NETWORK;
	}

}

package org.openstack4j.api.network;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.HealthMonitor;
import org.openstack4j.model.network.ext.HealthMonitorType;
import org.openstack4j.model.network.ext.HealthMonitorUpdate;
import org.openstack4j.openstack.networking.domain.ext.HttpMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
/**
 * 
 * @author liujunpeng
 *
 */
@Test(suiteName="Network/healthMonitor", enabled = false)
public class HealthMonitorTests extends AbstractTest{
	

	public void testListHealtMonitor(){
		List<? extends HealthMonitor> list = osv3().networking().loadbalancers().healthMonitor().list();
		System.out.println("test lb HealtMonitor List"+list);
		assertEquals(4, list.size());
	}
	public void testListHealtMonitorFilter(){
		Map <String, String> map = new HashMap<String, String>();
		map.put("type", "ping");
		List<? extends HealthMonitor> list = osv3().networking().loadbalancers().healthMonitor().list(map);
		System.out.println("test lb HealtMonitor List filter"+list);
		assertEquals(1, list.size());
	}
	public void testGetHealtMonitor(){
		String id = "48de592b-aab4-4727-9c45-7c7587fde20d";
		HealthMonitor healthMonitor = osv3().networking().loadbalancers().healthMonitor().get(id);
		System.out.println("test get a HealtMonitor"+healthMonitor);
		assertEquals(id, healthMonitor.getId());

	}
	
	/**
	 * ping
	 */
	public void testCreatePingMonitor(){
		Integer delay = 10;
		Integer maxRetries = 3;
		Integer timeout = 3;
		HealthMonitorType type = HealthMonitorType.PING;
		HealthMonitor monitor = Builders.healthMonitor()
				.delay(delay)
				.maxRetries(maxRetries)
				.timeout(timeout)
				.type(type)
				.build();
		HealthMonitor result = osv3().networking().loadbalancers().healthMonitor().create(monitor);
		assertEquals(delay, result.getDelay());
		assertEquals(maxRetries, result.getMaxRetries());
		assertEquals(timeout, result.getTimeout());
		assertEquals(type, result.getType());
		
	}
	
	public void testUpdatePingMonitor(){
		Integer delay = 5;
		Integer maxRetries = 1;
		Integer timeout = 1;
		String monitorId = "998e8127-5905-45fe-b9ee-6e691611dda8";
		HealthMonitorUpdate update = Builders.healthMonitorUpdate()
				.delay(delay)
				.maxRetries(maxRetries)
				.timeout(timeout)
				.build();
		HealthMonitor result = osv3().networking().loadbalancers().healthMonitor().update(monitorId,update);
		assertEquals(delay, result.getDelay());
		assertEquals(maxRetries, result.getMaxRetries());
		assertEquals(timeout, result.getTimeout());
	}
	/**
	 * tcp
	 */
	public void testCreateTcpMonitor(){
		Integer delay = 10;
		Integer maxRetries = 3;
		Integer timeout = 3;
		HealthMonitorType type = HealthMonitorType.TCP;
		HealthMonitor monitor = Builders.healthMonitor()
				.delay(delay)
				.maxRetries(maxRetries)
				.timeout(timeout)
				.type(type)
				.build();
		HealthMonitor result = osv3().networking().loadbalancers().healthMonitor().create(monitor);
		assertEquals(delay, result.getDelay());
		assertEquals(maxRetries, result.getMaxRetries());
		assertEquals(timeout, result.getTimeout());
		assertEquals(type, result.getType());
	}
	public void testUpdateTcpMonitor(){
		Integer delay = 5;
		Integer maxRetries = 1;
		Integer timeout = 1;
		String monitorId = "8767284a-b542-4db6-8393-0a404a959c1d";
		HealthMonitorUpdate update = Builders.healthMonitorUpdate()
				.delay(delay)
				.maxRetries(maxRetries)
				.timeout(timeout)
				.build();
		HealthMonitor result = osv3().networking().loadbalancers().healthMonitor().update(monitorId,update);
		assertEquals(delay, result.getDelay());
		assertEquals(maxRetries, result.getMaxRetries());
		assertEquals(timeout, result.getTimeout());
	}
	/**
	 * HTTP
	 */
	public void testCreateHttpMonitor(){
		Integer delay = 10;
		Integer maxRetries = 3;
		Integer timeout = 3;
		String urlPath = "/";
		String expectedCodes = "200";
		HealthMonitorType type = HealthMonitorType.HTTP;
		String httpMethod = HttpMethod.GET.toString();
		HealthMonitor monitor = Builders.healthMonitor()
				.delay(delay)
				.maxRetries(maxRetries)
				.timeout(timeout)
				.type(type)
				.urlPath(urlPath)
				.expectedCodes(expectedCodes)
				.httpMethod(httpMethod)
				.build();
		HealthMonitor result = osv3().networking().loadbalancers().healthMonitor().create(monitor);
		assertEquals(delay, result.getDelay());
		assertEquals(maxRetries, result.getMaxRetries());
		assertEquals(timeout, result.getTimeout());
		assertEquals(type, result.getType());
		assertEquals(expectedCodes, result.getExpectedCodes());
	}
	
	public void testUpdateHttpMonitor(){
		Integer delay = 6;
		Integer maxRetries = 2;
		Integer timeout = 2;
		String urlPath = "/";
		String expectedCodes = "210";
		String httpMethod = HttpMethod.GET.toString();
		String monitorId = "764bd223-efaa-428d-892c-f32bcf2b6a41";
		HealthMonitorUpdate update = Builders.healthMonitorUpdate()
				.delay(delay)
				.maxRetries(maxRetries)
				.timeout(timeout)
				.urlPath(urlPath)
				.expectedCodes(expectedCodes)
				.httpMethod(httpMethod)
				.build();
		HealthMonitor result = osv3().networking().loadbalancers().healthMonitor().update(monitorId,update);
		assertEquals(delay, result.getDelay());
		assertEquals(maxRetries, result.getMaxRetries());
		assertEquals(timeout, result.getTimeout());
		assertEquals(expectedCodes, result.getExpectedCodes());
	}

	/**
	 * HTTPS
	 */
	public void testCreateHttpsMonitor(){
		Integer delay = 10;
		Integer maxRetries = 3;
		Integer timeout = 3;
		String urlPath = "/";
		String expectedCodes = "200";
		HealthMonitorType type = HealthMonitorType.HTTPS;
		String httpMethod = HttpMethod.GET.toString();
		HealthMonitor monitor = Builders.healthMonitor()
				.delay(delay)
				.maxRetries(maxRetries)
				.timeout(timeout)
				.type(type)
				.urlPath(urlPath)
				.expectedCodes(expectedCodes)
				.httpMethod(httpMethod)
				.build();
		HealthMonitor result = osv3().networking().loadbalancers().healthMonitor().create(monitor);
		assertEquals(delay, result.getDelay());
		assertEquals(maxRetries, result.getMaxRetries());
		assertEquals(timeout, result.getTimeout());
		assertEquals(type, result.getType());
		assertEquals(expectedCodes, result.getExpectedCodes());
	}
	
	public void testUpdateHttpsMonitor(){
		Integer delay = 6;
		Integer maxRetries = 2;
		Integer timeout = 2;
		String urlPath = "/";
		String expectedCodes = "210";
		String httpMethod = HttpMethod.GET.toString();
		String monitorId = "71dad2a4-876a-46f3-99d4-f5226463d864";
		HealthMonitorUpdate update = Builders.healthMonitorUpdate()
				.delay(delay)
				.maxRetries(maxRetries)
				.timeout(timeout)
				.urlPath(urlPath)
				.expectedCodes(expectedCodes)
				.httpMethod(httpMethod)
				.build();
		HealthMonitor result = osv3().networking().loadbalancers().healthMonitor().update(monitorId,update);
		assertEquals(delay, result.getDelay());
		assertEquals(maxRetries, result.getMaxRetries());
		assertEquals(timeout, result.getTimeout());
		assertEquals(expectedCodes, result.getExpectedCodes());
	}
	public void testDeleteHealtMonitor(){
		String id = "48de592b-aab4-4727-9c45-7c7587fde20d";
		ActionResponse result = osv3().networking().loadbalancers().healthMonitor().delete(id);
		assertTrue(result.isSuccess());

	}
	@Override
	protected Service service() {
		return Service.NETWORK;
	}

}

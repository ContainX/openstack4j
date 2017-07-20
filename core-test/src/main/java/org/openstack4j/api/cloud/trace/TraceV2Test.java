/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package org.openstack4j.api.cloud.trace;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.openstack.cloud.trace.constants.TraceStatus;
import org.openstack4j.openstack.cloud.trace.constants.TraceType;
import org.openstack4j.openstack.cloud.trace.v2.domain.Trace;
import org.openstack4j.openstack.cloud.trace.v2.options.TraceListOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "CTSV2/Tracce", enabled = true)
public class TraceV2Test extends AbstractTest {

	@Test
	public void testListTracker() throws IOException, InterruptedException {
		respondWith("/cloud-trace/list_trace_v2_response.json");

		Date now = new Date(1500113647674L);
		TraceListOptions options = TraceListOptions.create().id("trace-id").user("zhangdong").serviceType("CTS")
				.resourceId("resource-id").resourceType("resource-type").resourceName("resource-name")
				.status(TraceStatus.Incident).from(now).to(now).marker("some-trace-id").limit(5);
		List<Trace> list = osv3().cloudTraceV2().traces().list("system", options);

		RecordedRequest request = server.takeRequest();

		Assert.assertEquals(request.getPath(),
				"/v2.0/project-id/system/trace?to=1500113647674&limit=5&resource_id=resource-id"
						+ "&resource_name=resource-name&trace_id=trace-id&service_type=CTS"
						+ "&resource_type=resource-type&next=some-trace-id&trace_status=incident"
						+ "&from=1500113647674&user=zhangdong");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertTrue(list.size() == 2);

		Trace trace = list.get(0);
		
		Assert.assertEquals(trace.getTime().getTime(), 1472148708232L);
		Assert.assertEquals(trace.getCode().intValue(), 200);
		Assert.assertEquals(trace.getServiceType(), "VPC");
		Assert.assertEquals(trace.getResourceType(), "eip");
		Assert.assertEquals(trace.getResourceName(), "192.144.163.1");
		Assert.assertEquals(trace.getResourceId(), "d502809d-0d1d-41ce-9690-784282142ccc");
		Assert.assertEquals(trace.getName(), "deleteEip");
		Assert.assertEquals(trace.getStatus(), TraceStatus.Warning);
		Assert.assertEquals(trace.getType(), TraceType.ConsoleAction);
		Assert.assertEquals(trace.getApiVersion(), "2.0");
		Assert.assertEquals(trace.getRecordTime().getTime(), 1481066128032L);
		Assert.assertEquals(trace.getId(), "e001ccb9-bc09-11e6-b00b-4b2a61338db6");
		
		HashMap<String, Object> response = trace.getResponse();
		Assert.assertEquals(response.get("code"), "VPC.0514" );
		Assert.assertEquals(response.get("message"), "Update port fail.");
		HashMap<String, Object> user = trace.getUser();
		Assert.assertEquals(user.get("name"), "xxx" );
		Assert.assertEquals(user.get("id"), "a2e899190fcd444084a68fc0ac2sc1e9");
	}

	@Override
	protected Service service() {
		return Service.CLOUD_TRACE;
	}

}

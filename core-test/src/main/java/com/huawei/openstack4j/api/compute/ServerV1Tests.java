/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package com.huawei.openstack4j.api.compute;

import static org.testng.Assert.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.compute.RebootType;
import com.huawei.openstack4j.model.compute.StopType;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "ServersV1")
public class ServerV1Tests extends AbstractTest {

	@Override
	protected Service service() {
		return Service.COMPUTE;
	}

	@Test
	public void deleteServerTest() throws Exception {
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");

		List<String> serverIds = Lists.newArrayList("server-id-1", "server-id-2");
		String jobId = osv3().compute().serversV1().delete(serverIds, false, false);

		RecordedRequest request = server.takeRequest();
		assertEquals(request.getPath(), "/v1/project-id/cloudservers/delete");
		assertEquals(request.getMethod(), "POST");
		
		assertEquals(jobId, "this-is-a-job-id");

		String requestBody = request.getBody().readUtf8();
		String expectBody = this.getResource("/compute/v1/servers_delete_request.json");
		Assert.assertEquals(requestBody, expectBody);
	}
	
	@Test
	public void stopServerTest() throws Exception {
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");

		List<String> serverIds = Lists.newArrayList("server-id-1", "server-id-2");
		String jobId = osv3().compute().serversV1().stop(serverIds, StopType.SOFT);

		RecordedRequest request = server.takeRequest();
		assertEquals(request.getPath(), "/v1/project-id/cloudservers/action");
		assertEquals(request.getMethod(), "POST");
		
		assertEquals(jobId, "this-is-a-job-id");

		String requestBody = request.getBody().readUtf8();
		String expectBody = this.getResource("/compute/v1/servers_stop_request.json");
		Assert.assertEquals(requestBody, expectBody);
	}
	
	@Test
	public void batchRebootServerTest() throws Exception {
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");

		List<String> serverIds = Lists.newArrayList("server-id-1", "server-id-2");
		String jobId = osv3().compute().serversV1().reboot(serverIds, RebootType.HARD);

		RecordedRequest request = server.takeRequest();
		assertEquals(request.getPath(), "/v1/project-id/cloudservers/action");
		assertEquals(request.getMethod(), "POST");
		assertEquals(jobId, "this-is-a-job-id");

		String requestBody = request.getBody().readUtf8();
		String expectBody = this.getResource("/compute/v1/servers_reboot_request.json");
		Assert.assertEquals(requestBody, expectBody);
	}
	
}

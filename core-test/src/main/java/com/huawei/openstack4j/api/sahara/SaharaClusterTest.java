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
package com.huawei.openstack4j.api.sahara;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.core.transport.ObjectMapperSingleton;
import com.huawei.openstack4j.model.common.ActionResponse;

import com.google.common.collect.Lists;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Sahara/Cluster", enabled = true)
public class SaharaClusterTest extends AbstractTest {
	
	@Test
	public void testDeleteCluster() throws IOException, InterruptedException {
		respondWith(200);
		ActionResponse result = osv3().sahara().clusters().delete("cluster-id");
		Assert.assertTrue(result.isSuccess());
		
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/clusters/cluster-id");
		Assert.assertEquals(request.getMethod(), "DELETE");
	}

	@Test
	public void testExpandCluster() throws IOException, InterruptedException {
		respondWith(200, "{ \"result\": \"succeeded\" }");
		ActionResponse result = osv3().sahara().clusters().expand("cluster-id", 3);
		Assert.assertTrue(result.isSuccess());
		
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/cluster_infos/cluster-id");
		Assert.assertEquals(request.getMethod(), "PUT");
		
		String requestBody = request.getBody().readUtf8();

		JsonNode response = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertEquals("", response.get("service_id").asText());
		Assert.assertEquals("", response.get("plan_id").asText());
		Assert.assertEquals("", response.get("previous_values").get("plan_id").asText());
		
		JsonNode parameters = response.get("parameters");
		Assert.assertEquals("", parameters.get("order_id").asText());
		Assert.assertEquals("node_orderadd", parameters.get("node_id").asText());
		Assert.assertEquals("scale_out", parameters.get("scale_type").asText());
		Assert.assertEquals("3", parameters.get("instances").asText());
		Assert.assertTrue(parameters.get("include_instances").isArray());
		Assert.assertTrue(parameters.get("include_instances").size() == 0);
		Assert.assertTrue(parameters.get("exclude_instances").isArray());
		Assert.assertTrue(parameters.get("exclude_instances").size() == 0);
	}
	
	@Test
	public void testReduceCluster() throws IOException, InterruptedException {
		respondWith(200, "{ \"result\": \"succeeded\" }");
		List<String> includes = Lists.newArrayList("instance-id-1", "instance-id-2");
		List<String> excludes = Lists.newArrayList("instance-id-3", "instance-id-4");
		ActionResponse result = osv3().sahara().clusters().reduce("cluster-id", 3, includes, excludes);
		Assert.assertTrue(result.isSuccess());
		
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/cluster_infos/cluster-id");
		Assert.assertEquals(request.getMethod(), "PUT");
		
		String requestBody = request.getBody().readUtf8();

		JsonNode response = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertEquals("", response.get("service_id").asText());
		Assert.assertEquals("", response.get("plan_id").asText());
		Assert.assertEquals("", response.get("previous_values").get("plan_id").asText());
		
		JsonNode parameters = response.get("parameters");
		Assert.assertEquals("", parameters.get("order_id").asText());
		Assert.assertEquals("node_orderadd", parameters.get("node_id").asText());
		Assert.assertEquals("scale_in", parameters.get("scale_type").asText());
		Assert.assertEquals("3", parameters.get("instances").asText());
		Assert.assertTrue(parameters.get("include_instances").isArray());
		Assert.assertEquals("instance-id-1", parameters.get("include_instances").get(0).asText());
		Assert.assertEquals("instance-id-2", parameters.get("include_instances").get(1).asText());
		Assert.assertTrue(parameters.get("exclude_instances").isArray());
		Assert.assertEquals("instance-id-3", parameters.get("exclude_instances").get(0).asText());
		Assert.assertEquals("instance-id-4", parameters.get("exclude_instances").get(1).asText());
	}

	@Override
	protected Service service() {
		return Service.SAHARA;
	}

}

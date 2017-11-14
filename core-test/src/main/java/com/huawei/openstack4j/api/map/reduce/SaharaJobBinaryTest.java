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
package com.huawei.openstack4j.api.map.reduce;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.core.transport.ObjectMapperSingleton;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.map.reduce.JobBinary;
import com.huawei.openstack4j.model.map.reduce.options.JobBinaryListOptions;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobBinary;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Sahara/JobBinary", enabled = true)
public class SaharaJobBinaryTest extends AbstractTest {

	@Test
	public void testGetJobBinary() throws IOException, InterruptedException {
		respondWith("/sahara/get_job_binary_response.json");

		JobBinary jobBinary = osv3().mrs().jobBinaries().get("job-binary-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/job-binaries/job-binary-id");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(jobBinary.getId(), "092b628b-26a3-4571-9ba4-f8d000df8877");
		Assert.assertEquals(jobBinary.isPublic(), Boolean.FALSE);
		Assert.assertEquals(jobBinary.isProtected(), Boolean.FALSE);
		Assert.assertEquals(jobBinary.getTenantId(), "46abc9f8044f40428f83a06cba0d5ddb");
		Assert.assertEquals(jobBinary.getCreatedAt().getTime(), 1488246418000L);
		Assert.assertEquals(jobBinary.getUpdatedAt(), null);
		Assert.assertEquals(jobBinary.getName(), "my-job-binary");
		Assert.assertEquals(jobBinary.getDescription(), "this is the job binary for map reduce job");
		Assert.assertEquals(jobBinary.getURL(), "swift://simple/mapreduce/input");
	}

	@Test
	public void testUpdateJobBinary() throws IOException, InterruptedException {
		respondWith("/sahara/update_job_binary_response.json");

		JobBinary update = MapReduceJobBinary.builder().id("job-binary-id").name("SDK-unittests")
				.url("/sdk/unittests/input1").build();
		JobBinary jobBinary = osv3().mrs().jobBinaries().update(update);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/job-binaries/job-binary-id");
		Assert.assertEquals(request.getMethod(), "PUT");

		String requestBody = request.getBody().readUtf8();
		JsonNode requestNode = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertEquals("SDK-unittests", requestNode.get("name").asText());
		Assert.assertEquals("/sdk/unittests/input1", requestNode.get("url").asText());

		Assert.assertEquals(jobBinary.getId(), "092b628b-26a3-4571-9ba4-f8d000df8877");
		Assert.assertEquals(jobBinary.isPublic(), Boolean.FALSE);
		Assert.assertEquals(jobBinary.isProtected(), Boolean.FALSE);
		Assert.assertEquals(jobBinary.getTenantId(), "46abc9f8044f40428f83a06cba0d5ddb");
		Assert.assertEquals(jobBinary.getCreatedAt().getTime(), 1488246418000L);
		Assert.assertEquals(jobBinary.getUpdatedAt(), null);
		Assert.assertEquals(jobBinary.getName(), "my-job-binary");
		Assert.assertEquals(jobBinary.getDescription(), "this is the job binary for map reduce job");
		Assert.assertEquals(jobBinary.getURL(), "swift://simple/mapreduce/input");
	}

	@Test
	public void testListJobBinary() throws IOException, InterruptedException {
		respondWith("/sahara/list_job_binary_response.json");

		JobBinaryListOptions options = JobBinaryListOptions.create().asc("created_at").limit(10).marker("last-binary-id");
		List<? extends JobBinary> jobBinaries = osv3().mrs().jobBinaries().list(options);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(),
				"/v1.1/project-id/job-binaries?limit=10&sort_by=created_at&marker=last-binary-id");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(jobBinaries.size(), 2);
		JobBinary jobBinary = jobBinaries.get(0);
		Assert.assertEquals(jobBinary.getId(), "7b8298be-f6ba-4765-935b-59a3d6c0037b");
		Assert.assertEquals(jobBinary.isPublic(), Boolean.TRUE);
		Assert.assertEquals(jobBinary.isProtected(), null);
		Assert.assertEquals(jobBinary.getTenantId(), "3f99e3319a8943ceb15c584f3325d064");
		Assert.assertEquals(jobBinary.getCreatedAt().getTime(), 1484622271000L);
		Assert.assertEquals(jobBinary.getUpdatedAt().getTime(), 1487300671000L);
		Assert.assertEquals(jobBinary.getName(), "jarexample1.5jar");
		Assert.assertEquals(jobBinary.getDescription(), "This is a job binary15");
		Assert.assertEquals(jobBinary.getURL(), "swift://container/jar-example.jar");
	}

	@Test
	public void testDeleteJobBinary() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse delete = osv3().mrs().jobBinaries().delete("job-binary-id");
		Assert.assertTrue(delete.isSuccess());

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/job-binaries/job-binary-id");
		Assert.assertEquals(request.getMethod(), "DELETE");
	}

	@Test
	public void testCreateJobBinary() throws IOException, InterruptedException {
		respondWith("/sahara/create_job_binary_response.json");

		JobBinary create = MapReduceJobBinary.builder().name("SDK-unittests").url("/sdk/unittests/input1")
				.description("SDK-unittests").isProtect(false).isPublic(false).build();

		JobBinary jobBinary = osv3().mrs().jobBinaries().create(create);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/job-binaries");
		Assert.assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode requestNode = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertEquals("SDK-unittests", requestNode.get("name").asText());
		Assert.assertEquals("/sdk/unittests/input1", requestNode.get("url").asText());
		Assert.assertEquals("SDK-unittests", requestNode.get("description").asText());
		Assert.assertFalse(requestNode.get("is_protected").asBoolean());
		Assert.assertFalse(requestNode.get("is_public").asBoolean());

		Assert.assertEquals(jobBinary.getId(), "092b628b-26a3-4571-9ba4-f8d000df8877");
		Assert.assertEquals(jobBinary.isPublic(), Boolean.FALSE);
		Assert.assertEquals(jobBinary.isProtected(), Boolean.FALSE);
		Assert.assertEquals(jobBinary.getTenantId(), "46abc9f8044f40428f83a06cba0d5ddb");
		Assert.assertEquals(jobBinary.getCreatedAt().getTime(), 1488246418000L);
		Assert.assertEquals(jobBinary.getUpdatedAt(), null);
		Assert.assertEquals(jobBinary.getName(), "my-job-binary");
		Assert.assertEquals(jobBinary.getDescription(), "this is the job binary for map reduce job");
		Assert.assertEquals(jobBinary.getURL(), "swift://simple/mapreduce/input");
	}

	@Override
	protected Service service() {
		return Service.SAHARA;
	}

}

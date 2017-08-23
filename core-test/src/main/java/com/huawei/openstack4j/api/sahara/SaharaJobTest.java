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
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.core.transport.ObjectMapperSingleton;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.sahara.Job;
import com.huawei.openstack4j.model.sahara.JobBinary;
import com.huawei.openstack4j.model.sahara.Job.JobType;
import com.huawei.openstack4j.model.sahara.options.JobListOptions;
import com.huawei.openstack4j.openstack.sahara.domain.SaharaJob;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Sahara/Job", enabled = true)
public class SaharaJobTest extends AbstractTest {

	@Test
	public void testGetJob() throws IOException, InterruptedException {
		respondWith("/sahara/get_job_response.json");

		Job job = osv3().sahara().jobs().get("job-id");

		RecordedRequest request = server.takeRequest();
		AssertJUnit.assertEquals(request.getPath(), "/v1.1/project-id/jobs/job-id");
		AssertJUnit.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(job.getId(), "a5c1e223-e37d-4059-ad90-7a5fd35bbb68");
		Assert.assertEquals(job.getName(), "my-job");
		Assert.assertEquals(job.getType(), JobType.MapReduce);
		Assert.assertEquals(job.isPublic(), Boolean.FALSE);
		Assert.assertEquals(job.isProtected(), Boolean.FALSE);
		Assert.assertEquals(job.getTenantId(), "46abc9f8044f40428f83a06cba0d5ddb");
		Assert.assertEquals(job.getCreatedAt().getTime(), 1481312676000L);
		Assert.assertEquals(job.getUpdatedAt(), null);
		Assert.assertEquals(job.getDescription(), "This is the Map Reduce job template");

		List<? extends JobBinary> libs = job.getFullLibs();
		Assert.assertEquals(libs.size(), 1);
		JobBinary binary = libs.get(0);
		Assert.assertEquals(binary.getId(), "092b628b-26a3-4571-9ba4-f8d000df8877");
		Assert.assertEquals(binary.getName(), "my-job-binary");
		Assert.assertEquals(binary.getDescription(), "this is the job binary for map reduce job");
		Assert.assertEquals(binary.getURL(), "swift://simple/mapreduce/input");
		Assert.assertEquals(binary.getTenantId(), "46abc9f8044f40428f83a06cba0d5ddb");
		Assert.assertEquals(binary.getTenantId(), "46abc9f8044f40428f83a06cba0d5ddb");
		Assert.assertFalse(binary.isPublic());
		Assert.assertFalse(binary.isProtected());
	}

	@Test
	public void testUpdateJob() throws IOException, InterruptedException {
		respondWith("/sahara/update_job_response.json");

		Job update = SaharaJob.builder().id("job-id").name("SDK-unittests").type(JobType.Hive).description("desc")
				.addMain("main-id-1").isProtect(false).isPublic(false).build();
		Job job = osv3().sahara().jobs().update(update);

		RecordedRequest request = server.takeRequest();
		AssertJUnit.assertEquals(request.getPath(), "/v1.1/project-id/jobs/job-id");
		AssertJUnit.assertEquals(request.getMethod(), "PATCH");

		String requestBody = request.getBody().readUtf8();
		JsonNode requestNode = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		AssertJUnit.assertEquals("SDK-unittests", requestNode.get("name").asText());
		AssertJUnit.assertEquals("Hive", requestNode.get("type").asText());
		AssertJUnit.assertEquals("desc", requestNode.get("description").asText());
		Assert.assertTrue(requestNode.get("mains").isArray());
		Assert.assertEquals("main-id-1", requestNode.get("mains").get(0).asText());
		Assert.assertFalse(requestNode.get("is_protected").asBoolean());
		Assert.assertFalse(requestNode.get("is_public").asBoolean());

			  
		Assert.assertEquals(job.getId(), "71beddbc-9032-458b-b01e-83c515d31be2");
		Assert.assertEquals(job.getName(), "my_job0001");
		Assert.assertEquals(job.getType(), JobType.MapReduce);
		Assert.assertEquals(job.isPublic(), Boolean.FALSE);
		Assert.assertEquals(job.isProtected(), Boolean.FALSE);
		Assert.assertEquals(job.getTenantId(), "3f99e3319a8943ceb15c584f3325d064");
		Assert.assertEquals(job.getCreatedAt().getTime(), 1484636436000L);
		Assert.assertEquals(job.getUpdatedAt().getTime(), 1487587045000L);
		Assert.assertEquals(job.getDescription(), "This is a Map Reduce job template");

		List<? extends JobBinary> mains = job.getFullMains();
		Assert.assertEquals(mains.size(), 1);
		JobBinary binary = mains.get(0);
		Assert.assertEquals(binary.getId(), "36dc7b90-d8a4-4e05-83ae-b1c03095c680");
		Assert.assertEquals(binary.getName(), "jar-example15.jar");
		Assert.assertEquals(binary.getDescription(), "This is a job binary15");
		Assert.assertEquals(binary.getURL(), "swift://container/jar-example.jar");
		Assert.assertEquals(binary.getTenantId(), "3f99e3319a8943ceb15c584f3325d064");
		Assert.assertTrue(binary.isPublic());
		Assert.assertEquals(binary.getCredentials().getPassword(), "admin");
		Assert.assertEquals(binary.getCredentials().getUser(), "admin");
		
	}

	@Test
	public void testListJob() throws IOException, InterruptedException {
		respondWith("/sahara/list_job_response.json");

		JobListOptions options = JobListOptions.create().asc("created_at").limit(10).marker("last-job-id");
		List<? extends Job> jobs = osv3().sahara().jobs().list(options);

		RecordedRequest request = server.takeRequest();
		AssertJUnit.assertEquals(request.getPath(),
				"/v1.1/project-id/jobs?limit=10&sort_by=created_at&marker=last-job-id");
		AssertJUnit.assertEquals(request.getMethod(), "GET");

		AssertJUnit.assertEquals(jobs.size(), 2);
		Job job = jobs.get(0);
		Assert.assertEquals(job.getId(), "66d81fda-264f-40ef-8c4f-3c65408e9611");
		Assert.assertEquals(job.getName(), "my_job64552243zz44hh77");
		Assert.assertEquals(job.getType(), JobType.MapReduce);
		Assert.assertEquals(job.isPublic(), Boolean.FALSE);
		Assert.assertEquals(job.isProtected(), Boolean.FALSE);
		Assert.assertEquals(job.getTenantId(), "3f99e3319a8943ceb15c584f3325d064");
		Assert.assertEquals(job.getCreatedAt().getTime(), 1484623561000L);
		Assert.assertEquals(job.getUpdatedAt().getTime(), 1484623561000L);
		Assert.assertEquals(job.getDescription(), "This is the Map Reduce job template");
		Assert.assertEquals(job.getMains(), null);
		Assert.assertEquals(job.getLibs(), null);
		
	}

	@Test
	public void testDeleteJob() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse delete = osv3().sahara().jobs().delete("job-id");
		AssertJUnit.assertTrue(delete.isSuccess());

		RecordedRequest request = server.takeRequest();
		AssertJUnit.assertEquals(request.getPath(), "/v1.1/project-id/jobs/job-id");
		AssertJUnit.assertEquals(request.getMethod(), "DELETE");
	}

	@Test
	public void testCreateJob() throws IOException, InterruptedException {
		respondWith("/sahara/create_job_response.json");

		Job create = SaharaJob.builder().name("SDK-unittests").isProtect(false).isPublic(false).type(JobType.MapReduce)
				.description("desc").addLib("lib-id-1").addLib("lib-id-2").addMain("main-id-1").addMain("main-id-2")
				.build();

		Job job = osv3().sahara().jobs().create(create);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/jobs");
		Assert.assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode requestNode = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertEquals("SDK-unittests", requestNode.get("name").asText());
		Assert.assertEquals("desc", requestNode.get("description").asText());
		Assert.assertEquals("MapReduce", requestNode.get("type").asText());
		Assert.assertTrue(requestNode.get("libs").isArray());
		Assert.assertEquals("lib-id-1", requestNode.get("libs").get(0).asText());
		Assert.assertEquals("lib-id-2", requestNode.get("libs").get(1).asText());
		Assert.assertTrue(requestNode.get("mains").isArray());
		Assert.assertEquals("main-id-1", requestNode.get("mains").get(0).asText());
		Assert.assertEquals("main-id-2", requestNode.get("mains").get(1).asText());
		Assert.assertFalse(requestNode.get("is_protected").asBoolean());
		Assert.assertFalse(requestNode.get("is_public").asBoolean());

		Assert.assertEquals(job.getId(), "a5c1e223-e37d-4059-ad90-7a5fd35bbb68");
		Assert.assertEquals(job.getName(), "my-job");
		Assert.assertEquals(job.getType(), JobType.MapReduce);
		Assert.assertEquals(job.isPublic(), Boolean.FALSE);
		Assert.assertEquals(job.isProtected(), Boolean.FALSE);
		Assert.assertEquals(job.getTenantId(), "46abc9f8044f40428f83a06cba0d5ddb");
		Assert.assertEquals(job.getCreatedAt().getTime(), 1481312676000L);
		Assert.assertEquals(job.getUpdatedAt(), null);
		Assert.assertEquals(job.getDescription(), "This is the Map Reduce job template");

		List<? extends JobBinary> libs = job.getFullLibs();
		Assert.assertEquals(libs.size(), 1);
		JobBinary binary = libs.get(0);
		Assert.assertEquals(binary.getId(), "092b628b-26a3-4571-9ba4-f8d000df8877");
		Assert.assertEquals(binary.getName(), "my-job-binary");
		Assert.assertEquals(binary.getDescription(), "this is the job binary for map reduce job");
		Assert.assertEquals(binary.getURL(), "swift://simple/mapreduce/input");
		Assert.assertEquals(binary.getTenantId(), "46abc9f8044f40428f83a06cba0d5ddb");
		Assert.assertEquals(binary.getTenantId(), "46abc9f8044f40428f83a06cba0d5ddb");
		Assert.assertFalse(binary.isPublic());
		Assert.assertFalse(binary.isProtected());
	}
	

	@Override
	protected Service service() {
		return Service.SAHARA;
	}

}

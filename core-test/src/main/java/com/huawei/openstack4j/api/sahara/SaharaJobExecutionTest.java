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
import com.huawei.openstack4j.model.sahara.JobConfig;
import com.huawei.openstack4j.model.sahara.JobExecution;
import com.huawei.openstack4j.model.sahara.options.JobExecutionListOptions;
import com.huawei.openstack4j.openstack.sahara.domain.SaharaJobConfig;
import com.huawei.openstack4j.openstack.sahara.domain.SaharaJobExecution;

import com.google.common.collect.Lists;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Sahara/JobExecution", enabled = true)
public class SaharaJobExecutionTest extends AbstractTest {

	@Test
	public void testGetJobExecution() throws IOException, InterruptedException {
		respondWith("/sahara/get_job_execution_response.json");

		JobExecution execution = osv3().sahara().jobExecutions().get("job-execution-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/job-executions/job-execution-id");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(execution.getId(), "f31b9bc7-0918-4924-b7d4-59c1b1d72933");
		Assert.assertEquals(execution.getJobId(), "15429516-aff2-44ef-b9de-14a4fc1cff63");
		Assert.assertEquals(execution.getEngineJobId(), "0000000-161209184814131-oozie-hado-W");
		Assert.assertEquals(execution.isPublic(), Boolean.FALSE);
		Assert.assertEquals(execution.isProtected(), Boolean.FALSE);
		Assert.assertEquals(execution.getReturnCode(), null);
		Assert.assertEquals(execution.getOozieJobId(), "0000000-161209184814131-oozie-hado-W");
		Assert.assertEquals(execution.getTenantId(), "46abc9f8044f40428f83a06cba0d5ddb");
		Assert.assertEquals(execution.getInputId(), "ce8c2b04-f46c-4580-8b58-5b6aaf4a44a9");
		Assert.assertEquals(execution.getOutputId(), "9d59ce5b-d0f4-46d4-8738-6e50c2a5c68a");
		Assert.assertEquals(execution.getClusterId(), "2b460e01-3351-4170-b0a7-57b9dd5ffef3");
		Assert.assertEquals(execution.getCreatedAt().getTime(), 1481309572000L);
		Assert.assertEquals(execution.getUpdatedAt().getTime(), 1481309873000L);
		Assert.assertEquals(execution.getStartTime().getTime(), 1481309744000L);
		Assert.assertEquals(execution.getEndTime(), null);
		Assert.assertEquals(execution.getDataSourceUrls().get("ce8c2b04-f46c-4580-8b58-5b6aaf4a44a9"), "swift://simple/pigtest/input");
		Assert.assertEquals(execution.getDataSourceUrls().get("9d59ce5b-d0f4-46d4-8738-6e50c2a5c68a"), "swift://simple/pigtest/output");
		
	}

	@Test
	public void testListJobExecution() throws IOException, InterruptedException {
		respondWith("/sahara/list_job_execution_response.json");

		JobExecutionListOptions options = JobExecutionListOptions.create().asc("created_at").limit(10)
				.marker("last-binary-id");
		List<? extends JobExecution> jobExecutions = osv3().sahara().jobExecutions().list(options);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(),
				"/v1.1/project-id/job-executions?limit=10&sort_by=created_at&marker=last-binary-id");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(jobExecutions.size(), 1);
		JobExecution execution = jobExecutions.get(0);
		
		Assert.assertEquals(execution.getId(), "20da9edb-12ce-4b45-a473-41baeefef997");
		Assert.assertEquals(execution.getJobId(), "310b0fc6-e1db-408e-8798-312e7500f3ac");
		Assert.assertEquals(execution.getEngineJobId(), "0000001-150915094349962-oozie-hado-W");
		Assert.assertEquals(execution.isPublic(), Boolean.FALSE);
		Assert.assertEquals(execution.isProtected(), Boolean.FALSE);
		Assert.assertEquals(execution.getReturnCode(), null);
		Assert.assertEquals(execution.getOozieJobId(), "0000001-150915094349962-oozie-hado-W");
		Assert.assertEquals(execution.getTenantId(), "808d5032ea0446889097723bfc8e919d");
		Assert.assertEquals(execution.getInputId(), "3e1bc8e6-8c69-4749-8e52-90d9341d15bc");
		Assert.assertEquals(execution.getOutputId(), "52146b52-6540-4aac-a024-fee253cf52a9");
		Assert.assertEquals(execution.getClusterId(), "811e1134-666f-4c48-bc92-afb5b10c9d8c");
		Assert.assertEquals(execution.getCreatedAt().getTime(), 1442310564000L);
		Assert.assertEquals(execution.getUpdatedAt().getTime(), 1442310646000L);
		Assert.assertEquals(execution.getStartTime().getTime(), 1442321383000L);
		Assert.assertEquals(execution.getEndTime().getTime(), 1442321446000L);
		Assert.assertEquals(execution.getDataSourceUrls().get("3e1bc8e6-8c69-4749-8e52-90d9341d15bc"), "swift://ap-cont/input");
		Assert.assertEquals(execution.getDataSourceUrls().get("52146b52-6540-4aac-a024-fee253cf52a9"), "swift://ap-cont/output");
	
	}

	@Test
	public void testDeleteJobExecution() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse delete = osv3().sahara().jobExecutions().delete("job-execution-id");
		Assert.assertTrue(delete.isSuccess());

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/job-executions/job-execution-id");
		Assert.assertEquals(request.getMethod(), "DELETE");
	}

	@Test
	public void testCreateJobExecution() throws IOException, InterruptedException {
		respondWith("/sahara/create_job_execution_response.json");

		JobConfig jobConfig = SaharaJobConfig.builder().addConfig("mapred.map.tasks", 1)
				.addConfig("mapred.reduce.tasks", 1).addArg("wordcount").addParam("param1", "value1")
				.addParam("param2", "value2").build();
		
		JobExecution create = SaharaJobExecution.builder().jobId("job-id").clusterId("cluster-id")
				.inputId("input-id").outputId("output-id").setJobConfig(jobConfig)
				.isProtect(false).isPublic(false).build();
		
		JobExecution execution = osv3().sahara().jobExecutions().create(create);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/jobs/job-id/execute");
		Assert.assertEquals(request.getMethod(), "POST");
		

		String requestBody = request.getBody().readUtf8();
		JsonNode requestNode = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertEquals("cluster-id", requestNode.get("cluster_id").asText());
		Assert.assertEquals("input-id", requestNode.get("input_id").asText());
		Assert.assertEquals("output-id", requestNode.get("output_id").asText());
		Assert.assertFalse(requestNode.get("is_protected").asBoolean());
		Assert.assertFalse(requestNode.get("is_public").asBoolean());
		
		JsonNode configs = requestNode.get("job_configs");
		Assert.assertEquals(1, configs.get("configs").get("mapred.map.tasks").asInt());
		Assert.assertEquals(1, configs.get("configs").get("mapred.reduce.tasks").asInt());
		Assert.assertTrue(configs.get("args").isArray());
		Assert.assertEquals("wordcount", configs.get("args").get(0).asText());
		Assert.assertEquals("value1", configs.get("params").get("param1").asText());
		Assert.assertEquals("value2", configs.get("params").get("param2").asText());
		

		Assert.assertEquals(execution.getId(), "4a56525d-34db-43e3-99c9-af67491025cd");
		Assert.assertEquals(execution.getJobId(), "2c12ff33-da22-47b1-b51f-2828c16bbad8");
		Assert.assertEquals(execution.isPublic(), null);
		Assert.assertEquals(execution.isProtected(), null);
		Assert.assertEquals(execution.getReturnCode(), null);
		Assert.assertEquals(execution.getOozieJobId(), null);
		Assert.assertEquals(execution.getTenantId(), "3f99e3319a8943ceb15c584f3325d064");
		Assert.assertEquals(execution.getInputId(), "ce8c2b04-f46c-4580-8b58-5b6aaf4a44a9");
		Assert.assertEquals(execution.getOutputId(), "9d59ce5b-d0f4-46d4-8738-6e50c2a5c68a");
		Assert.assertEquals(execution.getClusterId(), "c1000b4f-f2a1-49e1-af3c-2e19fc1eb72d");
		Assert.assertEquals(execution.getCreatedAt().getTime(), 1487581892000L);
		Assert.assertEquals(execution.getUpdatedAt().getTime(), 1487581892000L);
		Assert.assertEquals(execution.getStartTime().getTime(), 1487581892000L);
		Assert.assertEquals(execution.getEndTime(), null);
		Assert.assertEquals(execution.getInfo(), null);
		Assert.assertEquals(execution.getDataSourceUrls(), null);
		
		JobConfig jobConfigs = execution.getJobConfigs();
		Assert.assertEquals(jobConfigs.getConfigs().get("mapred.map.tasks"), "1");
		Assert.assertEquals(jobConfigs.getConfigs().get("mapred.reduce.tasks"), "1");
		Assert.assertEquals(jobConfigs.getArgs(), Lists.newArrayList("wordcount ", "arg2"));
		Assert.assertEquals(jobConfigs.getParams().get("param2"), "value2");
		Assert.assertEquals(jobConfigs.getParams().get("param1"), "value1");
		
	}

	@Override
	protected Service service() {
		return Service.SAHARA;
	}

}

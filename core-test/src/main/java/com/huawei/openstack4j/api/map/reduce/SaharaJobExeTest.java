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

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.map.reduce.options.JobExeListOptions;
import com.huawei.openstack4j.openstack.map.reduce.constants.JobFinalStatus;
import com.huawei.openstack4j.openstack.map.reduce.constants.JobState;
import com.huawei.openstack4j.openstack.map.reduce.constants.JobType;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExe;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExeCreate;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Sahara/JobExe", enabled = true)
public class SaharaJobExeTest extends AbstractTest {

	@Test
	public void testCreateSaharaJobExe() throws IOException, InterruptedException {
		respondWith("/sahara/create_job_and_execute_response.json");

		MapReduceJobExeCreate jobExeCreate = MapReduceJobExeCreate.builder().jobType(JobType.Spark).jobName("sdk-unittests")
				.clusterId("cluster-id").jarPath("s3a://sdk/jar.jar").arguments("wordcount").input("s3a://sdk/input")
				.output("s3a://sdk/output").jobLog("s3a://sdk/log").fileAction("export").hql("hql")
				.hiveScriptPath("s3a://sdk/script.hql").isProtected(true).isPublic(false).build();
		MapReduceJobExe exe = osv3().mrs().jobExes().create(jobExeCreate);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/jobs/submit-job");
		Assert.assertEquals(request.getMethod(), "POST");
		
		// assert post body
		String requestBody = request.getBody().readUtf8();
		String expectBody = getResource("/sahara/create_job_and_execute_request.json");
		Assert.assertEquals(expectBody, requestBody);
		
		// assert response mapping
		Assert.assertEquals(exe.getId(), "12ee9ae4-6ee1-48c6-bb84-fb0b4f76cf03");
		Assert.assertEquals(exe.getTemplated().booleanValue(), false);
		Assert.assertEquals(exe.getCreatedAt().getTime(), 1496387588913L);
		Assert.assertEquals(exe.getUpdatedAt().getTime(), 1496387588913L);
		Assert.assertEquals(exe.getTenantId(), "c71ad83a66c5470496c2ed6e982621cc");
		Assert.assertEquals(exe.getJobId(), "");
		Assert.assertEquals(exe.getJobName(), "mrs_test_jobone_20170602_141106");
		Assert.assertEquals(exe.getInputId(), null);
		Assert.assertEquals(exe.getOutputId(), null);
		Assert.assertEquals(exe.getStartTime().getTime(), 1496387588913L);
		Assert.assertEquals(exe.getEndTime(), null);
		Assert.assertEquals(exe.getClusterId(), "e955a7a3-d334-4943-a39a-994976900d56");
		Assert.assertEquals(exe.getEnineJobId(), null);
		Assert.assertEquals(exe.getReturnCode(), null);
		Assert.assertEquals(exe.getIsPublic(), null);
		Assert.assertEquals(exe.getIsProtected(), null);
		Assert.assertEquals(exe.getGroupId(), "12ee9ae4-6ee1-48c6-bb84-fb0b4f76cf03");
		Assert.assertEquals(exe.getJarPath(), "s3a://mrs-opsadm/jarpath/hadoop-mapreduce-examples-2.7.2.jar");
		Assert.assertEquals(exe.getInput(), "s3a://mrs-opsadm/input/");
		Assert.assertEquals(exe.getOutput(), "s3a://mrs-opsadm/output/");
		Assert.assertEquals(exe.getJobLog(), "s3a://mrs-opsadm/log/");
		Assert.assertEquals(exe.getJobType(), JobType.MapReduce);
		Assert.assertEquals(exe.getFileAction(), "");
		Assert.assertEquals(exe.getArguments(), "wordcount");
		Assert.assertEquals(exe.getHql(), "");
		Assert.assertEquals(exe.getHiveScriptPath(), "");
		Assert.assertEquals(exe.getJobState(), JobState.Running);
		Assert.assertEquals(exe.getJobFinalStatus(), JobFinalStatus.Running);
		Assert.assertEquals(exe.getCreateBy(), "b67132be2f054a45b247365647e05af0");
		Assert.assertEquals(exe.getFinishedStep().intValue(), 0);
		Assert.assertEquals(exe.getJobMainId(), "");
		Assert.assertEquals(exe.getJobStepId(), "");
		Assert.assertEquals(exe.getPostponeAt().getTime(), 1496387588911L);
		Assert.assertEquals(exe.getStepName(), "");
		Assert.assertEquals(exe.getStepNum().intValue(), 0);
		Assert.assertEquals(exe.getTaskNum().intValue(), 0);
		Assert.assertEquals(exe.getUpdateBy(), "b67132be2f054a45b247365647e05af0");
		Assert.assertEquals(exe.getCredentials(), "");
		Assert.assertEquals(exe.getUserId(), "b67132be2f054a45b247365647e05af0");
		Assert.assertEquals(exe.getJobConfigs(), null);
		Assert.assertEquals(exe.getExtra(), null);
		Assert.assertEquals(exe.getDataSourceUrls(), null);
		Assert.assertEquals(exe.getInfo(), null);

	}

	@Test
	public void testGetSaharaJobExe() throws IOException, InterruptedException {
		respondWith("/sahara/get_job_exe_response.json");

		MapReduceJobExe exe = osv3().mrs().jobExes().get("job-exe-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/job-exes/job-exe-id");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(exe.getId(), "632863d5-15d4-4691-9dc1-1a72340cb097");
		Assert.assertEquals(exe.getJobId(), "632863d5-15d4-4691-9dc1-1a72340cb097");
		Assert.assertEquals(exe.getJobName(), "hive_script");
		Assert.assertEquals(exe.getTenantId(), "3f99e3319a8943ceb15c584f3325d064");
		Assert.assertEquals(exe.getInput(), "s3a://jp-test1/input/");
		Assert.assertEquals(exe.getOutput(), "s3a://jp-test1/output/");
		Assert.assertEquals(exe.getJobLog(), "s3a://jp-test1/joblogs/");
		Assert.assertEquals(exe.getFileAction(), "");
		Assert.assertEquals(exe.getArguments(), "wordcount");
		Assert.assertEquals(exe.getHql(), null);
		Assert.assertEquals(exe.getJobState(), JobState.Completed);
		Assert.assertEquals(exe.getJobFinalStatus(), JobFinalStatus.Terminated);
		Assert.assertEquals(exe.getClusterId(), "8b1d55f6-150e-45e2-8347-b2ca608d366b");
		Assert.assertEquals(exe.getGroupId(), "632863d5-15d4-4691-9dc1-1a72340cb097");
		Assert.assertEquals(exe.getJarPath(), "s3a://jp-test1/program/Hivescript.sql");
		Assert.assertEquals(exe.getHiveScriptPath(), "s3a://jp-test1/program/Hivescript.sql");
		Assert.assertEquals(exe.getCreateBy(), "3f99e3319a8943ceb15c584f3325d064");
		Assert.assertEquals(exe.getUpdateBy(), "3f99e3319a8943ceb15c584f3325d064");
		Assert.assertEquals(exe.getFinishedStep().intValue(), 0);
		Assert.assertEquals(exe.getJobMainId(), "");
		Assert.assertEquals(exe.getJobStepId(), "");
		Assert.assertEquals(exe.getStepName(), "");
		Assert.assertEquals(exe.getStepNum().intValue(), 0);
		Assert.assertEquals(exe.getTaskNum().intValue(), 0);
		Assert.assertEquals(exe.getSpendTime(), null);
		Assert.assertEquals(exe.getStepSeq().intValue(), 222);
		Assert.assertEquals(exe.getProgress(), "first progress");
		Assert.assertEquals(exe.getPostponeAt().getTime(), 1484240558705L);
		Assert.assertEquals(exe.getCreateAt().getTime(), 1484240559176L);
		Assert.assertEquals(exe.getUpdateAt().getTime(), 1484240559176L);
		Assert.assertEquals(exe.getStartTime().getTime(), 1484240559176L);
		Assert.assertEquals(exe.getEndTime(), null);

	}

	@Test
	public void testListSaharaJobExe() throws IOException, InterruptedException {
		respondWith("/sahara/list_job_exe_response.json");

		JobExeListOptions options = JobExeListOptions.create();
		List<? extends MapReduceJobExe> jobExes = osv3().mrs().jobExes().list(options);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/job-exes");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(jobExes.size(), 1);
		MapReduceJobExe exe = jobExes.get(0);

		Assert.assertEquals(exe.getId(), "669476bd-89d2-45aa-8f1a-872d16de377e");
		Assert.assertEquals(exe.getJobId(), "669476bd-89d2-45aa-8f1a-872d16de377e");
		Assert.assertEquals(exe.getJobName(), "myfirstjob");
		Assert.assertEquals(exe.getTenantId(), "3f99e3319a8943ceb15c584f3325d064");
		Assert.assertEquals(exe.getInput(), "s3a://jp-test1/input/");
		Assert.assertEquals(exe.getOutput(), "s3a://jp-test1/output/");
		Assert.assertEquals(exe.getJobLog(), "s3a://jp-test1/joblogs/");
		Assert.assertEquals(exe.getFileAction(), "");
		Assert.assertEquals(exe.getArguments(), "wordcount");
		Assert.assertEquals(exe.getHql(), "");

		Assert.assertEquals(exe.getJobState(), JobState.Running);
		Assert.assertEquals(exe.getJobFinalStatus(), JobFinalStatus.Terminated);
		Assert.assertEquals(exe.getClusterId(), "2b460e01-3351-4170-b0a7-57b9dd5ffef3");
		Assert.assertEquals(exe.getGroupId(), "669476bd-89d2-45aa-8f1a-872d16de377e");
		Assert.assertEquals(exe.getJarPath(), "s3a://jp-test1/program/hadoop-mapreduce-examples-2.4.1.jar");
		Assert.assertEquals(exe.getHiveScriptPath(), null);
		Assert.assertEquals(exe.getCreateBy(), "3f99e3319a8943ceb15c584f3325d064");
		Assert.assertEquals(exe.getUpdateBy(), "3f99e3319a8943ceb15c584f3325d064");
		Assert.assertEquals(exe.getFinishedStep().intValue(), 0);
		Assert.assertEquals(exe.getJobMainId(), "");
		Assert.assertEquals(exe.getJobStepId(), "");
		Assert.assertEquals(exe.getJobType(), JobType.MapReduce);
		Assert.assertEquals(exe.getStepName(), "");

		Assert.assertEquals(exe.getStepNum().intValue(), 0);
		Assert.assertEquals(exe.getTaskNum().intValue(), 0);
		Assert.assertEquals(exe.getSpendTime(), null);
		Assert.assertEquals(exe.getStepSeq().intValue(), 222);
		Assert.assertEquals(exe.getProgress(), "first progress");
		Assert.assertEquals(exe.getPostponeAt().getTime(), 1484641003174L);
		Assert.assertEquals(exe.getCreateAt().getTime(), 1484641003707L);
		Assert.assertEquals(exe.getUpdateAt().getTime(), 1484641003707L);
		Assert.assertEquals(exe.getStartTime().getTime(), 1484641003707L);
		Assert.assertEquals(exe.getEndTime(), null);

	}

	@Override
	protected Service service() {
		return Service.SAHARA;
	}

}

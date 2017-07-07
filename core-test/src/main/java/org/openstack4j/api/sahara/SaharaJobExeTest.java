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
package org.openstack4j.api.sahara;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.sahara.options.JobExeListOptions;
import org.openstack4j.openstack.sahara.domain.SaharaJobExe;
import org.openstack4j.openstack.sahara.domain.SaharaJobExe.JobFinalStatus;
import org.openstack4j.openstack.sahara.domain.SaharaJobExe.JobState;
import org.openstack4j.openstack.sahara.domain.SaharaJobExe.JobType;
import org.testng.Assert;
import org.testng.annotations.Test;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Sahara/JobExe", enabled = true)
public class SaharaJobExeTest extends AbstractTest {

	@Test
	public void testGetSaharaJobExe() throws IOException, InterruptedException {
		respondWith("/sahara/get_job_exe_response.json");

		SaharaJobExe exe = osv3().sahara().jobExes().get("job-exe-id");

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
		List<? extends SaharaJobExe> jobExes = osv3().sahara().jobExes().list(options);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/job-exes");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(jobExes.size(), 1);
		SaharaJobExe exe = jobExes.get(0);

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

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
package org.openstack4j.sample.mrs;

import java.util.List;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.sahara.Job;
import org.openstack4j.model.sahara.Job.JobType;
import org.openstack4j.model.sahara.JobConfig;
import org.openstack4j.model.sahara.JobExecution;
import org.openstack4j.model.sahara.options.JobListOptions;
import org.openstack4j.openstack.sahara.domain.SaharaJob;
import org.openstack4j.openstack.sahara.domain.SaharaJobConfig;
import org.openstack4j.openstack.sahara.domain.SaharaJobExecution;
import org.openstack4j.sample.AbstractSample;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-05 15:36:39
 */
public class JobSample extends AbstractSample {

	String name = randomName();
	Job createdJob;

	@BeforeClass
	public void testCreateJob() {
		Job build = SaharaJob.builder().name(name).type(JobType.MapReduce).description("sdk unittest").isProtect(true)
				.isPublic(true).build();
		createdJob = osclient.sahara().jobs().create(build);
		Assert.assertEquals(name, createdJob.getName());
		Assert.assertEquals(JobType.MapReduce, createdJob.getType());
		Assert.assertEquals("sdk unittest", createdJob.getDescription());
		Assert.assertTrue(createdJob.isProtected());
		Assert.assertTrue(createdJob.isPublic());
	}

	@AfterClass
	public void testDeleteJob() {
		ActionResponse delete = osclient.sahara().jobs().delete(createdJob.getId());
		Assert.assertTrue(delete.isSuccess());
	}

	@Test
	public void testGetJob() {
		Job job = osclient.sahara().jobs().get(createdJob.getId());
		Assert.assertEquals(job.getId(), createdJob.getId());
		Assert.assertEquals(name, createdJob.getName());
		Assert.assertEquals(JobType.MapReduce, createdJob.getType());
		Assert.assertEquals("sdk unittest", createdJob.getDescription());
		Assert.assertTrue(job.isProtected());
		Assert.assertTrue(job.isPublic());
	}

	@Test(dependsOnMethods = { "testGetJob" })
	public void testUpdateJob() {
		Job build = SaharaJob.builder().id(createdJob.getId()).name("sdk-new-name").description("updated desc")
				.type(JobType.Spark).isProtect(false).isPublic(false).build();
		createdJob = osclient.sahara().jobs().update(build);
		Assert.assertEquals(createdJob.getType(), JobType.Spark);
		Assert.assertEquals(createdJob.getName(), "sdk-new-name");
		Assert.assertEquals(createdJob.getDescription(), "updated desc");
		Assert.assertFalse(createdJob.isProtected());
		Assert.assertFalse(createdJob.isPublic());
	}

	@Test(dependsOnMethods = { "testUpdateJob" })
	public void testListJob() {
		JobListOptions options = JobListOptions.create().desc("created_at").limit(10);
		List<? extends Job> list = osclient.sahara().jobs().list(options);
		boolean found = false;
		for (Job jobBinary : list) {
			if (jobBinary.getId().equals(createdJob.getId())) {
				found = true;
				break;
			}
		}
		Assert.assertTrue(found);
	}
	
	
	/**
	 * TODO
	 */
	public void testExecuteJob() {
		JobConfig jobConfig = SaharaJobConfig.builder().addConfig("mapred.map.tasks", 1)
				.addConfig("mapred.reduce.tasks", 1).addArg("wordcount").addParam("param1", "value1")
				.addParam("param2", "value2").build();
		JobExecution jobExecution = SaharaJobExecution.builder().jobId("to-execute-job-id").clusterId("cluster-id")
				.inputId("input-id").outputId("output-id").isProtect(true).isPublic(true).setJobConfig(jobConfig)
				.build();
		JobExecution execution = osclient.sahara().jobExecutions().create(jobExecution);
	}

}

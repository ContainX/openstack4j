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
package com.huawei.openstack4j.functional.mrs;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.map.reduce.JobExecution;
import com.huawei.openstack4j.model.map.reduce.options.JobExecutionListOptions;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExecution;


/**
 * TODO not finished
 *
 * @author QianBiao.NG
 * @date   2017-07-05 15:36:39
 */
public class JobExecutionTest extends AbstractTest {

	String name = randomName();
	JobExecution createdJobExecution;

	@BeforeClass
	public void testCreateJobExecution() {
		JobExecution build = MapReduceJobExecution.builder().isProtect(true).isPublic(true).build();
		createdJobExecution = osclient.mrs().jobExecutions().create(build);
		Assert.assertTrue(createdJobExecution.isProtected());
		Assert.assertTrue(createdJobExecution.isPublic());
	}

	@AfterClass
	public void testDeleteJobExecution() {
		ActionResponse delete = osclient.mrs().jobExecutions().delete(createdJobExecution.getId());
		Assert.assertTrue(delete.isSuccess());
	}

	@Test
	public void testGetJobExecution() {
		JobExecution execution = osclient.mrs().jobExecutions().get(createdJobExecution.getId());
		Assert.assertEquals(execution.getId(), createdJobExecution.getId());
		Assert.assertTrue(execution.isProtected());
		Assert.assertTrue(execution.isPublic());
	}
	
	@Test
	public void testCancelJobExecution() {
		JobExecution execution = osclient.mrs().jobExecutions().cancel(createdJobExecution.getId());
		Assert.assertEquals(execution.getId(), createdJobExecution.getId());
		Assert.assertTrue(execution.isProtected());
		Assert.assertTrue(execution.isPublic());
	}

	@Test(dependsOnMethods = { "testGetJobExecution" })
	public void testListJobExecution() {
		JobExecutionListOptions options = JobExecutionListOptions.create().desc("created_at").limit(10);
		List<? extends JobExecution> list = osclient.mrs().jobExecutions().list(options);
		boolean found = false;
		for (JobExecution jobBinary : list) {
			if (jobBinary.getId().equals(createdJobExecution.getId())) {
				found = true;
				break;
			}
		}
		Assert.assertTrue(found);
	}

}

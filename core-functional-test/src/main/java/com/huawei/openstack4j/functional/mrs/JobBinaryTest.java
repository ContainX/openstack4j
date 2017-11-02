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
import com.huawei.openstack4j.model.sahara.JobBinary;
import com.huawei.openstack4j.model.sahara.options.JobBinaryListOptions;
import com.huawei.openstack4j.openstack.sahara.domain.SaharaJobBinary;

public class JobBinaryTest extends AbstractTest {


	String name = randomName();
	JobBinary createdJobBinary;

	@BeforeClass
	public void testCreateJobBinary() {
		JobBinary build = SaharaJobBinary.builder().name(name).url("/sdk/unittest/input")
				.isProtect(true).isPublic(true).description("sdk unittests").build();
		createdJobBinary = osclient.sahara().jobBinaries().create(build);
		Assert.assertEquals(createdJobBinary.getName(), name);
		Assert.assertEquals(createdJobBinary.getURL(), "/sdk/unittest/input");
		Assert.assertTrue(createdJobBinary.isProtected());
		Assert.assertTrue(createdJobBinary.isPublic());
	}

	@AfterClass
	public void testDeleteJobBinary() {
		ActionResponse delete = osclient.sahara().jobBinaries().delete(createdJobBinary.getId());
		Assert.assertTrue(delete.isSuccess());
	}

	@Test
	public void testGetJobBinary() {
		JobBinary jobBinary = osclient.sahara().jobBinaries().get(createdJobBinary.getId());
		Assert.assertEquals(jobBinary.getId(), createdJobBinary.getId());
		Assert.assertEquals(jobBinary.getName(), name);
		Assert.assertEquals(jobBinary.getURL(), "/sdk/unittest/input");
		Assert.assertTrue(jobBinary.isProtected());
		Assert.assertTrue(jobBinary.isPublic());
	}

	@Test(dependsOnMethods = { "testGetJobBinary" })
	public void testUpdateJobBinary() {
		JobBinary build = SaharaJobBinary.builder().id(createdJobBinary.getId()).name("sdk-new-name")
				.url("/sdk/unittest/input2").build();
		createdJobBinary = osclient.sahara().jobBinaries().update(build);
		Assert.assertEquals(createdJobBinary.getURL(), "/sdk/unittest/input2");
		Assert.assertEquals(createdJobBinary.getName(), "sdk-new-name");
	}

	@Test(dependsOnMethods = { "testUpdateJobBinary" })
	public void testListJobBinary() {
		JobBinaryListOptions options = JobBinaryListOptions.create().desc("created_at").limit(10);
		List<? extends JobBinary> list = osclient.sahara().jobBinaries().list(options);
		boolean found = false;
		for (JobBinary jobBinary : list) {
			if (jobBinary.getId().equals(createdJobBinary.getId())) {
				found = true;
				break;
			}
		}
		Assert.assertTrue(found);
	}

}

/*******************************************************************************
 *  Copyright 2017 Huawei TLD
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
/*******************************************************************************
 *******************************************************************************/
package com.huawei.openstack4j.sample.vbs;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.storage.block.AsyncVolumeBackupJob;
import com.huawei.openstack4j.model.storage.block.Volume;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicy;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicyBackupTask;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicy.VolumeBackupPolicyStatus;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicyResource.VolumeBackupPolicyResourceActionResult;
import com.huawei.openstack4j.model.storage.block.options.BakcupTaskListOptions;
import com.huawei.openstack4j.openstack.storage.block.domain.VBSVolumeBackupPolicy;
import com.huawei.openstack4j.openstack.storage.block.domain.VBSVolumeBackupScheduledPolicy;
import com.huawei.openstack4j.sample.AbstractSample;

/**
 *
 * @author Woo Cubic
 * @date   2017-06-06 20:40:10
 */
@Test(suiteName = "VolumeBackup/volume-backup-policy")
public class VolumeBackupPolicySample extends AbstractSample {

	static String policyName = randomName();
	List<? extends Volume> volumes;
	VolumeBackupPolicy policy;
	AsyncVolumeBackupJob createJobDetail;

	@BeforeClass
	public void prepare() {
		// 准备获取最多两个，最少一个volumes用于后面的资源绑定
		Map<String, String> filter = Maps.newHashMap();
		filter.put("limit", "2");
		filter.put("status", Volume.Status.AVAILABLE.toString());
		List<? extends Volume> list = osclient.blockStorage().volumes().list(filter);
		if (list != null && list.size() > 0) {
			volumes = list;
		} else {
			throw new RuntimeException("No volume availble for test");
		}
	}

	@AfterClass
	public void cleanup() {

	}

	/**
	 * 创建一个新的 policy
	 */
	@Test
	public void testPolicyCreate() {
		VBSVolumeBackupScheduledPolicy scheduledPolicy = VBSVolumeBackupScheduledPolicy.builder().frequency(10)
				.maxBackupAmount(10).retainFirstBackupOfCurrentMonth(true).startTime("01:00")
				.status(VolumeBackupPolicyStatus.ON).build();

		VolumeBackupPolicy create = VBSVolumeBackupPolicy.builder().name(policyName).scheduledPolicy(scheduledPolicy)
				.build();
		this.policy = osclient.blockStorage().policies().create(create);
	}

	@Test(priority = 1, dependsOnMethods = { "testPolicyCreate" })
	public void testPolicyList() {
		this.policy = getPolicyById(this.policy.getId());
		Assert.assertEquals(this.policy.getName(), policyName);
		Assert.assertEquals(this.policy.getScheduledPolicy().getFrequency().intValue(), 10);
		Assert.assertEquals(this.policy.getScheduledPolicy().getMaxBackupAmount().intValue(), 10);
		Assert.assertEquals(this.policy.getScheduledPolicy().getStartTime(), "01:00");
		Assert.assertEquals(this.policy.getScheduledPolicy().getStatus(), VolumeBackupPolicyStatus.ON);
		Assert.assertTrue(this.policy.getScheduledPolicy().getRetainFirstBackupOfCurrentMonth());
	}

	/**
	 * @return 
	 * 
	 */
	public VolumeBackupPolicy getPolicyById(String policyId) {
		List<? extends VolumeBackupPolicy> policies = osclient.blockStorage().policies().list();
		for (VolumeBackupPolicy volumeBackupPolicy : policies) {
			if (volumeBackupPolicy.getId().equals(policyId)) {
				return volumeBackupPolicy;
			}
		}
		return null;
	}

	@Test(priority = 2, dependsOnMethods = { "testPolicyList" })
	public void testPolicyUpdate() {
		VBSVolumeBackupScheduledPolicy scheduledPolicy = VBSVolumeBackupScheduledPolicy.builder().frequency(5)
				.maxBackupAmount(5).retainFirstBackupOfCurrentMonth(false).status(VolumeBackupPolicyStatus.OFF).build();

		VBSVolumeBackupPolicy policy = VBSVolumeBackupPolicy.builder().id(this.policy.getId())
				.scheduledPolicy(scheduledPolicy).build();
		osclient.blockStorage().policies().update(policy);

		this.policy = getPolicyById(this.policy.getId());
		Assert.assertEquals(this.policy.getName(), policyName);
		Assert.assertEquals(this.policy.getScheduledPolicy().getFrequency().intValue(), 5);
		Assert.assertEquals(this.policy.getScheduledPolicy().getMaxBackupAmount().intValue(), 5);
		Assert.assertEquals(this.policy.getScheduledPolicy().getStartTime(), "01:00"); // not updated field
		Assert.assertEquals(this.policy.getScheduledPolicy().getStatus(), VolumeBackupPolicyStatus.OFF);
		Assert.assertFalse(this.policy.getScheduledPolicy().getRetainFirstBackupOfCurrentMonth());
	}

	@Test(priority = 3, dependsOnMethods = { "testPolicyList" })
	public void testLinkResources() {
		List<String> resources = this.volumes.stream().map(input -> input.getId()).collect(Collectors.toList());
		VolumeBackupPolicyResourceActionResult result = osclient.blockStorage().policies()
				.linkResources(this.policy.getId(), resources);
		Assert.assertEquals(result.getSuccessResources().size(), 2);

		List<String> collect = result.getSuccessResources().stream().map(input -> input.getId())
				.collect(Collectors.toList());
		Assert.assertEquals(collect, resources);

	}
	
	@Test(dependsOnMethods = { "testLinkResources" })
	public void testExecute() {
		ActionResponse execute = osclient.blockStorage().policies().execute(this.policy.getId());
		Assert.assertTrue(execute.isSuccess());
	}
	
	@Test(dependsOnMethods = { "testExecute" })
	public void testListBackupTasks() {
		BakcupTaskListOptions limit = BakcupTaskListOptions.create().asc().limit(10);
		List<? extends VolumeBackupPolicyBackupTask> tasks = osclient.blockStorage().policies().tasks(this.policy.getId(), null);
		tasks.size();
	}
	
	@Test(dependsOnMethods = { "testListBackupTasks" })
	public void testUnlinkResources() {
		List<String> resources = this.volumes.stream().map(input -> input.getId()).collect(Collectors.toList());
		VolumeBackupPolicyResourceActionResult result = osclient.blockStorage().policies()
				.unlinkResources(this.policy.getId(), resources);
		Assert.assertEquals(result.getSuccessResources().size(), 2);
		List<String> collect = result.getSuccessResources().stream().map(input -> input.getId())
				.collect(Collectors.toList());
		Assert.assertEquals(collect, resources);
	}
	
	
	@Test(dependsOnMethods = { "testPolicyUpdate" })
	public void testEnable() {
		VolumeBackupPolicy enable = osclient.blockStorage().policies().enable(this.policy.getId());
		this.policy = this.getPolicyById(enable.getId());
		Assert.assertEquals(this.policy.getScheduledPolicy().getStatus(), VolumeBackupPolicyStatus.ON);
	}
	
	@Test(dependsOnMethods = { "testEnable" })
	public void testDisable() {
		VolumeBackupPolicy enable = osclient.blockStorage().policies().disable(this.policy.getId());
		this.policy = this.getPolicyById(enable.getId());
		Assert.assertEquals(this.policy.getScheduledPolicy().getStatus(), VolumeBackupPolicyStatus.OFF);
	}
	
	

	@Test(priority = 100, dependsOnMethods = { "testDisable" })
	public void testPolicyDelete() {
		osclient.blockStorage().policies().delete(this.policy.getId());
		Assert.assertNull(getPolicyById(this.policy.getId()), "Policy delete failed");
	}

}

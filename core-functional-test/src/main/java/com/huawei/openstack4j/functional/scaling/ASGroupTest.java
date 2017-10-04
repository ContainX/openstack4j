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
package com.huawei.openstack4j.functional.scaling;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.scaling.ScalingGroup;
import com.huawei.openstack4j.model.scaling.ScalingGroup.HealthPeriodicAuditMethod;
import com.huawei.openstack4j.model.scaling.ScalingGroup.InstanceTerminatePolicy;
import com.huawei.openstack4j.model.scaling.ScalingGroup.ScalingGroupStatus;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingGroupUpdate;
import com.huawei.openstack4j.openstack.scaling.options.ScalingGroupListOptions;

/**
 *
 * @author Woo Cubic
 * @date 2017-06-06 20:40:10
 */
public class ASGroupTest extends BaseAutoScalingTest {

	String name = randomName();
	String asConfigId;
	String asGroupId;
	ScalingGroup create = null;

	@BeforeClass
	public void testCreateGroup() {
		asConfigId = createConfig(name);
		asGroupId = createGroup(name, asConfigId);
	}

	@AfterClass
	public void testDeleteGroup() {
		osclient.autoScaling().groups().delete(asGroupId);
		osclient.autoScaling().configs().delete(asConfigId);
	}

	@Test
	public void testGetAutoScalingGroup() {
		ScalingGroup group = osclient.autoScaling().groups().get(asGroupId);
		validateGroup(group);
		create = group;
	}

	@Test
	public void testListAutoScalingGroups() {
		ScalingGroupListOptions options = ScalingGroupListOptions.create().groupName(name).limit(5);
		List<? extends ScalingGroup> list = osclient.autoScaling().groups().list(options);
		Assert.assertEquals(list.size(), 1);
		ScalingGroup scalingGroup = list.get(0);
		validateGroup(scalingGroup);
	}

	/**
	 * @param group
	 */
	public void validateGroup(ScalingGroup group) {
		Assert.assertNotNull(group);
		Assert.assertEquals(group.getGroupName(), name);
		Assert.assertEquals(group.getConfigId(), asConfigId);
		Assert.assertEquals(group.getVpcId(), vpcId);
		Assert.assertEquals(group.getNetworks().size(), 1);
		Assert.assertEquals(group.getNetworks().get(0).getId(), networkId);
		Assert.assertEquals(group.getSecurityGroups().size(), 1);
		Assert.assertEquals(group.getSecurityGroups().get(0).getId(), sgId);
		Assert.assertEquals(group.getMaxInstanceNumber().intValue(), 2);
		Assert.assertEquals(group.getMinInstanceNumber().intValue(), 0);
		Assert.assertEquals(group.getDesireInstanceNumber().intValue(), 1);
		Assert.assertEquals(group.getCoolDownTime().intValue(), 200);
		Assert.assertEquals(group.getHealthPeriodicAuditMethod(), HealthPeriodicAuditMethod.NOVA_AUDIT);
		Assert.assertEquals(group.getHealthPeriodicAuditTime().intValue(), 5);
		Assert.assertEquals(group.getInstanceTerminatePolicy(), InstanceTerminatePolicy.OLD_CONFIG_OLD_INSTANCE);
	}

	@Test(dependsOnMethods = { "testGetAutoScalingGroup", "testListAutoScalingGroups" })
	public void testUpdateAutoScalingGroup() {
		ASAutoScalingGroupUpdate update = ASAutoScalingGroupUpdate.builder().groupName(name + "-updated").build();
		String groupId = osclient.autoScaling().groups().update(asGroupId, update);
		Assert.assertNotNull(groupId);

		ScalingGroup updated = osclient.autoScaling().groups().get(groupId);
		Assert.assertEquals(updated.getGroupName(), name + "-updated");
	}

	@Test(dependsOnMethods = { "testUpdateAutoScalingGroup" })
	public void testOperateAutoScalingGroup() {
		ActionResponse resp = osclient.autoScaling().groups().pause(asGroupId);
		Assert.assertTrue(resp.isSuccess(), resp.getFault());

		ScalingGroup group = osclient.autoScaling().groups().get(asGroupId);
		Assert.assertEquals(group.getGroupStatus(), ScalingGroupStatus.PAUSED);

		resp = osclient.autoScaling().groups().resume(asGroupId);
		Assert.assertTrue(resp.isSuccess(), resp.getFault());

		group = osclient.autoScaling().groups().get(asGroupId);
		Assert.assertEquals(group.getGroupStatus(), ScalingGroupStatus.INSERVICE);

	}

}

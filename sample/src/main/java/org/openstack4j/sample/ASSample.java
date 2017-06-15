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
package org.openstack4j.sample;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.ScalingGroup;
import org.openstack4j.model.scaling.ScalingGroupCreate;
import org.openstack4j.model.scaling.ScalingGroupUpdate;
import org.openstack4j.openstack.common.IdResourceEntity;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupCreate;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupUpdate;
import org.openstack4j.openstack.scaling.domain.action.ASScalingGroupAction.Pause;
import org.openstack4j.openstack.scaling.domain.action.ASScalingGroupAction.Resume;
import org.openstack4j.openstack.scaling.options.ScalingGroupListOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

/**
 *
 * @author Woo Cubic
 * @date 2017-06-06 20:40:10
 */
public class ASSample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(ASSample.class);

	@Test
	public void testCreateAutoScalingGroup() {
		IdResourceEntity network = new IdResourceEntity();
		network.setId("d2c9712f-84a8-4511-bebf-ec6eac62daf8");

		IdResourceEntity securityGroup = new IdResourceEntity();
		securityGroup.setId("0005ba27-b937-4a7c-a280-c7b65cea2e47");

		ASAutoScalingGroupCreate group = ASAutoScalingGroupCreate.builder()
				.groupName("test-4-bill")
				.vpcId("31d158b8-e7d7-4b4a-b2a7-a5240296b267")
				.networks(Lists.newArrayList(network))
				.securityGroups(Lists.newArrayList(securityGroup))
				.build();

		ScalingGroupCreate result = osclient.autoScaling().groups().create(group);
		logger.info("{}", result);
		Assert.assertNotNull(result.groupId());
	}

	@Test
	public void testListAutoScalingGroups() {
		List<? extends ScalingGroup> list = osclient.autoScaling().groups().list(ScalingGroupListOptions.create().groupName("test-4-bill").limit(5).startNumber(1));
		logger.info("{}", list);
		Assert.assertTrue(!list.isEmpty());
		Assert.assertEquals(list.get(0).groupName(), "test-4-bill");
		list = osclient.autoScaling().groups().list(ScalingGroupListOptions.create().groupName("$&"));
		logger.info("{}", list);
		Assert.assertTrue(list.isEmpty());
		list = osclient.autoScaling().groups().list();
		logger.info("{}", list);
		Assert.assertTrue(!list.isEmpty());
	}

	@Test
	public void testGetAutoScalingGroup() {
		ScalingGroup group = osclient.autoScaling().groups().get("9d841f24-755a-4706-ba1a-11fcd27d5891");
		logger.info("{}", group);
		Assert.assertNotNull(group);
		Assert.assertEquals(group.groupName(), "as-group-349s");
	}
	
	@Test
	public void testUpdateAutoScalingGroup() {
		String groupId = "99beebd6-3357-4a56-a74a-045c6eae5580";
		ScalingGroup group = osclient.autoScaling().groups().get(groupId);
		Assert.assertNotNull(group);

		String before = group.groupName();
		String after = new StringBuilder(before).reverse().toString();
		ScalingGroupUpdate result = osclient.autoScaling().groups().update(group.groupId(), ASAutoScalingGroupUpdate.fromScalingGroup(group).toBuilder().groupName(after).build());
		Assert.assertNotNull(result.groupId());
		
		ScalingGroup afterUpdate = osclient.autoScaling().groups().get(groupId);
		Assert.assertEquals(afterUpdate.groupName(), after);
	}
	
	@Test
	public void testDeleteAutoScalingGroup() {
		IdResourceEntity network = new IdResourceEntity();
		network.setId("d2c9712f-84a8-4511-bebf-ec6eac62daf8");

		IdResourceEntity securityGroup = new IdResourceEntity();
		securityGroup.setId("0005ba27-b937-4a7c-a280-c7b65cea2e47");

		ASAutoScalingGroupCreate group = ASAutoScalingGroupCreate.builder()
				.groupName("test-4-bill")
				.vpcId("31d158b8-e7d7-4b4a-b2a7-a5240296b267")
				.networks(Lists.newArrayList(network))
				.securityGroups(Lists.newArrayList(securityGroup))
				.build();

		ScalingGroupCreate result = osclient.autoScaling().groups().create(group);
		Assert.assertNotNull(result.groupId());
		
		ActionResponse resp = osclient.autoScaling().groups().delete(result.groupId());
		Assert.assertTrue(resp.isSuccess(), resp.getFault());
		
		ScalingGroup deletedGroup = osclient.autoScaling().groups().get(result.groupId());
		Assert.assertNull(deletedGroup);
	}
	
	@Test
	public void testActionAotoScalingGroup() {
		//TODO re-test this after scaling group configuration api finish
		assertTrue(false, "no scaling configuration");
		
		String resumeGroupStatus = "INSERVICE";
		String pauseGroupStatus = "PAUSED";
		
		List<? extends ScalingGroup> list = osclient.autoScaling().groups().list();
		Assert.assertTrue(!list.isEmpty(), "No group exits");
		
		String groupId = "9d841f24-755a-4706-ba1a-11fcd27d5891";
		ActionResponse resp = osclient.autoScaling().groups().operate(groupId, new Resume());
		Assert.assertTrue(resp.isSuccess(), resp.getFault());
		
		ScalingGroup group = osclient.autoScaling().groups().get(groupId);
		Assert.assertEquals(group.groupStatus(), resumeGroupStatus);
		
		resp = osclient.autoScaling().groups().operate(groupId, new Pause());
		Assert.assertTrue(resp.isSuccess(), resp.getFault());
		
		group = osclient.autoScaling().groups().get(groupId);
		Assert.assertEquals(group.groupStatus(), pauseGroupStatus);
	}
}

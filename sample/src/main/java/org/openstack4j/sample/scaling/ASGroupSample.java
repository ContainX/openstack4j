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
package org.openstack4j.sample.scaling;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.compute.Keypair;
import org.openstack4j.model.scaling.Disk;
import org.openstack4j.model.scaling.InstanceConfig;
import org.openstack4j.model.scaling.ScalingConfig;
import org.openstack4j.model.scaling.ScalingConfigCreate;
import org.openstack4j.model.scaling.ScalingGroup;
import org.openstack4j.model.scaling.ScalingGroupCreate;
import org.openstack4j.model.scaling.ScalingGroupUpdate;
import org.openstack4j.openstack.common.IdResourceEntity;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingConfigCreate;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupCreate;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupUpdate;
import org.openstack4j.openstack.scaling.options.ScalingConfigListOptions;
import org.openstack4j.openstack.scaling.options.ScalingGroupListOptions;
import org.openstack4j.sample.AbstractSample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.beust.jcommander.internal.Maps;
import com.google.common.collect.Lists;

/**
 *
 * @author Woo Cubic
 * @date 2017-06-06 20:40:10
 */
public class ASGroupSample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(ASGroupSample.class);

	@Test
	public void testCreateAutoScalingGroup() {
		String groupId = createScalingGroup();
		logger.info(groupId);
	}

	@Test
	public void testListAutoScalingGroups() {
		List<? extends ScalingGroup> list = osclient.autoScaling().groups()
				.list(ScalingGroupListOptions.create().groupName("test-4-bill").limit(5).startNumber(1));
		logger.info("{}", list);
		Assert.assertTrue(!list.isEmpty());
		Assert.assertEquals(list.get(0).getGroupName(), "test-4-bill");
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
		Assert.assertEquals(group.getGroupName(), "as-group-349s");
	}

	@Test
	public void testUpdateAutoScalingGroup() {
		String groupId = "8f633489-ec05-4a22-b8b5-2fa1006f6b60";
		ScalingGroup group = osclient.autoScaling().groups().get(groupId);
		Assert.assertNotNull(group);

		String before = group.getGroupName();
		String after = new StringBuilder(before).reverse().toString();
		ScalingGroupUpdate result = osclient.autoScaling().groups().update(group.getGroupId(),
				ASAutoScalingGroupUpdate.fromScalingGroup(group).toBuilder().groupName(after).maxInstanceNumber(2)
						.minInstanceNumber(0).desireInstanceNumber(1).build());
		Assert.assertNotNull(result.getGroupId());

		ScalingGroup afterUpdate = osclient.autoScaling().groups().get(groupId);
		Assert.assertEquals(afterUpdate.getGroupName(), after);
	}

	@Test
	public void testDeleteAutoScalingGroup() {
		String groupId = createScalingGroup();

		ActionResponse resp = osclient.autoScaling().groups().delete(groupId);
		Assert.assertTrue(resp.isSuccess(), resp.getFault());

		ScalingGroup deletedGroup = osclient.autoScaling().groups().get(groupId);
		Assert.assertNull(deletedGroup);
	}

	@Test
	public void testOperateAutoScalingGroup() {
		String resumeGroupStatus = "INSERVICE";
		String pauseGroupStatus = "PAUSED";

		String groupId = createScalingGroup();
		ActionResponse resp = osclient.autoScaling().groups().resume(groupId);
		Assert.assertTrue(resp.isSuccess(), resp.getFault());

		ScalingGroup group = osclient.autoScaling().groups().get(groupId);
		Assert.assertEquals(group.getGroupStatus(), resumeGroupStatus);

		resp = osclient.autoScaling().groups().pause(groupId);
		Assert.assertTrue(resp.isSuccess(), resp.getFault());

		group = osclient.autoScaling().groups().get(groupId);
		Assert.assertEquals(group.getGroupStatus(), pauseGroupStatus);

		resp = osclient.autoScaling().groups().delete(groupId);
		Assert.assertTrue(resp.isSuccess(), resp.getFault());
	}

	@Test
	public void testCreateAutoScalingConfig() {
		String configId = createScalingConfig();
		logger.info("{}", configId);
		assertNotNull(configId);
	}

	@Test
	public void testListAutoScalingConfig() {
		List<? extends ScalingConfig> all = osclient.autoScaling().configs().list();
		logger.info("{}", all);

		createScalingConfig();
		ScalingConfigListOptions options = ScalingConfigListOptions.create().configName("test-config-name");
		List<? extends ScalingConfig> list = osclient.autoScaling().configs().list(options);
		logger.info("{}", list);
		assertTrue(!list.isEmpty());
		for (ScalingConfig config : list) {
			assertEquals(config.getConfigName(), "test-config-name");
		}
	}

	@Test
	public void testGetAutoScalingConfig() {
		String configId = createScalingConfig();

		ScalingConfigCreate newCfg = osclient.autoScaling().configs().get(configId);
		assertEquals(newCfg.getConfigName(), "test-config-name");
	}

	@Test
	public void testDeleteAutoScalingConfig() {
		String configId = createScalingConfig();
		ActionResponse resp = osclient.autoScaling().configs().delete(configId);
		assertTrue(resp.isSuccess(), resp.getFault());
		ScalingConfigCreate config = osclient.autoScaling().configs().get(configId);
		assertNull(config);

		ArrayList<String> configIds = Lists.newArrayList();
		for (int i = 0; i < 5; i++) {
			configIds.add(createScalingConfig());
		}
		resp = osclient.autoScaling().configs().delete(configIds);
		assertTrue(resp.isSuccess(), resp.getFault());
		for (String cid : configIds) {
			assertNull(osclient.autoScaling().configs().get(cid));
		}
	}

	/**
	 * create scaling group
	 * 
	 * @return group id
	 */
	private String createScalingGroup() {
		IdResourceEntity network = new IdResourceEntity();
		network.setId("d2c9712f-84a8-4511-bebf-ec6eac62daf8");

		IdResourceEntity securityGroup = new IdResourceEntity();
		securityGroup.setId("0005ba27-b937-4a7c-a280-c7b65cea2e47");

		ASAutoScalingGroupCreate group = ASAutoScalingGroupCreate.builder().groupName("test-4-bill")
				.vpcId("31d158b8-e7d7-4b4a-b2a7-a5240296b267").networks(Lists.newArrayList(network))
				.configId("21114dcf-7d42-4c7b-84b8-35983da4fccb").securityGroups(Lists.newArrayList(securityGroup))
				.maxInstanceNumber(2).minInstanceNumber(1).desireInstanceNumber(1).build();

		ScalingGroupCreate result = osclient.autoScaling().groups().create(group);
		Assert.assertNotNull(result.getGroupId());
		return result.getGroupId();
	}

	/**
	 * create scaling configuration
	 * 
	 * @return configuration id
	 */
	private String createScalingConfig() {
		String keyname = "KeyPair-28ice";
		Keypair keypair = osclient.compute().keypairs().get(keyname);
		if (keypair == null) {
			osclient.compute().keypairs().create(keyname, null);
		}

		Map<String, String> metaData = Maps.newHashMap();
		metaData.put("key1", "val1");
		metaData.put("key2", "val2");
		Disk disk = Disk.builder().size(40).volumeType("SATA").diskType("SYS").build();
		InstanceConfig instanceConfig = InstanceConfig.builder().flavorRef("computev1-1")
				.imageRef("cb6ad86a-f69e-4a36-b65b-1038b19e15d3").disks(Lists.newArrayList(disk)).keyName(keyname)
				.metadata(metaData).build();
		ScalingConfigCreate config = ASAutoScalingConfigCreate.builder().configName("test-config-name")
				.instanceConfig(instanceConfig).build();

		ScalingConfigCreate result = osclient.autoScaling().configs().create(config);
		assertNotNull(result.getConfigId());
		return result.getConfigId();
	}
}

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
package com.huawei.openstack4j.api.scaling;

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.core.transport.ObjectMapperSingleton;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.scaling.ScalingGroup;
import com.huawei.openstack4j.model.scaling.ScalingGroupCreate;
import com.huawei.openstack4j.model.scaling.ScalingGroupUpdate;
import com.huawei.openstack4j.model.scaling.ScalingGroup.HealthPeriodicAuditMethod;
import com.huawei.openstack4j.model.scaling.ScalingGroup.InstanceTerminatePolicy;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingGroupCreate;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingGroupUpdate;

import com.google.common.collect.Lists;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "AutoScaling/AutoScalingGroupV1")
public class AutoScalingGroupV1Tests extends AbstractTest {
	
	private static final String JSON_SCALING_GROUP_LIST = "/scaling/as_scaling_group_list.json";
	private static final String JSON_SCALING_GROUP = "/scaling/as_scaling_group.json";
	private static final String JSON_SCALING_GROUP_CREATE = "/scaling/as_scaling_group_create.json";
	private static final String JSON_SCALING_GROUP_UPDATE = "/scaling/as_scaling_group_update.json";

	@Test(priority = 1)
	public void testCreateAutoScalingGroup() throws IOException, InterruptedException {
		respondWith(JSON_SCALING_GROUP_CREATE);
		IdResourceEntity network = new IdResourceEntity();
		network.setId("d2c9712f-84a8-4511-bebf-ec6eac62daf8");

		IdResourceEntity securityGroup = new IdResourceEntity();
		securityGroup.setId("0005ba27-b937-4a7c-a280-c7b65cea2e47");

		ASAutoScalingGroupCreate group = ASAutoScalingGroupCreate.builder().groupName("test-4-bill")
				.vpcId("31d158b8-e7d7-4b4a-b2a7-a5240296b267").networks(Lists.newArrayList(network))
				.securityGroups(Lists.newArrayList(securityGroup))
				.healthPeriodicAuditMethod(HealthPeriodicAuditMethod.ELB_AUDIT)
				.instanceTerminatePolicy(InstanceTerminatePolicy.NEW_INSTANCE)
				.availabilityZones(Lists.newArrayList("eu-de")).build();
		
		ScalingGroupCreate result = osv3().autoScaling().groups().create(group);

		RecordedRequest request = server.takeRequest();
		assertTrue(request.getPath().equals("/v1/project-id/scaling_group"));
		assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode response = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		assertEquals("test-4-bill", response.get("scaling_group_name").asText());
		assertEquals("31d158b8-e7d7-4b4a-b2a7-a5240296b267", response.get("vpc_id").asText());
		assertTrue(response.get("available_zones").isArray());
		assertEquals("eu-de", response.get("available_zones").get(0).asText());

		
		assertEquals(result.getGroupId(), "613baa6b-32c5-4052-9cb1-45f9a22b2579");
	}

	@Test(priority = 2)
	public void testsListAutoScalingGroups() throws IOException {
		respondWith(JSON_SCALING_GROUP_LIST);
		List<? extends ScalingGroup> list = osv3().autoScaling().groups().list();
		assertEquals(list.size(), 6);
		assertEquals(list.get(0).getGroupName(), "test-4-bill");
	}

	@Test(priority = 3)
	public void testGetAutoScalingGroup() throws IOException {
		respondWith(JSON_SCALING_GROUP);
		ScalingGroup group = osv3().autoScaling().groups().get("9d841f24-755a-4706-ba1a-11fcd27d5891");
		assertEquals(group.getGroupName(), "as-group-349s");
	}

	@Test(priority = 4)
	public void testUpdateAutoScalingGroup() throws IOException {
		respondWith(JSON_SCALING_GROUP);
		ScalingGroup group = osv3().autoScaling().groups().get("9d841f24-755a-4706-ba1a-11fcd27d5891");
		assertEquals(group.getGroupName(), "as-group-349s");

		respondWith(JSON_SCALING_GROUP_UPDATE);
		ScalingGroupUpdate result = osv3().autoScaling().groups().update(group.getGroupId(),
				ASAutoScalingGroupUpdate.fromScalingGroup(group).toBuilder().groupName("groupNameUpdate").build());
		assertEquals(result.getGroupId(), group.getGroupId());
	}

	@Test(priority = 5)
	public void testDeleteAutoScalingGroup() {
		respondWith(204);
		ActionResponse resp = osv3().autoScaling().groups().delete("9d841f24-755a-4706-ba1a-11fcd27d5891");
		assertTrue(resp.isSuccess());
	}

	@Test(priority = 6)
	public void testActionAutoScalingGroup() {
		respondWith(204);
		respondWith(204);
		String groupId = "9d841f24-755a-4706-ba1a-11fcd27d5891";
		ActionResponse resp = osv3().autoScaling().groups().resume(groupId);
		assertTrue(resp.isSuccess());

		resp = osv3().autoScaling().groups().pause(groupId);
		assertTrue(resp.isSuccess());
	}

	@Override
	protected Service service() {
		return Service.AUTO_SCALING;
	}
}

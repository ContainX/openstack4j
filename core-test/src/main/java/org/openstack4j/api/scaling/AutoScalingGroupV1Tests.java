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
package org.openstack4j.api.scaling;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.ScalingGroup;
import org.openstack4j.model.scaling.ScalingGroupCreate;
import org.openstack4j.model.scaling.ScalingGroupUpdate;
import org.openstack4j.openstack.common.IdResourceEntity;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupCreate;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupUpdate;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

@Test(suiteName="AutoScaling/AutoScalingGroupV1", enabled = true)
public class AutoScalingGroupV1Tests extends AbstractTest {
	private static final String JSON_SCALING_GROUP_LIST = "/scaling/as_scaling_group_list.json";
	private static final String JSON_SCALING_GROUP = "/scaling/as_scaling_group.json";
	private static final String JSON_SCALING_GROUP_CREATE = "/scaling/as_scaling_group_create.json";
	private static final String JSON_SCALING_GROUP_UPDATE = "/scaling/as_scaling_group_update.json";
	
	public void testCreateAutoScalingGroup() throws IOException {
		respondWith(JSON_SCALING_GROUP_CREATE);
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

		ScalingGroupCreate result = osv3().autoScaling().groups().create(group);
		assertEquals(result.getGroupId(), "613baa6b-32c5-4052-9cb1-45f9a22b2579");
	}
	
    public void testsListAutoScalingGroups() throws IOException {
		respondWith(JSON_SCALING_GROUP_LIST);
        List<? extends ScalingGroup> list = osv3().autoScaling().groups().list();
        assertEquals(list.size(), 6);
        assertEquals(list.get(0).getGroupName(), "test-4-bill");
    }
    
    public void testGetAutoScalingGroup() throws IOException {
		respondWith(JSON_SCALING_GROUP);
    	ScalingGroup group = osv3().autoScaling().groups().get("9d841f24-755a-4706-ba1a-11fcd27d5891");
    	assertEquals(group.getGroupName(), "as-group-349s");
    }
    
    public void testUpdateAutoScalingGroup() throws IOException {
    	respondWith(JSON_SCALING_GROUP);
    	ScalingGroup group = osv3().autoScaling().groups().get("9d841f24-755a-4706-ba1a-11fcd27d5891");
    	assertEquals(group.getGroupName(), "as-group-349s");
    	
    	respondWith(JSON_SCALING_GROUP_UPDATE);
    	ScalingGroupUpdate result = osv3().autoScaling().groups().update(group.getGroupId(), ASAutoScalingGroupUpdate.fromScalingGroup(group).toBuilder().groupName("groupNameUpdate").build());
    	assertEquals(result.getGroupId(), group.getGroupId());
    }
    
    public void testDeleteAutoScalingGroup() {
    	respondWith(204);
    	ActionResponse resp = osv3().autoScaling().groups().delete("9d841f24-755a-4706-ba1a-11fcd27d5891");
    	assertTrue(resp.isSuccess());
    }
    
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

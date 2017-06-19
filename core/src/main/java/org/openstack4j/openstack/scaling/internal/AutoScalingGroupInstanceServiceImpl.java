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
package org.openstack4j.openstack.scaling.internal;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.openstack4j.api.scaling.AutoScalingGroupInstanceService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.ScalingGroupInstance;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupInstance.ASAutoScalingGroupInstances;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupInstanceBatch;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupInstanceBatch.Action;
import org.openstack4j.openstack.scaling.options.ScalingGroupInstanceListOptions;

import com.google.common.base.Strings;

public class AutoScalingGroupInstanceServiceImpl extends BaseAutoScalingServices
		implements AutoScalingGroupInstanceService {

	@Override
	public List<? extends ScalingGroupInstance> list(String groupId) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "groupId is required");
		return get(ASAutoScalingGroupInstances.class, uri("/scaling_group_instance/%s/list", groupId)).execute()
				.getList();
	}

	@Override
	public List<? extends ScalingGroupInstance> list(String groupId, ScalingGroupInstanceListOptions options) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "groupId is required");
		return get(ASAutoScalingGroupInstances.class, uri("/scaling_group_instance/%s/list", groupId))
				.params(options.getOptions()).execute().getList();
	}

	@Override
	public ActionResponse delete(String instanceId, boolean deleteInstance) {
		checkArgument(!Strings.isNullOrEmpty(instanceId), "instanceId is required");
		String yesOrNo = deleteInstance ? "yes" : "no";
		return deleteWithResponse(uri("/scaling_group_instance/%s?instance_delete=%s", instanceId, yesOrNo)).execute();
	}

	@Override
	public ActionResponse batchOperate(String groupId, List<String> instanceIds, boolean deleteInstance,
			Action action) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "groupId is required");
		String yesOrNo = deleteInstance ? "yes" : "no";
		ASAutoScalingGroupInstanceBatch entity = ASAutoScalingGroupInstanceBatch.builder().instanceIds(instanceIds)
				.delete(yesOrNo).action(action.name()).build();
		return post(ActionResponse.class, uri("/scaling_group_instance/%s/action", groupId)).entity(entity).execute();
	}
}

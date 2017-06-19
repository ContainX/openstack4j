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

import org.openstack4j.api.scaling.AutoScalingActivityLogService;
import org.openstack4j.model.scaling.ScalingActivityLog;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingActivityLog.ASAutoScalingActivityLogs;
import org.openstack4j.openstack.scaling.options.ScalingActivityLogListOptions;

import com.google.common.base.Strings;

public class AutoScalingActivityLogServiceImpl extends BaseAutoScalingServices
		implements AutoScalingActivityLogService {

	@Override
	public List<? extends ScalingActivityLog> list(String groupId) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "groupId required");
		return get(ASAutoScalingActivityLogs.class, uri("/scaling_activity_log/%s", groupId)).execute().getList();
	}

	@Override
	public List<? extends ScalingActivityLog> list(String groupId, ScalingActivityLogListOptions options) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "groupId required");
		checkArgument(options != null, "options required");
		return get(ASAutoScalingActivityLogs.class, uri("/scaling_activity_log/%s", groupId))
				.params(options.getOptions()).execute().getList();
	}

}

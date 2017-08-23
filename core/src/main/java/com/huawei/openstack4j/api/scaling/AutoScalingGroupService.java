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
package com.huawei.openstack4j.api.scaling;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.scaling.ScalingGroup;
import com.huawei.openstack4j.model.scaling.ScalingGroupCreate;
import com.huawei.openstack4j.model.scaling.ScalingGroupUpdate;
import com.huawei.openstack4j.openstack.scaling.options.ScalingGroupListOptions;

public interface AutoScalingGroupService extends RestService {
	
	public ScalingGroupCreate create(ScalingGroupCreate group); 

	public List<? extends ScalingGroup> list(ScalingGroupListOptions options);
	
	public List<? extends ScalingGroup> list();
	
	public ScalingGroup get(String groupId);
	
	public ScalingGroupUpdate update(String groupId, ScalingGroupUpdate group);
	
	public ActionResponse delete(String groupId);
	
	public ActionResponse resume(String groupId);
	
	public ActionResponse pause(String groupId);
}

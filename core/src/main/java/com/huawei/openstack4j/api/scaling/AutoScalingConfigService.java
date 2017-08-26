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

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.scaling.ScalingConfig;
import com.huawei.openstack4j.model.scaling.ScalingConfigCreate;
import com.huawei.openstack4j.openstack.scaling.options.ScalingConfigListOptions;

public interface AutoScalingConfigService extends RestService {
	
	/**
	 * Create a new auto scaling configuration
	 * 
	 * @param config	model represent the attributes of auto scaling configuration creation
	 * @return the created auto-scaling config identifier
	 */
	public String create(ScalingConfigCreate config);
	
	public List<? extends ScalingConfig> list(ScalingConfigListOptions options);
	
	public List<? extends ScalingConfig> list();
	
	public ScalingConfig get(String configId);
	
	public ActionResponse delete(String configId);
	
	public ActionResponse delete(List<String> configIds);
}

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
package com.huawei.openstack4j.openstack.scaling.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.scaling.InstanceConfig;
import com.huawei.openstack4j.model.scaling.ScalingConfig;
import com.huawei.openstack4j.openstack.common.DateTimeUtils;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("scaling_configuration")
public class ASAutoScalingConfig implements ScalingConfig {

	private static final long serialVersionUID = -8356492591040412347L;
	
	@JsonProperty("scaling_configuration_id")
	private String configId;
	
	@JsonProperty("scaling_configuration_name")
	private String configName;
	
	@JsonProperty("instance_config")
	private InstanceConfig instanceConfig;
	
	@JsonProperty
	private String tenant;
	
	@JsonProperty("create_time")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMSZ)
	private Date createTime;

	public static class ASAutoScalingConfigs extends ListResult<ASAutoScalingConfig> {

		private static final long serialVersionUID = -1873594481944340934L;
		
		@JsonProperty("scaling_configurations")
		private List<ASAutoScalingConfig> configs;

		@Override
		protected List<ASAutoScalingConfig> value() {
			return configs;
		}
	}
}

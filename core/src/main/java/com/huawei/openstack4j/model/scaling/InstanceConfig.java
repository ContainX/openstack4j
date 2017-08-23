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
package com.huawei.openstack4j.model.scaling;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstanceConfig implements ModelEntity {

	private static final long serialVersionUID = 3766509894708168472L;

	@JsonProperty("instance_id")
	private String instanceId;

	@JsonProperty("instance_name")
	private String instanceName;

	@JsonProperty
	private String flavorRef;

	@JsonProperty
	private String imageRef;

	@JsonProperty("disk")
	private List<Disk> disks;

	@JsonProperty("key_name")
	private String keyName;

	@JsonProperty
	private String adminPass;

	@JsonProperty
	private List<Personality> personality;

	@JsonProperty("public_ip")
	private PublicIp publicIp;

	@JsonProperty
	private Map<String, String> metadata;

	@JsonProperty("user_data")
	private String userData;
}

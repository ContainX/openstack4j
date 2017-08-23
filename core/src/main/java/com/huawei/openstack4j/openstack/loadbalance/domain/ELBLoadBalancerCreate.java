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
package com.huawei.openstack4j.openstack.loadbalance.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.loadbalance.LoadBalancerCreate;
import com.huawei.openstack4j.model.loadbalance.LoadBalancer.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ELBLoadBalancerCreate implements LoadBalancerCreate {

	private static final long serialVersionUID = 742286551840027186L;

	@JsonProperty
	private String name;

	@JsonProperty
	private String description;

	@JsonProperty("vpc_id")
	private String vpcId;

	@JsonProperty
	private Integer bandwidth;

	@JsonProperty
	private Type type;

	@JsonProperty("admin_state_up")
	private Integer adminStateUp;

	@JsonProperty("vip_subnet_id")
	private String vipSubnetId;

	@JsonProperty("az")
	private String azId;

	@JsonProperty("charge_mode")
	private ChargeMode chargeMode;

	@JsonProperty("eip_type")
	private EipType eipType;

	@JsonProperty("security_group_id")
	private String securityGroupId;

	@JsonProperty("vip_address")
	private String vipAddress;

	@JsonProperty
	private String tenantId;
}

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
package org.openstack4j.openstack.loadbalance.domain;

import java.util.Date;
import java.util.List;

import org.openstack4j.model.loadbalance.LoadBalancer;
import org.openstack4j.openstack.common.DateTimeUtils;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class ELBLoadBalancer implements LoadBalancer {

	private static final long serialVersionUID = 742286551840027186L;

	@JsonProperty
	private String id;
	
	@JsonProperty
	private String name;

	@JsonProperty
	private String description;

	@JsonProperty("vpc_id")
	private String vpcId;

	@JsonProperty
	private Integer bandwidth;

	@JsonProperty
	private String type;

	@JsonProperty("admin_state_up")
	private Integer adminStateUp;

	@JsonProperty("vip_subnet_id")
	private String vipSubnetId;

	@JsonProperty("security_group_id")
	private String securityGroupId;

	@JsonProperty("vip_address")
	private String vipAddress;
	
	@JsonProperty
	private String status;
	
	@JsonProperty("create_time")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDHMS)
	private Date createTime;
	
	@JsonProperty("update_time")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDHMS)
	private Date updateTime;
	
	public static class ELBLoadBalancers extends ListResult<ELBLoadBalancer> {
		private static final long serialVersionUID = -397487098967183828L;
		
		@JsonProperty("loadbalancers")
		private List<ELBLoadBalancer> loadBalancers;
		
		@Override
		protected List<ELBLoadBalancer> value() {
			return loadBalancers;
		}
		
	}
}

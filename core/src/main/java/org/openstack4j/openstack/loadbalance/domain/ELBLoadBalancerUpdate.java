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

import org.openstack4j.model.loadbalance.LoadBalancer;
import org.openstack4j.model.loadbalance.LoadBalancerUpdate;

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
public class ELBLoadBalancerUpdate implements LoadBalancerUpdate {

	private static final long serialVersionUID = 742286551840027186L;
	
	@JsonProperty
	private String name;

	@JsonProperty
	private String description;

	@JsonProperty
	private Integer bandwidth;

	@JsonProperty("admin_state_up")
	private Integer adminStateUp;

	@JsonProperty
	private String tenantId;
	
	public static ELBLoadBalancerUpdate fromLoadBalancer(LoadBalancer loadBalancer) {
		return ELBLoadBalancerUpdate.builder()
					.name(loadBalancer.getName())
					.description(loadBalancer.getDescription())
					.bandwidth(loadBalancer.getBandwidth())
					.adminStateUp(loadBalancer.getAdminStateUp())
					.build();
	}
}

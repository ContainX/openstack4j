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

import org.openstack4j.model.loadbalance.HealthCheckUpdate;

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
public class ELBHealthCheckUpdate implements HealthCheckUpdate {

	private static final long serialVersionUID = 3164457385221233948L;

	@JsonProperty("healthcheck_id")
	private String healthCheckId;

	@JsonProperty("healthcheck_protocol")
	private String healthCheckProtocol;
	
	@JsonProperty("healthcheck_uri")
	private String healthCheckUri;
	
	@JsonProperty("healthcheck_connect_port")
	private Integer healthCheckConnectPort;
	
	@JsonProperty("healthy_threshold")
	private Integer healthyThreshold;
	
	@JsonProperty("unhealthy_threshold")
	private Integer unhealthyThreshold;
	
	@JsonProperty("healthcheck_timeout")
	private Integer healthCheckTimeout;
	
	@JsonProperty("healthcheck_interval")
	private Integer healthCheckInterval;
}

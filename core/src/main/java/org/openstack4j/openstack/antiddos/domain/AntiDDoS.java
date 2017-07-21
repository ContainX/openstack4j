/*******************************************************************************
 * 	Copyright 2017 HuaWei TLD and OTC                                          
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
package org.openstack4j.openstack.antiddos.domain;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.antiddos.constants.AppType;
import org.openstack4j.openstack.antiddos.constants.CleaningAccessPos;
import org.openstack4j.openstack.antiddos.constants.HttpRequestPos;
import org.openstack4j.openstack.antiddos.constants.TrafficPos;

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
public class AntiDDoS implements ModelEntity {

	private static final long serialVersionUID = -1475286444132857293L;
	
	@JsonProperty("enable_L7")
	private Boolean enableL7;
	
	@JsonProperty("traffic_pos_id")
	private TrafficPos trafficPos;
	
	@JsonProperty("http_request_pos_id")
	private HttpRequestPos httpRequestPos;
	
	@JsonProperty("cleaning_access_pos_id")
	private CleaningAccessPos cleaningAccessPos;
	
	@JsonProperty("app_type_id")
	private AppType appType;
}

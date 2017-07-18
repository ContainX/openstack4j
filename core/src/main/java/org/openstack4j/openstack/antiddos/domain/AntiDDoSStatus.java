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

import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.antiddos.constants.NetworkType;
import org.openstack4j.openstack.antiddos.constants.Status;
import org.openstack4j.openstack.common.ListResult;

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
public class AntiDDoSStatus implements ModelEntity {

	private static final long serialVersionUID = -2054296701363380933L;

	/**
	 * elastic ip number
	 */
	private Integer total;

	/**
	 * ddos status
	 */
	private List<DDoSStatus> ddosStatus;

	@Getter
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	private class DDoSStatus {

		/**
		 * floating ip address
		 */
		@JsonProperty("floating_ip_address")
		private String floatingIpAddress;

		/**
		 * floating ip id
		 */
		@JsonProperty("floating_ip_id")
		private String floatingIpId;

		/**
		 * network type
		 */
		@JsonProperty("network_type")
		private NetworkType networkType;

		/**
		 * status
		 */
		private Status status;
	}
	
	public class AntiDDoSStatuses extends ListResult<AntiDDoSStatus> {

		private static final long serialVersionUID = 6346118253981717289L;

		@JsonProperty("ddosStatus")
		private List<AntiDDoSStatus> statuses;
		
		@Override
		protected List<AntiDDoSStatus> value() {
			return statuses;
		}
	}
}

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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.ModelEntity;

import com.google.common.base.Strings;

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
public class Eip implements ModelEntity {

	private static final long serialVersionUID = -1069053200085079737L;

	public enum IpType {
		TELCOM5("5_telcom"), BGP5("5_bgp"), LXBGP5("5_lxbgp"), UNION5("5_union");

		private String val;

		private IpType(String val) {
			this.val = val;
		}

		@JsonValue
		public String getVal() {
			return this.val;
		}

		@JsonCreator
		public IpType forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (IpType type : IpType.values()) {
					if (type.getVal().equalsIgnoreCase(value)) {
						return type;
					}
				}
			}
			return null;
		}
	}

	@JsonProperty("ip_type")
	private IpType ipType;

	@JsonProperty
	private Bandwidth bandwidth;
}

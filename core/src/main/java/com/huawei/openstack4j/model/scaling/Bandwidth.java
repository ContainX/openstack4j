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

import com.google.common.base.Strings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.ModelEntity;

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
public class Bandwidth implements ModelEntity {

	private static final long serialVersionUID = -1154665269978660693L;

	public enum ShareType {
		PER;

		@JsonCreator
		public ShareType forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (ShareType type : ShareType.values()) {
					if (type.name().equalsIgnoreCase(value)) {
						return type;
					}
				}
			}
			return null;
		}
	}

	public enum ChargingMode {
		TRAFFIC("traffic"), BANDWIDTH("bandwidth");

		private String val;

		private ChargingMode(String val) {
			this.val = val;
		}

		@JsonValue
		public String getVal() {
			return this.val;
		}

		@JsonCreator
		public ChargingMode forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (ChargingMode mode : ChargingMode.values()) {
					if (mode.getVal().equalsIgnoreCase(value)) {
						return mode;
					}
				}
			}
			return null;
		}
	}

	@JsonProperty
	private String size;

	@JsonProperty("share_type")
	private ShareType shareType;

	@JsonProperty("charging_mode")
	private ChargingMode chargingMode;
}

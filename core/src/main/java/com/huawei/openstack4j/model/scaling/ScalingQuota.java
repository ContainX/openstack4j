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
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.ModelEntity;

import com.google.common.base.Strings;

public interface ScalingQuota extends ModelEntity {

	public enum Type {
		SCALING_GROUP("scaling_Group"), SCALING_CONFIG("scaling_Config"), SCALING_POLICY(
				"scaling_Policy"), SCALING_INSTANCE("scaling_Instance");

		private String val;

		private Type(String val) {
			this.val = val;
		}

		@JsonValue
		public String getVal() {
			return this.val;
		}

		@JsonCreator
		public Type forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (Type type : Type.values()) {
					if (type.getVal().equalsIgnoreCase(value)) {
						return type;
					}
				}
			}
			return null;
		}
	}

	/**
	 * @return quota type
	 */
	Type getType();

	/**
	 * @return used number
	 */
	Integer getUsed();

	/**
	 * @return sum of quota
	 */
	Integer getQuota();

	/**
	 * @return max quota
	 */
	Integer getMax();
}

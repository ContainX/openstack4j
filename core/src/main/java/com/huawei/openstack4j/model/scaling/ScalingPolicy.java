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

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.base.Strings;

public interface ScalingPolicy extends ScalingPolicyCreateUpdate {
	public enum PolicyStatus {
		INSERVICE, PAUSED;

		@JsonCreator
		public PolicyStatus forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (PolicyStatus status : PolicyStatus.values()) {
					if (status.name().equalsIgnoreCase(value)) {
						return status;
					}
				}
			}
			return null;
		}
	}

	/**
	 * @return policy status
	 */
	PolicyStatus getPolicyStatus();

	/**
	 * create time of policy
	 * 
	 * @return
	 */
	Date getCreateTime();
}

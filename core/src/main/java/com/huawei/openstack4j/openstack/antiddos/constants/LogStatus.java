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
package com.huawei.openstack4j.openstack.antiddos.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LogStatus {
	/**
	 * Packet cleaning
	 */
	Status_1(1),
	/**
	 * Packet dropping
	 */
	Status_2(2),
	;
	
	private Integer val;
	
	@JsonValue
	public Integer getVal() {
		return val;
	}

	private LogStatus(Integer val) {
		this.val = val;
	}
	
	@JsonCreator
	public LogStatus forValue(Integer val) {
		if(val != null) {
			for (LogStatus status : LogStatus.values()) {
				if(val.equals(status.getVal())) {
					return status;
				}
			}
		}
		return null;
	}
}

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

public enum SendFrequency {
	FREQUENCY_0(0),
	FREQUENCY_1(1),
	;
	
	private Integer val;
	
	private SendFrequency(Integer val) {
		this.val = val;
	}

	@JsonValue
	public Integer getVal() {
		return val;
	}
	
	@JsonCreator
	public SendFrequency forValue(Integer val) {
		if(val != null) {
			for (SendFrequency freq : SendFrequency.values()) {
				if(val.equals(freq.getVal())) {
					return freq;
				}
			}
		}
		return null;
	}
	
}

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
package org.openstack4j.openstack.maas.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum State {
	INIT(0), WAIT(1), EXECUTE(2), STOP(3), FAILED(4), SUCCESS(5),;

	private Integer val;

	private State(Integer val) {
		this.val = val;
	}

	@JsonValue
	public Integer getVal() {
		return val;
	}

	@JsonCreator
	public static State forValue(Integer val) {
		if (val != null) {
			for (State state : State.values()) {
				if (val.equals(state.getVal())) {
					return state;
				}
			}
		}
		return null;
	}

}

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
package org.openstack4j.openstack.antiddos.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Access position
 */
public enum CleaningAccessPos {
	POS_1(1, 10, 30), POS_2(2, 20, 100), POS_3(3, 30, 200), POS_4(4, 40, 250), POS_5(5, 50, 300), POS_6(6, 60,
			500), POS_7(7, 70, 600), POS_8(8, 80, 700),;

	/**
	 * Access position id when cleaning
	 */
	private Integer id;

	/**
	 * new connection limited
	 */
	private Integer newConnectionLimited;

	/**
	 * total connection limited
	 */
	private Integer totalConnectionLimited;

	@JsonValue
	public Integer getId() {
		return id;
	}

	public Integer getNewConnectionLimited() {
		return newConnectionLimited;
	}

	public Integer getTotalConnectionLimited() {
		return totalConnectionLimited;
	}

	private CleaningAccessPos(Integer id, Integer newConnectionLimited, Integer totalConnectionLimited) {
		this.id = id;
		this.newConnectionLimited = newConnectionLimited;
		this.totalConnectionLimited = totalConnectionLimited;
	}

	@JsonCreator
	public static CleaningAccessPos forValue(Integer id) {
		if (id != null) {
			for (CleaningAccessPos pos : CleaningAccessPos.values()) {
				if (id.equals(pos.getId())) {
					return pos;
				}
			}
		}
		return null;
	}
}

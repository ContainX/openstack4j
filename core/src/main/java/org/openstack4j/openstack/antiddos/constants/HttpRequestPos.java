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
 * Anti-DDoS http request position
 */
public enum HttpRequestPos {
	POS_1(1, 100),
	POS_2(2, 150),
	POS_3(3, 240),
	POS_4(4, 350),
	POS_5(5, 480),
	POS_6(6, 550),
	POS_7(7, 700),
	POS_8(8, 850),
	POS_9(9, 1000),
	POS_10(10, 1500),
	POS_11(11, 2000),
	POS_12(12, 3000),
	POS_13(13, 5000),
	POS_14(14, 10000),
	POS_15(15, 20000),
	;
	
	/**
	 * http request position id
	 */
	private Integer id;
	
	/**
	 * packet per second
	 */
	private Integer packetPerSecond;
	
	@JsonValue
	public Integer getId() {
		return id;
	}

	public Integer getPacketPerSecond() {
		return packetPerSecond;
	}

	private HttpRequestPos(Integer id, Integer packetPerSecond) {
		this.id = id;
		this.packetPerSecond = packetPerSecond;
	}
	
	@JsonCreator
	public HttpRequestPos forValue(Integer id) {
		if(id != null) {
			for (HttpRequestPos pos : HttpRequestPos.values()) {
				if(id.equals(pos.getId())) {
					return pos;
				}
			}
		}
		
		return null;
	}
}

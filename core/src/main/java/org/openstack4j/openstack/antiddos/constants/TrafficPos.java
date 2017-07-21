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
 * Anti-DDoS traffic position 
 */
public enum TrafficPos {
	POS_1(1, 10, 2000),
	POS_2(2, 30, 6000),
	POS_3(3, 50, 10000),
	POS_4(4, 70, 15000),
	POS_5(5, 100, 20000),
	POS_6(6, 150, 25000),
	POS_7(7, 200, 35000),
	POS_8(8, 250, 50000),
	POS_9(9, 300, 70000),
	;
	
	/**
	 * traffic position id
	 */
	private Integer id;
	
	/**
	 * traffic per second(Mbit/s)
	 */
	private Integer trafficPerSecond;
	
	/**
	 * packet per second
	 */
	private Integer packetPerSecond;
	
	@JsonValue
	public Integer getId() {
		return id;
	}

	public Integer getTrafficPerSecond() {
		return trafficPerSecond;
	}

	public Integer getPacketPerSecond() {
		return packetPerSecond;
	}

	private TrafficPos(Integer id, Integer traficPerSecond, Integer packetPerSecond) {
		this.id = id;
		this.trafficPerSecond = traficPerSecond;
		this.packetPerSecond = packetPerSecond;
	}
	
	@JsonCreator
	public static TrafficPos forValue(Integer id) {
		if(id != null) {
			for (TrafficPos pos : TrafficPos.values()) {
				if(id.equals(pos.getId())) {
					return pos;
				}
			}
		}
		
		return null;
	}
}

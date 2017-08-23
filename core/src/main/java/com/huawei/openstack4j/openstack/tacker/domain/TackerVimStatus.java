/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package com.huawei.openstack4j.openstack.tacker.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * <p>The state of a Tacker (VIM) entity.</p>
 * 
 * <p>Indicates whether tacker vim resource is currently operational.</p>
 * 
 * @author Vishvesh Deshmukh
 * @date Aug 18, 2016
 */
public enum TackerVimStatus {
	REGISTERING,
	REACHABLE,
	UNREACHABLE,
	PENDING,
	ERROR,
	UNRECOGNIZED;
	  
	@JsonCreator
	public static TackerVimStatus forValue(String value) {
		if (value != null)
		{
			for (TackerVimStatus s : TackerVimStatus.values()) {
				if (s.name().equalsIgnoreCase(value))
					return s;
			}
		}
		return TackerVimStatus.UNRECOGNIZED;
	}
}
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
package com.huawei.openstack4j.model.dns.v2;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.huawei.openstack4j.model.ModelEntity;

/**
 * ReverseRecord(PTR) model
 */
public interface PTR extends ModelEntity {
	enum Status {
		ACTIVE, UNKNOWN;
		
		@JsonCreator
		public static Status forValue(String value) {
			if (value != null)
			{
				for (Status s : Status.values()) {
					if (s.name().equalsIgnoreCase(value))
						return s;
				}
			}
			return Status.UNKNOWN;
		}
	}

	String getId();
	String getPtrdname();
	String getDescription();
	Integer getTtl();
	String getAddress();
	Status getStatus();
	String getAction();
	Map<String, String> getLinks();
	String getRegion();
	String getFloatingIpId();

}

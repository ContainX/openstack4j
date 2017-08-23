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
package com.huawei.openstack4j.openstack.compute.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
/**
 * The JSON object used to update an existing aggregate
 * 
 * @author liujunpeng
 */
@JsonRootName("aggregate")
@JsonIgnoreProperties(ignoreUnknown=true)
public class NovaHostAggregateUpdate implements ModelEntity {

	private static final long serialVersionUID = 1L;
	@JsonProperty("availability_zone")
	public String availabilityZone;
	public String name;

	public NovaHostAggregateUpdate(String name,String availabilityZone){
		this.availabilityZone = availabilityZone;
		this.name=name;
		
	}

	public String getAvailabilityZone() {
		return availabilityZone;
	}

	public void setAvailabilityZone(String availabilityZone) {
		this.availabilityZone = availabilityZone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "NovaHostAggregateUpdate [availabilityZone=" + availabilityZone
				+ ", name=" + name + "]";
	}
		
}

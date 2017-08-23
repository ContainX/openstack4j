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
package com.huawei.openstack4j.openstack.heat.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.common.Link;
import com.huawei.openstack4j.model.heat.Resource;
import com.huawei.openstack4j.openstack.common.GenericLink;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * This is a model of a HeatResource. It uses Jackson annotations for
 * (de)serialization into JSON format
 * 
 * @author Octopus Zhang
 * 
 */
public class HeatResource implements Resource {
private static final long serialVersionUID = 1L;
	
	private List<GenericLink> links;
	@JsonProperty("updated_time")
	private Date time;
	@JsonProperty("resource_type")
	private String type;
	@JsonProperty("resource_status_reason")
	private String reason;
	@JsonProperty("resource_name")
	private String resourceName;
	@JsonProperty("logical_resource_id")
	private String localReourceId;
	@JsonProperty("resource_status")
	private String resourceStatus;
	@JsonProperty("physical_resource_id")
	private String physicalResourceId;
	@JsonProperty("required_by")
	private List<String> requiredBy;
	
	@Override
	public List<? extends Link> getLinks() {
		return links;
	}
	
	@Override
	public Date getTime() {
		return time;
	}
	
	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public String getReason() {
		return reason;
	}
	
	@Override
	public String getResourceName() {
		return resourceName;
	}
	
	@Override
	public String getLocalReourceId() {
		return localReourceId;
	}
	
	@Override
	public String getResourceStatus() {
		return resourceStatus;
	}
	
	@Override
	public String getPhysicalResourceId() {
		return physicalResourceId;
	}
	
	@Override
	public List<String> getRequiredBy() {
		return requiredBy;
	}
	
	/**
	 * An inner class for representing lists of Heat Resource
	 * 
	 * @author Octopus Zhang
	 * 
	 */
	public static class Resources extends ListResult<HeatResource> {
		private static final long serialVersionUID = 1L;
		
		@JsonProperty("resources")
		private List<HeatResource> list;

		protected List<HeatResource> value() {
			return list;
		}
	}
}

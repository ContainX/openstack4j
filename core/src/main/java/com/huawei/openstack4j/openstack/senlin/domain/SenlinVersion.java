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
package com.huawei.openstack4j.openstack.senlin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.senlin.Version;
import com.huawei.openstack4j.openstack.common.ListResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This is a model of a senlinVersion. It uses Jackson annotations for
 * (de)serialization into JSON format
 * 
 * @author lion
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("version")
public class SenlinVersion implements Version {
	private static final long serialVersionUID = -3019833373605658588L;

	@JsonProperty("status")
	private String status;
	@JsonProperty("id")
	private String id;
	@JsonProperty("links")
	private List<Map<String, String>> links;

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public List<Map<String, String>> getLinks() {
		return links;
	}

	@Override
	public String toString() {
		return "SenlinVersion{" +
				"status='" + status + '\'' +
				", id='" + id + '\'' +
				", links=" + links +
				'}';
	}

	/**
	 * An inner class for representing lists of senlinVersion
	 * 
	 * @author lion
	 * 
	 */
	public static class Version extends ListResult<SenlinVersion> {
		private static final long serialVersionUID = 6026392138824408084L;
		@JsonProperty("versions")
		private List<SenlinVersion> list;

		protected List<SenlinVersion> value() {
			return list;
		}
	}
}

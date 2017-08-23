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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.compute.ServerGroup;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;

@JsonRootName("server_group")
@JsonIgnoreProperties(ignoreUnknown=true)
public class NovaServerGroup implements ServerGroup{

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private List<String> members;
	private Map<String, String> metadata;
	private List<String> policies;

	public static NovaServerGroup create(String name, String policy) {
		NovaServerGroup ns = new NovaServerGroup();
		List<String> policyList = new ArrayList<String>();
		policyList.add(policy);
		ns.name = name;
		ns.policies = policyList;
		return ns;
	}


	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<String> getMembers() {
		return members;
	}
	@Override
	public Map<String, String> getMetadata() {
		return metadata;
	}

	@Override
	public List<String> getPolicies() {
		return policies;
	}



	public void setName(String name) {
		this.name = name;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public void setPolicies(List<String> policies) {
		this.policies = policies;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				   .add("id",id).add("name", name).add("members", members)
				   .add("policies", policies).add("metadata", metadata)
				   .toString();
	}

	public static class ServerGroups extends ListResult<NovaServerGroup> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("server_groups")
		private List<NovaServerGroup> serverGroups;

		@Override
		protected List<NovaServerGroup> value() {
			return serverGroups;
		}

	}



}

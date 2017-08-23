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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;

/**
 *
 * @author Vishvesh Deshmukh
 * @date Aug 18, 2016
 */
@JsonRootName("vim_project")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VimProject {

	private String id;

	private String name;

	@JsonProperty("project_domain_name")
	private String projectDomainName;

	public static VimProject create() {
		return new VimProject();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("id", id).add("name", name).add("projectDomainName", projectDomainName)
				.toString();
	}

	/**
	 * ID to Set
	 *
	 * @param id the id to set
	 * @return VimProject
	 */
	public VimProject id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Project Name to Set
	 *
	 * @param name the name to set
	 * @return VimProject
	 */
	public VimProject name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * ProjectDomainName to Set
	 *
	 * @param projectDomainName the projectDomainName to set
	 * @return VimProject
	 */
	public VimProject projectDomainName(String projectDomainName) {
		this.projectDomainName = projectDomainName;
		return this;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the projectDomainName
	 */
	public String getProjectDomainName() {
		return projectDomainName;
	}
}
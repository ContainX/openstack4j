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
import com.huawei.openstack4j.model.senlin.BuildInfo;

import java.util.Map;

/**
 * This is a model of a senlinBuild_info. It uses Jackson annotations for
 * (de)serialization into JSON format
 * 
 * @author lion
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("build_info")
public class SenlinBuildInfo implements BuildInfo {
	private static final long serialVersionUID = -7596480972776554810L;

	@JsonProperty("api")
	private Map<String, String> api;
	@JsonProperty("engine")
	private Map<String, String> engine;

	@Override
	public Map<String, String> getApi() {
		return api;
	}

	@Override
	public Map<String, String> getEngine() {
		return engine;
	}

	@Override
	public String toString() {
		return "SenlinBuildInfo{" +
				"api=" + api +
				", engine=" + engine +
				'}';
	}
}

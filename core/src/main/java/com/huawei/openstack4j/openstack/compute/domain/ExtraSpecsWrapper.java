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

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

/**
 * A Wrapper for flavor extra specs to get
 * 
 * @author Octopus Zhang
 */
public class ExtraSpecsWrapper implements ModelEntity {

	private static final long serialVersionUID = 1L;

	@JsonProperty("extra_specs")
	Map<String, String> extraSpecs;

	public ExtraSpecsWrapper() { }

	public ExtraSpecsWrapper(Map<String, String> extraSpecs) {
		this.extraSpecs = extraSpecs;
	}
	/**
	 * Wraps the given extraSpecs into the wrapper
	 * 
	 * @param extraSpecs
	 * @return extraSpecs wrapper
	 */
	public static ExtraSpecsWrapper wrap(Map<String, String> extraSpecs) {
		return new ExtraSpecsWrapper(extraSpecs);
	}
	
	/**
	 * @return the extraSpecs
	 */
	public Map<String, String> getExtraSpecs() {
		return extraSpecs;
	}

}

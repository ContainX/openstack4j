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

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * Maps to the OpenStack Floating IP Pool specification and used internally to retrieve the list of pool names
 * 
 * @author Jeremy Unruh
 *
 */
public class NovaFloatingIPPools extends ListResult<String> {

	private static final long serialVersionUID = 1L;

	@JsonProperty("floating_ip_pools")
	private List<Wrapper> values;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<String> value() {
		if (values != null)
			return Lists.transform(values, WrapperToStringFunc.instance);
		return Collections.emptyList();
	}

	static final class Wrapper {
		@JsonProperty("name")
		String name;
	}
	
	private static class WrapperToStringFunc implements Function<Wrapper, String> {

		static final WrapperToStringFunc instance = new WrapperToStringFunc();
		
		@Override
		public String apply(Wrapper input) {
			return input.name;
		}
		
	}
	
}

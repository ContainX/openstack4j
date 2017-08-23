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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * Nova OS Host contains a list of Nova Host Resources
 * 
 * @author Qin An
 */
public class NovaHost extends ListResult<NovaHostResource> {

	public static final long serialVersionUID = 1L;

	@JsonProperty("host")
	List<NovaHostResourceBody> hostItems;

	@Override
	protected List<NovaHostResource> value() {
		List<NovaHostResource> hostResources = new ArrayList<NovaHostResource>();
		for (NovaHostResourceBody body : hostItems) {
			hostResources.add((NovaHostResource) body.getHostResource());
		}
		return hostResources;
	}

}

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
import com.huawei.openstack4j.model.compute.ServerCreate;

/**
 * An entity for build a Server with Scheduler hints
 * 
 * @author octopus zhang
 */
public class NovaServerCreateWithHintsWrapper implements ModelEntity {

	private static final long serialVersionUID = 1L;

	@JsonProperty("os:scheduler_hints")
	private Map<String, Object> schedulerHints;
	private ServerCreate server;

	public Map<String, Object> getSchedulerHints() {
		return schedulerHints;
	}

	public ServerCreate getServer() {
		return server;
	}

	public static NovaServerCreateWithHintsWrapper wrap(ServerCreate server) {
	    NovaServerCreateWithHintsWrapper wrapper = new NovaServerCreateWithHintsWrapper();
	    wrapper.server = server;
	    wrapper.schedulerHints = server.getSchedulerHints();
	    return wrapper;
	}

}

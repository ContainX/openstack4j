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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

/**
 * Represents the get Console Output action with related parameters.
 * It is used to query the console output of a server.
 *
 * @author Lorenzo Biava
 */
@JsonRootName("os-getConsoleOutput")
public class ConsoleOutputOptions implements ModelEntity {

	private static final long serialVersionUID = 1L;

	@JsonProperty("length")
	private Integer length;

	/**
	 * To get the full console output.
	 */
	public ConsoleOutputOptions() {
		this.length = null;
	}

	/**
	 * To get the last <code>length</code> lines of the console output.
	 * @param length the number of lines to retrieve.
	 */
	public ConsoleOutputOptions(Integer length) {
		this.length = length;
	}

	public Integer getLength() {
		return length;
	}

}
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
package com.huawei.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.network.ext.LbPoolStats;

@JsonRootName("stats")
public class NeutronLbPoolStats implements LbPoolStats {
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("bytes_in")
	private Long bytesIn;
	@JsonProperty("bytes_out")
	private Long bytesOut;
	@JsonProperty("total_connections")
	private Integer totalConnections;
	@JsonProperty("active_connections")
	private Integer activeConnections;

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Long getBytesIn() {
		return bytesIn;
	}
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Long getBytesOut() {
		return bytesOut;
	}
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getTotalConnections() {
		return totalConnections;
	}
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getActiveConnections() {
		return activeConnections;
	}

}

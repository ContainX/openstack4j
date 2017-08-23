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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.compute.AbsoluteLimit;
import com.huawei.openstack4j.model.compute.Limits;
import com.huawei.openstack4j.model.compute.RateLimit;

import com.google.common.base.MoreObjects;

/**
 * Accounts may be pre-configured with a set of thresholds (or limits) to manage capacity and prevent abuse of the system.
 * The system recognizes two kinds of limits: rate limits and absolute limits. Rate limits are thresholds that are reset after a
 * certain amount of time passes. Absolute limits are fixed.
 *
 * @author Jeremy Unruh
 * @see http://docs.openstack.org/api/openstack-compute/2/content/Limits-d1e846.html
 */
@JsonRootName("limits")
public class NovaLimits implements Limits {

	private static final long serialVersionUID = 1L;

	private List<NovaRateLimit> rate;
	private NovaAbsoluteLimit absolute;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends RateLimit> getRate() {
		return rate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbsoluteLimit getAbsolute() {
		return absolute;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				    .add("rate", rate).add("absolute", absolute)
				    .toString();
	}

}

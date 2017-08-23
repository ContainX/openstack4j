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
package com.huawei.openstack4j.openstack.networking.domain;

import com.google.common.base.MoreObjects;

import com.huawei.openstack4j.model.network.HostRoute;

/**
 * A Network Host based Routing Entry.
 *
 * @author Jeremy Unruh
 */
public class NeutronHostRoute implements HostRoute {

	private static final long serialVersionUID = 1L;

	private String destination;
	private String nexthop;

	public NeutronHostRoute() {
	}

	public NeutronHostRoute(String destination, String nexthop) {
		this.destination = destination;
		this.nexthop = nexthop;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDestination() {
		return destination;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNexthop() {
		return nexthop;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				     .add("destination", destination).add("nexthop", nexthop).toString();
	}

}

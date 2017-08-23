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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.network.RouterInterface;

import com.google.common.base.MoreObjects;

/**
 * An interface data model which is returned during interface association with a router
 *
 * @author Jeremy Unruh
 */
public class NeutronRouterInterface implements RouterInterface {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;

	@JsonProperty("tenant_id")
	private String tenantId;

	@JsonProperty("subnet_id")
	private String subnetId;

	@JsonProperty("port_id")
	private String portId;

	public NeutronRouterInterface() { }

	public NeutronRouterInterface(String subnetId, String portId) {
		this.subnetId = subnetId;
		this.portId = portId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSubnetId() {
		return subnetId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPortId() {
		return portId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				     .add("id", id).add("subnetId", subnetId).add("portId", portId).add("tenantId", tenantId).toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return java.util.Objects.hash(id, subnetId, portId, tenantId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof NeutronRouterInterface) {
            NeutronRouterInterface that = (NeutronRouterInterface) obj;
			if (java.util.Objects.equals(id, that.id) &&
					java.util.Objects.equals(subnetId, that.subnetId) &&
					java.util.Objects.equals(portId, that.portId) &&
					java.util.Objects.equals(tenantId, that.tenantId)) {
				return true;
			}
		}
		return false;
	}

}

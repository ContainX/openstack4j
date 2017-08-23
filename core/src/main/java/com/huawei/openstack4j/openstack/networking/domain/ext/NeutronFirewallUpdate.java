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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.network.ext.FirewallUpdate;
import com.huawei.openstack4j.model.network.ext.builder.FirewallUpdateBuilder;

import com.google.common.base.MoreObjects;

/**
 * An entity used to update Neutron Firewall (FwaaS).
 *
 * @author Vishvesh Deshmukh
 */
@JsonRootName("firewall")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronFirewallUpdate implements FirewallUpdate {

	private static final long serialVersionUID = 1L;

	private String name;

	@JsonProperty("tenant_id")
	private String tenantId;

	private String description;

	private Boolean shared;

	@JsonProperty("admin_state_up")
    private Boolean adminStateUp;

	@JsonProperty("firewall_policy_id")
	private String policyId;

	/**
	 * Wrap this Firewall to a builder
	 * @return FirewallUpdateBuilder
	 */
	@Override
	public FirewallUpdateBuilder toBuilder() {
		return new FirewallUpdateConcreteBuilder(this);
	}

	/**
	 * @return FirewallUpdateBuilder
	 */
	public static FirewallUpdateBuilder builder() {
		return new FirewallUpdateConcreteBuilder();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getTenantId() {
		return tenantId;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Boolean isAdminStateUp() {
		return adminStateUp != null && adminStateUp;
	}

	@Override
	public Boolean isShared() {
		return shared != null && shared;
	}

	@Override
	public String getPolicy() {
		return policyId;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				.add("name", name).add("policyId", policyId)
				.add("shared", shared).add("adminStateUp", adminStateUp)
				.add("tenantId", tenantId).add("description", description)
				.toString();
	}

	public static class FirewallUpdateConcreteBuilder implements FirewallUpdateBuilder {
		NeutronFirewallUpdate f;

		@Override
		public FirewallUpdate build() {
			return f;
		}

		public FirewallUpdateConcreteBuilder() {
			this(new NeutronFirewallUpdate());
		}

		public FirewallUpdateConcreteBuilder(NeutronFirewallUpdate f) {
			this.f = f;
		}

		@Override
		public FirewallUpdateBuilder from(FirewallUpdate in) {
			this.f = (NeutronFirewallUpdate) in;
			return this;
		}

		@Override
		public FirewallUpdateBuilder tenantId(String tenantId) {
			f.tenantId = tenantId;
			return this;
		}

		@Override
		public FirewallUpdateBuilder name(String name) {
			f.name = name;
			return this;
		}

		@Override
		public FirewallUpdateBuilder description(String description) {
			f.description = description;
			return this;
		}

		@Override
		public FirewallUpdateBuilder adminStateUp(Boolean adminStateUp) {
			f.adminStateUp = adminStateUp;
			return this;
		}

		@Override
		public FirewallUpdateBuilder shared(Boolean shared) {
			f.shared = shared;
			return this;
		}

		@Override
		public FirewallUpdateBuilder policy(String policyId) {
			f.policyId = policyId;
			return this;
		}
	}

}

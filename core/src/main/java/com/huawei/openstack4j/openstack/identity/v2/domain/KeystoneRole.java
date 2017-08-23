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
package com.huawei.openstack4j.openstack.identity.v2.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.huawei.openstack4j.core.transport.internal.OSBadBooleanDeserializer;
import com.huawei.openstack4j.model.identity.v2.Role;
import com.huawei.openstack4j.model.identity.v2.builder.RoleBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;

/**
 * A User based Role - see {@link Role}
 *
 * @author Jeremy Unruh
 */
@JsonRootName("role")
public class KeystoneRole implements Role {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String description;
	@JsonDeserialize(using=OSBadBooleanDeserializer.class)
	private Boolean enabled = true;
	private String tenantId;
	private String serviceId;

	public static RoleBuilder builder() {
		return new RoleConcreteBuilder();
	}

	@Override
	public RoleBuilder toBuilder() {
		return new RoleConcreteBuilder(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isEnabled() {
		return (enabled != null && enabled);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getServiceId() {
		return serviceId;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
						.add("id", id).add("name", name).add("description", description)
						.add("enabled", enabled).add("tenantId", tenantId).add("serviceId", serviceId)
						.toString();
	}



	public static class Roles extends ListResult<KeystoneRole> {

		private static final long serialVersionUID = 1L;
		@JsonProperty("roles")
		private List<KeystoneRole> list;

		@Override
		protected List<KeystoneRole> value() {
			return list;
		}


	}

	public static class RoleConcreteBuilder implements RoleBuilder {

		private KeystoneRole model;

		RoleConcreteBuilder() {
			this(new KeystoneRole());
		}

		RoleConcreteBuilder(KeystoneRole model) {
			this.model = model;
		}

		public RoleBuilder id(String id) {
			model.id = id;
			return this;
		}

		public RoleBuilder tenantId(String tenantId) {
			model.tenantId = tenantId;
			return this;
		}

		public RoleBuilder name(String name) {
			model.name = name;
			return this;
		}

		public RoleBuilder description(String description) {
			model.description = description;
			return this;
		}

		public RoleBuilder enabled(boolean enabled) {
			model.enabled = enabled;
			return this;
		}

		@Override
		public Role build() {
			return model;
		}

		@Override
		public RoleBuilder from(Role in) {
			model = (KeystoneRole)in;
			return this;
		}

	}
}

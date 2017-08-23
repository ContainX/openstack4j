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
import com.huawei.openstack4j.model.identity.v2.Service;
import com.huawei.openstack4j.model.identity.v2.builder.ServiceBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;

/**
 * OpenStack service, such as Compute (Nova), Object Storage (Swift), or Image Service (Glance).
 * A service provides one or more endpoints through which users can access resources and perform
 *
 * @author Jeremy Unruh
 */
@JsonRootName("OS-KSADM:service")
public class KeystoneService implements Service {

	private static final long serialVersionUID = 1L;

	String id;
	String type;
	String name;
	String description;

	public static ServiceBuilder builder() {
		return new ServiceConcreteBuilder();
	}

	@Override
	public ServiceBuilder toBuilder() {
		return new ServiceConcreteBuilder(this);
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				   .add("id", id).add("name", name).add("type", type).add("description", description)
				   .toString();
	}

	public static class Services extends ListResult<KeystoneService> {

		private static final long serialVersionUID = 1L;
		@JsonProperty("OS-KSADM:services")
		private List<KeystoneService> list;

		public List<KeystoneService> value() {
			return list;
		}
	}

	public static class ServiceConcreteBuilder implements ServiceBuilder {

		private KeystoneService model;

		ServiceConcreteBuilder() {
			this(new KeystoneService());
		}

		ServiceConcreteBuilder(KeystoneService model) {
			this.model = model;
		}

		public ServiceBuilder name(String name) {
			model.name = name;
			return this;
		}

		public ServiceBuilder type(String type) {
			model.type = type;
			return this;
		}

		public ServiceBuilder description(String description) {
			model.description = description;
			return this;
		}

		@Override
		public Service build() {
			return model;
		}

		@Override
		public ServiceBuilder from(Service in) {
			model = (KeystoneService)in;
			return this;
		}

	}

}

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
package org.openstack4j.openstack.tacker.domain;

import org.openstack4j.model.tacker.VnfUpdate;
import org.openstack4j.model.tacker.builder.VnfUpdateBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;

/**
 * An entity used to update Tacker Vnf.
 *
 * @author Vishvesh Deshmukh
 * @date Aug 17, 2016
 */
@JsonRootName("vnf")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TackerVnfUpdate implements VnfUpdate {

	private static final long serialVersionUID = 1L;

	private VnfUpdateAttributes attributes;

	/**
	 * Wrap this VnfUpdate to a builder
	 * @return VnfUpdateBuilder
	 */
	@Override
	public VnfUpdateBuilder toBuilder() {
		return new VnfUpdateConcreteBuilder(this);
	}

	/**
	 * @return VnfUpdateBuilder
	 */
	public static VnfUpdateBuilder builder() {
		return new VnfUpdateConcreteBuilder();
	}

	@Override
	public VnfUpdateAttributes getAttributes() {
		return attributes;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("attributes", attributes).toString();
	}

	public static class VnfUpdateConcreteBuilder implements VnfUpdateBuilder {
		TackerVnfUpdate f;

		@Override
		public TackerVnfUpdate build() {
			return f;
		}

		public VnfUpdateConcreteBuilder() {
			this(new TackerVnfUpdate());
		}

		public VnfUpdateConcreteBuilder(TackerVnfUpdate f) {
			this.f = f;
		}

		@Override
		public VnfUpdateBuilder from(VnfUpdate in) {
			this.f = (TackerVnfUpdate) in;
			return this;
		}

		@Override
		public VnfUpdateBuilder attributes(VnfUpdateAttributes attributes) {
			f.attributes = attributes;
			return this;
		}
	}
}

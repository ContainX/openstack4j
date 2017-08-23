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
package com.huawei.openstack4j.openstack.senlin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.senlin.NodeActionCreate;
import com.huawei.openstack4j.model.senlin.NodeCreate;
import com.huawei.openstack4j.model.senlin.builder.NodeActionCreateBuilder;

import java.util.Map;

/**
 * This class contains all elements required for the creation of a SenlinNode. It
 * uses Jackson annotation for (de)serialization into JSON
 *
 * @author lion
 *
 */
public class SenlinNodeActionCreate implements NodeActionCreate {
	private static final long serialVersionUID = 7461515803108018710L;

	@JsonProperty("check")
	private Map<String, String> check;
	@JsonProperty("recover")
	private Map<String, String> recover;

	/**
	 * Returnes a {@link SenlinNodeActionCreateConcreteBuilder} for configuration and
	 * creation of a {@link SenlinNodeActionCreate} object.
	 *
	 * @return a {@link SenlinNodeActionCreateConcreteBuilder}
	 */
	public static SenlinNodeActionCreateConcreteBuilder build() {
		return new SenlinNodeActionCreateConcreteBuilder();
	}

	@Override
	public NodeActionCreateBuilder toBuilder() {
		return new SenlinNodeActionCreateConcreteBuilder(this);
	}


	/**
	 * A Builder to create a SenlinNode. Use {@link #build()} to receive the
	 * {@link NodeCreate} object.
	 *
	 * @author lion
	 *
	 */
	public static class SenlinNodeActionCreateConcreteBuilder implements
			NodeActionCreateBuilder {

		private SenlinNodeActionCreate model;

		/**
		 * Constructor to create a {@link SenlinNodeActionCreateConcreteBuilder} object
		 * with a new, empty {@link SenlinNodeActionCreate} object.
		 */
		public SenlinNodeActionCreateConcreteBuilder() {

			this(new SenlinNodeActionCreate());
		}

		/**
		 * Constructor for manipulation of an existing {@link SenlinNodeActionCreate}
		 * object.
		 *
		 * @param model
		 *            the {@link SenlinNodeActionCreate} object which is to be
		 *            modified.
		 */
		public SenlinNodeActionCreateConcreteBuilder(SenlinNodeActionCreate model) {
			this.model = model;
		}

		@Override
		public NodeActionCreate build() {
			return model;
		}

		@Override
		public NodeActionCreateBuilder from(NodeActionCreate in) {
			model = (SenlinNodeActionCreate) in;
			return this;
		}

		@Override
		public NodeActionCreateBuilder check(Map<String, String> check) {
			model.check = check;
			return this;
		}

		@Override
		public NodeActionCreateBuilder recover(Map<String, String> recover) {
			model.recover = recover;
			return this;
		}

	}

}

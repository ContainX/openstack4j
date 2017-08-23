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
package com.huawei.openstack4j.openstack.workflow.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.workflow.ActionDefinition;
import com.huawei.openstack4j.model.workflow.builder.ActionDefinitionBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * Mistral action definition.
 * 
 * @author Renat Akhmerov
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class MistralActionDefinition extends BaseDefinition implements ActionDefinition {

	private static final long serialVersionUID = 1L;

	private String input;

	public static MistralActionDefinitionBuilder builder() {
		return new MistralActionDefinitionBuilder();
	}
	
	@Override
	public MistralActionDefinitionBuilder toBuilder() {
		return new MistralActionDefinitionBuilder(this);
	}

	@Override
	public String getInput() {
		return input;
	}

	/**
	 * Mistral action definition builder.
	 *
	 * @author Renat Akhmerov
	 */
	public static class MistralActionDefinitionBuilder extends
			BaseDefinitionBuilder<MistralActionDefinitionBuilder, MistralActionDefinition>
			implements ActionDefinitionBuilder<MistralActionDefinitionBuilder, MistralActionDefinition> {

		MistralActionDefinitionBuilder() {
			this(new MistralActionDefinition());
		}

		MistralActionDefinitionBuilder(MistralActionDefinition model) {
			super(model);
		}

		@Override
		public MistralActionDefinitionBuilder from(MistralActionDefinition in) {
			return null;
		}

		@Override
		public MistralActionDefinitionBuilder input(String input) {
            this.model.input = input;

			return this;
		}
	}

	public static class MistralActionDefinitions extends ListResult<MistralActionDefinition> {
		private static final long serialVersionUID = 1L;

		@JsonProperty("actions")
		private List<MistralActionDefinition> list;

		@Override
		protected List<MistralActionDefinition> value() {
			return this.list;
		}
	}
}

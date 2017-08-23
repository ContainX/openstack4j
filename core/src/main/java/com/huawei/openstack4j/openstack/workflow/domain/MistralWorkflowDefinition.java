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

import com.fasterxml.jackson.annotation.*;
import com.huawei.openstack4j.model.workflow.WorkflowDefinition;
import com.huawei.openstack4j.model.workflow.builder.WorkflowDefinitionBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * Mistral workflow definition.
 * 
 * @author Renat Akhmerov
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class MistralWorkflowDefinition extends BaseDefinition implements WorkflowDefinition {

	private static final long serialVersionUID = 1L;

	private String input;

	public static MistralWorkflowDefinitionBuilder builder() {
		return new MistralWorkflowDefinitionBuilder();
	}
	
	@Override
	public MistralWorkflowDefinitionBuilder toBuilder() {
		return new MistralWorkflowDefinitionBuilder(this);
	}

	@Override
	public String getInput() {
		return input;
	}

	/**
	 * Mistral workflow definition builder.
	 *
	 * @author Renat Akhmerov
	 */
	public static class MistralWorkflowDefinitionBuilder extends
			BaseDefinitionBuilder<MistralWorkflowDefinitionBuilder, MistralWorkflowDefinition>
			implements WorkflowDefinitionBuilder<MistralWorkflowDefinitionBuilder, MistralWorkflowDefinition> {

		MistralWorkflowDefinitionBuilder() {
			this(new MistralWorkflowDefinition());
		}

		MistralWorkflowDefinitionBuilder(MistralWorkflowDefinition model) {
			super(model);
		}

		@Override
		public MistralWorkflowDefinitionBuilder from(MistralWorkflowDefinition in) {
			return null;
		}

		@Override
		public MistralWorkflowDefinitionBuilder input(String input) {
            this.model.input = input;

			return this;
		}
	}

	public static class MistralWorkflowDefinitions extends ListResult<MistralWorkflowDefinition> {
		private static final long serialVersionUID = 1L;

		@JsonProperty("workflows")
		private List<MistralWorkflowDefinition> list;

		@Override
		protected List<MistralWorkflowDefinition> value() {
			return this.list;
		}
	}
}

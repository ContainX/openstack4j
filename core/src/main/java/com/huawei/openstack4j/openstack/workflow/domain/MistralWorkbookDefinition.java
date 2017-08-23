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
import com.huawei.openstack4j.model.workflow.WorkbookDefinition;
import com.huawei.openstack4j.model.workflow.builder.WorkbookDefinitionBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * Mistral workbook definition.
 * 
 * @author Renat Akhmerov
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class MistralWorkbookDefinition extends BaseDefinition implements WorkbookDefinition {

	private static final long serialVersionUID = 1L;

	public static MistralWorkbookDefinitionBuilder builder() {
		return new MistralWorkbookDefinitionBuilder();
	}
	
	@Override
	public MistralWorkbookDefinitionBuilder toBuilder() {
		return new MistralWorkbookDefinitionBuilder(this);
	}

	/**
	 * Mistral workbook definition builder.
	 *
	 * @author Renat Akhmerov
	 */
	public static class MistralWorkbookDefinitionBuilder extends
			BaseDefinitionBuilder<MistralWorkbookDefinitionBuilder, MistralWorkbookDefinition>
			implements WorkbookDefinitionBuilder<MistralWorkbookDefinitionBuilder, MistralWorkbookDefinition> {

		MistralWorkbookDefinitionBuilder() {
			this(new MistralWorkbookDefinition());
		}

		MistralWorkbookDefinitionBuilder(MistralWorkbookDefinition model) {
			super(model);
		}

		@Override
		public MistralWorkbookDefinitionBuilder from(MistralWorkbookDefinition in) {
			return null;
		}
	}

	public static class MistralWorkbookDefinitions extends ListResult<MistralWorkbookDefinition> {
		private static final long serialVersionUID = 1L;

		@JsonProperty("workbooks")
		private List<MistralWorkbookDefinition> list;

		@Override
		protected List<MistralWorkbookDefinition> value() {
			return this.list;
		}
	}
}

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
package com.huawei.openstack4j.model.workflow.builder;

import java.util.Date;
import java.util.List;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.workflow.Definition;
import com.huawei.openstack4j.model.workflow.Scope;

/**
 * Builder for {@link Definition} model class.
 * 
 * @author Renat Akhmerov
 */
public interface DefinitionBuilder<T extends DefinitionBuilder<T, M>, M extends Definition>
        extends Builder<T, M> {
	/**
	 * @see Definition#getId()
	 */
	T id(String id);

	/**
	 * @see Definition#getName()
	 */
	T name(String name);

	/**
	 * @see Definition#getDefinition()
	 */
	T definition(String definition);

	/**
	 * @see Definition#getCreatedAt()
	 */
	T created(Date created);

	/**
	 * @see Definition#getUpdatedAt()
	 */
	T updated(Date definition);

	/**
	 * @see Definition#isSystem()
	 */
	T system(Boolean system);

	/**
	 * @see Definition#getTags()
	 */
	T tags(List<String> tags);

	/**
	 * @see Definition#getScope()
	 */
	T scope(Scope scope);

	/**
	 * @see Definition#getProjectId()
	 */
	T projectId(String projectId);
}

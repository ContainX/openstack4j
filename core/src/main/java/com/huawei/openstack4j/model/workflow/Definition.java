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
/*
 * 
 */
package com.huawei.openstack4j.model.workflow;

import java.util.Date;
import java.util.List;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.workflow.builder.DefinitionBuilder;
import com.huawei.openstack4j.model.workflow.builder.WorkflowDefinitionBuilder;


/**
 * Base interface for all definition objects.
 * 
 * @author Renat Akhmerov
 */
public interface Definition extends ModelEntity, Buildable<DefinitionBuilder> {

	/**
	 * @return The id of this definition.
	 */
	String getId();
	
	/**
	 * @return The name of this definition.
	 */
	String getName();

    /**
     * @return The text of this definition.
     */
	String getDefinition();

	/**
	 * @return the createdAt
	 */
	Date getCreatedAt();

	/**
	 * @return the updatedAt
	 */
	Date getUpdatedAt();

	/**
	 * @return {@code True} if the definition is system (not createdAt by user).
	 */
	Boolean isSystem();

    /**
     * @return User tags.
     */
	List<String> getTags();

	/**
	 * @return Definition scope.
	 */
	Scope getScope();

	/**
	 * @return Definition project ID.
	 */
	String getProjectId();
}

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
package com.huawei.openstack4j.model.heat.builder;

/**
 * The Orchestration builders
 */
public interface OrchestrationBuilders {

    /**
     * The builder to create a Template
     *
     * @return the TemplateBuilder
     */
    public TemplateBuilder template();

    /**
     * The builder to create a StackCreate
     *
     * @return the StackCreate builder
     */
    public StackCreateBuilder stack();

    /**
     * The builder to create a SoftwareConfig
     *
     * @return the software config builder
     */
    public SoftwareConfigBuilder softwareConfig();

    /**
     * The builder to create a StackUpdate
     *
     * @return the StackUpdate builder
     */
    public StackUpdateBuilder stackUpdate();

    /**
     * The builder to create a resource health update
     *
     * @return
     */
    public ResourceHealthBuilder resourceHealth();

}

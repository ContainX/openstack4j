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
package com.huawei.openstack4j.model.heat;

import java.util.Map;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * Base interface for Stack Creation or Update based API Services
 * 
 * @author Jeremy Unruh
 */
public interface BaseStackCreateUpdate extends ModelEntity {

    // Future versions: Replace with Template-Object
    /**
     * Returns the Heat template if it was stored in JSON format or YAML format
     * 
     * @return the JSON or YAML formatted template out of which the stack is to be
     *         created. Returns <code> null </code> if no JSON formatted template has been set.
     */
    String getTemplate();

    /**
     * Returns the parameters which are used for creation of the stack
     * 
     * @return Map of parameters. This map is <code> null </code> if no
     *         parameter has been set. Returns empty if no parameter has been
     *         set.
     */
    Map<String, String> getParameters();
}

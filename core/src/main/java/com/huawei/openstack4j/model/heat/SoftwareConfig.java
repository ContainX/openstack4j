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

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.heat.builder.SoftwareConfigBuilder;

/**
 * Software Configuration Model
 *
 * @author Jeremy Unruh
 */
public interface SoftwareConfig extends ModelEntity, Buildable<SoftwareConfigBuilder> {

    /**
     * @return the configuration identifier
     */
    String getId();
    
    /**
     * The name of this configuration
     * 
     * @return the name of this configuration
     */
    String getName();
    
    /**
     * Namespace that groups this software configuration by when it is delivered to a server. 
     * This setting might imply which configuration tool performs the configuration.
     * 
     * @return the namespace group
     */
    String getGroup();
    
    /**
     * Configuration script or manifest that defines which configuration is performed.
     * 
     * @return the configuration script
     */
    String getConfig();
    
    /**
     * List of inputs that this software configuration expects
     * 
     * @return list of inputs
     */
    List<? extends Input> getInputs();

    /**
     * List of outputs this software configuration produces
     * 
     * @return list of outputs
     */
    List<? extends Output> getOutputs();
    
    /**
     * Map containing options specific to the configuration management tool used by this resource.
     * 
     * @return map of options or null
     */
    Map<String, Object> getOptions();
    
    public interface Input {
        
        /**
         * @return the name of this input
         */
        String getName();
        
        /**
         * @return the description of this input
         */
        String getDescription();
        
        /**
         * @return default initialized value for this input
         */
        String getDefaultValue();
        
        /**
         * @return the input type
         */
        String getType();
    }
    
    public interface Output {
        
        /**
         * @return the name of this output
         */
        String getName();
        
        /**
         * @return the description of this output
         */
        String getDescription();
        
        /**
         * @return the data type of this output
         */
        String getType();
        
        /**
         * @return true if this is an error related output
         */
        boolean isErrorOutput();
    }
    
}

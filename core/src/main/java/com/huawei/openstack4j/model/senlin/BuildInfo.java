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
package com.huawei.openstack4j.model.senlin;

import java.util.Map;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * This interface describes the getter-methods (and thus components) of a BuildInfo.
 * All getters map to the possible return values of
 * <code> GET /v1/build-info</code>
 * 
 * @see http://developer.openstack.org/api-ref-clustering-v1.html
 * 
 * @author lion
 * 
 */
public interface BuildInfo extends ModelEntity {

    /**
     * Returns the api of the build info
     *
     * @return the api of the build info
     */
    Map<String, String> getApi();

    /**
     * Returns the engine of the build info
     *
     * @return the engine of the build info
     */
    Map<String, String> getEngine();
}

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
package com.huawei.openstack4j.api.compute.ext;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.model.compute.ext.Service;

/**
 * API which supports the "os-services" extension.
 *
 * @author Stephan Latour
 */
public interface ServicesService {

    /**
     * List services info
     *
     * NOTE: This is an extension and not all deployments support os-services
     *
     * @return a list of nova services
     */
    List<? extends Service> list();
    
    /**
     * Returns list of compute services filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     * @author Wang Ting/王婷
     */
    List<? extends Service> list(Map<String, String> filteringParams);

    /**
     * Enables a compute services.
     *
     * @param binary the name of the service binary that you want to enable
     * @param host the host name of the service that you want to enable
     * @return the status of the enabled service
     * 
     * @author Wang Ting/王婷
     */
    Service.Status enableService(String binary, String host);

    /**
     * Disables a service.
     *
     * @param binary the name of the service binary that you want to disable
     * @param host the host name of the service that you want to disable
     * @return the status of the disabled service
     * 
     * @author Wang Ting/王婷
     */
    Service.Status disableService(String binary, String host);

}
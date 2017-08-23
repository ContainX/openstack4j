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
package com.huawei.openstack4j.openstack.murano.v1.internal;


import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.murano.v1.*;

/**
 * This class contains getters for all implementation of the available Murano services
 *
 * @author Nikolay Mahotkin
 */
public class MuranoService extends BaseMuranoServices implements AppCatalogService {

    @Override
    public MuranoEnvironmentService environments() {
        return Apis.get(MuranoEnvironmentService.class);
    }

    @Override
    public MuranoSessionService sessions() {
        return Apis.get(MuranoSessionService.class);
    }

    @Override
    public MuranoApplicationService services() {
        return Apis.get(MuranoApplicationService.class);
    }

    @Override
    public MuranoDeploymentService deployments() {
        return Apis.get(MuranoDeploymentService.class);
    }

    @Override
    public MuranoActionService actions() {
        return Apis.get(MuranoActionService.class);
    }
}

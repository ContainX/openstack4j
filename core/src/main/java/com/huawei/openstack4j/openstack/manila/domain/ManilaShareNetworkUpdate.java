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
package com.huawei.openstack4j.openstack.manila.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.manila.ShareNetworkUpdateOptions;

/**
 * Object used to update existing share networks.
 *
 * @author Daniel Gonzalez Nothnagel
 */
@JsonRootName("share_network")
public class ManilaShareNetworkUpdate implements ModelEntity {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;

    private ManilaShareNetworkUpdate() {}

    /**
     * Creates a share network from options.
     * This can be used to update an existing share networks.
     *
     * @param options the share network update options
     * @return a share network with the given options
     */
    public static ManilaShareNetworkUpdate fromOptions(ShareNetworkUpdateOptions options) {
        ManilaShareNetworkUpdate shareNetworkUpdate = new ManilaShareNetworkUpdate();
        shareNetworkUpdate.name = options.getName();
        shareNetworkUpdate.description = options.getDescription();

        return shareNetworkUpdate;
    }
}

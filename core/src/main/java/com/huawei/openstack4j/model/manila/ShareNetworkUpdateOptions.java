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
package com.huawei.openstack4j.model.manila;

/**
 * Options used to update a share network.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public class ShareNetworkUpdateOptions {
    private String name;
    private String description;

    public static ShareNetworkUpdateOptions create() {
        return new ShareNetworkUpdateOptions();
    }

    /**
     * Update the share network name.
     *
     * @param name the share network name
     * @return ShareNetworkUpdateOptions
     */
    public ShareNetworkUpdateOptions name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Update the share network description.
     *
     * @param description the share network description
     * @return ShareNetworkUpdateOptions
     */
    public ShareNetworkUpdateOptions description(String description) {
        this.description = description;
        return this;
    }

    /**
     * @return the share network name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the share network description
     */
    public String getDescription() {
        return description;
    }
}

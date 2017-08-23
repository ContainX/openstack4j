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
package com.huawei.openstack4j.model.image.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The container format refers to whether the virtual machine image is in a file format that also
 * contains metadata about the actual virtual machine.
 *
 * Note that the container format string is not currently used by Glance or other OpenStack
 * components, so it is safe to simply specify {@link #BARE} as the container format if you are
 * unsure.
 *
 * @author emjburns
 */
public enum ContainerFormat {
    /**
     * This indicates there is no container or metadata envelope for the image
     */
    BARE,

    /**
     * This is the OVF container format
     */
    OVF,

    /**
     * This indicates what is stored in Glance is an Amazon kernel image
     */
    AKI,

    /**
     * This indicates what is stored in Glance is an Amazon ramdisk image
     */
    ARI,

    /**
     * This indicates what is stored in Glance is an Amazon machine image
     */
    AMI,

    /**
     * This indicates what is stored in Glance is an OVA tar archive file
     */
    OVA,

    /**
     * This is the Docker container format
     */
    DOCKER,

    /**
     * Type unknown
     */
    UNRECOGNIZED;

    @JsonValue
    public String value() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static ContainerFormat value(String cf) {
        if (cf == null || cf.isEmpty()) return UNRECOGNIZED;
        try
        {
            return valueOf(cf.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            return UNRECOGNIZED;
        }
    }
}

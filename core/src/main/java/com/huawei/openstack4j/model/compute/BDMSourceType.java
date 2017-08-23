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
package com.huawei.openstack4j.model.compute;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Block Device Mapping Source Type
 * 
 * @author Jeremy Unruh
 * @see http://docs.openstack.org/developer/nova/block_device_mapping.html
 */
public enum BDMSourceType {
    BLANK,
    IMAGE,
    SNAPSHOT,
    VOLUME
    ;
    
    @JsonCreator
    public static BDMSourceType value(String v) {
        if (v == null)
            return VOLUME;
        try {
            return valueOf(v.toUpperCase());
        } catch (IllegalArgumentException e) {
            return VOLUME;
        }
    }

    @JsonValue
    public String value() {
        return name().toLowerCase();
    }
    
}

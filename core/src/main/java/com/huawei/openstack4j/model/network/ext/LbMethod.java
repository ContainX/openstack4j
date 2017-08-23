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
package com.huawei.openstack4j.model.network.ext;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Load Balancer Algorithm
 * 
 * @author liujunpeng
 *
 */
public enum LbMethod {
    ROUND_ROBIN,
    LEAST_CONNECTIONS,
    SOURCE_IP;

    @JsonCreator
    public static LbMethod forValue(String value) {
        if (value != null)
        {
            for (LbMethod s : LbMethod.values()) {
                if (s.name().equalsIgnoreCase(value)) {
                    return s;
                }
            }
        }
        return LbMethod.ROUND_ROBIN;
    }
}

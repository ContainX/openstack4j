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
package com.huawei.openstack4j.model.common;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * Base Filter class for building Filter Request options
 * 
 * @author Jeremy Unruh
 *
 */
public class BaseFilter {

    private Map<String, Object> constraints = Maps.newHashMap();
    
    protected BaseFilter() {
        
    }
    
    protected void filter(String name, Object value) {
        constraints.put(name, value);
    }
    
    public Map<String, Object> getConstraints() {
        return constraints;
    }
    
}

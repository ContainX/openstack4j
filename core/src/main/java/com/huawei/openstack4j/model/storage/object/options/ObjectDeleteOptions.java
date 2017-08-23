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
package com.huawei.openstack4j.model.storage.object.options;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Options used for the deletion of Objects
 */
public final class ObjectDeleteOptions {

    public static final ObjectDeleteOptions NONE = new ObjectDeleteOptions();

    private Map<String, List<Object>> queryParams = Maps.newHashMap();

    private ObjectDeleteOptions() { }

    public static ObjectDeleteOptions create() {
        return new ObjectDeleteOptions();
    }

    public ObjectDeleteOptions queryParam(String key, Object value) {
        if (value == null)
            return this;

        if (queryParams.containsKey(key)) {
            List<Object> list = queryParams.get(key);
            list.add(value);
        } else {
            List<Object> list = new ArrayList<Object>();
            list.add(value);
            queryParams.put(key, list);
        }
        return this;
    }

    public Map<String, List<Object>> getQueryParams() {
        return queryParams;
    }
}

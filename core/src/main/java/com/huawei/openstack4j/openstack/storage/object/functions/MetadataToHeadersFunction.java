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
package com.huawei.openstack4j.openstack.storage.object.functions;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

/**
 * Transforms a MetaHeaderRequestWrapper which applies headers based on a prefix to the outbound
 * HttpRequest
 * 
 * @author Jeremy Unruh
 */
public class MetadataToHeadersFunction implements Function<Map<String, String>, Map<String, String>> {

    private String prefix;
    
    private MetadataToHeadersFunction(String prefix) {
        this.prefix = prefix;
    }
    
    /**
     * Creates a new Metadata to Headers function
     * 
     * @param prefix the prefix used for headers
     * @return MetadataToHeadersFunction
     */
    public static <R> MetadataToHeadersFunction create(String prefix) {
        return new MetadataToHeadersFunction(prefix);
    }
    
    /**
     * Transforms metadata raw values into header form values
     * 
     * @param metadata the map of metadata 
     * @return map of metadata in header format
     */
    @Override
    public Map<String, String> apply(Map<String, String> metadata) {
        
        Map<String, String> headers = Maps.newHashMap();
        
        for (String key : metadata.keySet()) {
            String keyLower = key.toLowerCase();
            String value = metadata.get(key);
            if (keyLower.startsWith(prefix.toLowerCase()))
                headers.put(keyLower, value);
            else
                headers.put(String.format("%s%s", prefix, keyLower), value);
        }
        return headers;
    }

}

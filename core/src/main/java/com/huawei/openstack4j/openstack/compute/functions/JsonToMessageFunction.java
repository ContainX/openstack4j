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
package com.huawei.openstack4j.openstack.compute.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Function;

/**
 * Attempts to extract an error message from a JSON payload.  If the message cannot be found then the original 
 * JSON string is returned
 * 
 * @author Jeremy Unruh
 */
public class JsonToMessageFunction implements Function<String, String> {

    public static final JsonToMessageFunction INSTANCE = new JsonToMessageFunction();
    private static final Pattern MESSAGE_PATTERN = Pattern.compile(".*message\\\":\\s\\\"([^\"]+)\\\".*");
    
    
    @Override
    public String apply(String json) {
        if (json != null && json.contains("message")) {
            Matcher m = MESSAGE_PATTERN.matcher(json);
            if (m.matches())
                return m.group(1);
        }
        return json;
    }

}

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
package com.huawei.openstack4j.model.common.functions;

import com.google.common.base.Function;

import com.huawei.openstack4j.model.common.header.HeaderNameValue;
import com.huawei.openstack4j.model.common.header.Range;

/**
 * Transforms a Header Range array into a single HeaderNameValue object
 * 
 * @author Jeremy Unruh
 */
public class RangesToHeaderNameValue implements Function<Range[], HeaderNameValue> {

    public static final RangesToHeaderNameValue INSTANCE = new RangesToHeaderNameValue();
    
    @Override
    public HeaderNameValue apply(Range[] input) {
        if (input == null || input.length == 0)
            return null;

        if (input.length == 1)
            return input[0].toHeader();
        
        String name = input[0].toHeader().getName();
        StringBuilder value = new StringBuilder(String.valueOf(input[0].toHeader().getValue()));
        
        for (int i = 1; i < input.length; i++) {
            value.append(",");
            value.append(input[i].value());
        }
        
        return new HeaderNameValue(name, value.toString());
    }

}

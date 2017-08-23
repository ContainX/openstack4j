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
package com.huawei.openstack4j.openstack.common.functions;

import java.util.List;

import com.google.common.base.Function;

/**
 * A Function which returns one entry from a List or null
 * @author Jeremy Unruh
 * 
 * @param <T> The return Type
 */
public class OneOrNull<T> implements Function<List<T>, T> {

    public static <T> OneOrNull<T> create() {
        return new OneOrNull<T>();
    }
     
    @Override
    public T apply(List<T> input) {
        if (input != null && input.size() > 0)
            return input.get(0);
        return null;
    }

}

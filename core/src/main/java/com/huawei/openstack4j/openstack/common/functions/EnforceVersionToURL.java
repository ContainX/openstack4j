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

import com.google.common.base.Function;

/**
 * Removes a trailing version and appends the specified version to an endpoint URL
 * 
 * @author Jeremy Unruh
 */
public class EnforceVersionToURL implements Function<String, String> {

    private static final String VALIDATE = "-VALID-";
    
    private final String version;
    private boolean onlyIfAbsent;
    
    private EnforceVersionToURL(String version) {
        this(version, false);
    }
    
    private EnforceVersionToURL(String version, boolean onlyIfAbsent) {
        this.version = version;
        this.onlyIfAbsent = onlyIfAbsent;
    }
    
    public static EnforceVersionToURL instance(String version) {
        return new EnforceVersionToURL(version);
    }
    
    public static EnforceVersionToURL instance(String version,  boolean onlyIfAbsent) {
        return new EnforceVersionToURL(version, onlyIfAbsent);
    }
    
    
    @Override
    public String apply(String input) {
        if (onlyIfAbsent && input.replaceFirst(RemoveVersionFromURL.VERSION_REGEX, VALIDATE).contains(VALIDATE))
            return input;
        return RemoveVersionFromURL.INSTANCE.apply(input).concat(version);
    }
}

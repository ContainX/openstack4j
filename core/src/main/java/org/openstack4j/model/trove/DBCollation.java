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
package org.openstack4j.model.trove;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by sumit gandhi on 9/3/2016.
 */
public enum DBCollation {

    UTF8_GENERAL_CI,
    UTF16_GENERAL_CI,
    UTF32_GENERAL_CI,
    UNRECOGNIZED
    ;

    @JsonValue
    public String value() { return name().toLowerCase(); }

    @JsonCreator
    public static DBCollation value(String paramType) {
        try {
            return valueOf(paramType.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            return UNRECOGNIZED;
        }
    }

}

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
package com.huawei.openstack4j.openstack.manila.domain.actions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.manila.Access;

/**
 * Grant access to a share.
 *
 * @author Daniel Gonzalez Nothnagel
 */
@JsonRootName("os-allow_access")
public class GrantAccessAction implements ShareAction {
    private static final long serialVersionUID = 1L;

    @JsonProperty("access_level")
    private Access.Level accessLevel;
    @JsonProperty("access_type")
    private Access.Type accessType;
    @JsonProperty("access_to")
    private String accessTo;

    GrantAccessAction(Access.Level accessLevel, Access.Type accessType, String accessTo) {
        this.accessLevel = accessLevel;
        this.accessType = accessType;
        this.accessTo = accessTo;
    }

    public Access.Level getAccessLevel() {
        return accessLevel;
    }

    public Access.Type getAccessType() {
        return accessType;
    }

    public String getAccessTo() {
        return accessTo;
    }
}

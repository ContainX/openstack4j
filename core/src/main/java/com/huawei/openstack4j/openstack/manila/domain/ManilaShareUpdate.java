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
package com.huawei.openstack4j.openstack.manila.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.manila.ShareUpdateOptions;

/**
 * Object used to update existing shares.
 *
 * @author Daniel Gonzalez Nothnagel
 */
@JsonRootName(("share"))
public class ManilaShareUpdate implements ModelEntity {
    private static final long serialVersionUID = 1L;

    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("display_description")
    private String displayDescription;
    @JsonProperty("is_public")
    private Boolean isPublic;

    private ManilaShareUpdate() {}

    public static ManilaShareUpdate fromOptions(ShareUpdateOptions options) {
        ManilaShareUpdate shareUpdate = new ManilaShareUpdate();
        shareUpdate.displayName = options.getDisplayName();
        shareUpdate.displayDescription = options.getDisplayDescription();
        shareUpdate.isPublic = options.isPublic();

        return shareUpdate;
    }
}

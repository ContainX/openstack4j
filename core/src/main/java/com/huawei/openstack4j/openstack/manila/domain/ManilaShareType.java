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
import com.huawei.openstack4j.model.manila.ExtraSpecs;
import com.huawei.openstack4j.model.manila.ShareType;
import com.huawei.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * A share type enables you to filter or choose back ends before you create a share.
 *
 * @author Daniel Gonzalez Nothnagel
 */
@JsonRootName("share_type")
public class ManilaShareType implements ShareType {
    private String id;
    @JsonProperty("required_extra_specs")
    private ExtraSpecs requiredExtraSpecs;
    @JsonProperty("extra_specs")
    private ExtraSpecs extraSpecs;
    @JsonProperty("os-share-type-access:is_public")
    private Boolean osShareTypeAccessIsPublic;
    private String name;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public ExtraSpecs getRequiredExtraSpecs() {
        return requiredExtraSpecs;
    }

    @Override
    public ExtraSpecs getExtraSpecs() {
        return extraSpecs;
    }

    @Override
    public Boolean getOsShareTypeAccessIsPublic() {
        return osShareTypeAccessIsPublic;
    }

    @Override
    public String getName() {
        return name;
    }

    public static class ShareTypes extends ListResult<ManilaShareType> {
        private static final long serialVersionUID = 1L;

        @JsonProperty("share_types")
        private List<ManilaShareType> shareTypes;

        @Override
        protected List<ManilaShareType> value() {
            return shareTypes;
        }
    }
}

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
package com.huawei.openstack4j.model.manila;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * Access details for a share type.
 *
 * @author Daniel Gonazalez Nothnagel
 */
public class ShareTypeAccess implements ModelEntity {
    private static final long serialVersionUID = 1L;

    @JsonProperty("share_type_id")
    private String shareTypeId;
    @JsonProperty("project_id")
    private String projectId;

    public String getShareTypeId() {
            return shareTypeId;
        }

    public String getProjectId() {
            return projectId;
        }

    public static class ShareTypeAccessList extends ListResult<ShareTypeAccess> {
        private static final long serialVersionUID = 1L;

        @JsonProperty("share_type_access")
        List<ShareTypeAccess> shareTypeAccessList;

        @Override
        protected List<ShareTypeAccess> value() {
            return shareTypeAccessList;
        }
    }
}

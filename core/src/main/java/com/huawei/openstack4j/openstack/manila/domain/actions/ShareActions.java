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

import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.manila.Share;
import com.huawei.openstack4j.model.manila.actions.AccessOptions;

/**
 * Simple Actions classes used for share action invocation.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public final class ShareActions {
    @JsonRootName("os-access_list")
    public static class ListAccessRulesAction implements ShareAction {}

    @JsonRootName("os-force_delete")
    public static class ForceDeleteAction implements ShareAction {}

    public static GrantAccessAction grantAccess(AccessOptions options) {
        return new GrantAccessAction(options.getAccessLevel(), options.getAccessType(), options.getAccessTo());
    }
    public static RevokeAccessAction revokeAccess(String accessId) {
        return new RevokeAccessAction(accessId);
    }

    public static ListAccessRulesAction listAccessRules() {
        return new ListAccessRulesAction();
    }

    public static ResetStateAction resetState(Share.Status status) {
        return new ResetStateAction(status);
    }

    public static ForceDeleteAction forceDelete() {
        return new ForceDeleteAction();
    }

    public static SizeAction.Extend extend(int newSize) {
        return new SizeAction.Extend(newSize);
    }

    public static SizeAction.Shrink shrink(int newSize) {
        return new SizeAction.Shrink(newSize);
    }
}

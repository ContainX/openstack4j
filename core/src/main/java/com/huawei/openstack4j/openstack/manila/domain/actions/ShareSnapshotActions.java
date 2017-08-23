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
import com.huawei.openstack4j.model.manila.ShareSnapshot;

/**
 * Actions used for share snapshot action invocation.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public class ShareSnapshotActions {
    @JsonRootName("os-reset_status")
    public static class ResetStateAction implements ShareSnapshotAction {
        private ShareSnapshot.Status status;

        ResetStateAction(ShareSnapshot.Status status) {
            this.status = status;
        }

        public ShareSnapshot.Status getStatus() {
            return status;
        }
    }

    @JsonRootName("os-force_delete")
    public static class ForceDeleteAction implements ShareSnapshotAction {}

    public static ResetStateAction resetState(ShareSnapshot.Status status) {
        return new ResetStateAction(status);
    }

    public static ForceDeleteAction forceDelete() {
        return new ForceDeleteAction();
    }
}

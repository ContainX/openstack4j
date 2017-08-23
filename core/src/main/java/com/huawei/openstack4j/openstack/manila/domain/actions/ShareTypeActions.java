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

/**
 * Actions classes used for share type action invocation.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public final class ShareTypeActions {
    private static class ShareTypeAccessAction implements ShareTypeAction {
        private String project;

        private ShareTypeAccessAction(String project) {
            this.project = project;
        }

        private String getProject() {
            return project;
        }
    }

    @JsonRootName("addProjectAccess")
    public static class AddShareTypeAccessAction extends ShareTypeAccessAction {
        AddShareTypeAccessAction(String project) {
            super(project);
        }
    }

    @JsonRootName("removeProjectAccess")
    public static class RemoveShareTypeAccessAction extends ShareTypeAccessAction {
        RemoveShareTypeAccessAction(String project) {
            super(project);
        }
    }

    public static AddShareTypeAccessAction addShareTypeAccess(String project) {
        return new AddShareTypeAccessAction(project);
    }

    public static RemoveShareTypeAccessAction removeShareTypeAccess(String project) {
        return new RemoveShareTypeAccessAction(project);
    }
}

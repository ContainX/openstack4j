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
package com.huawei.openstack4j.api.manila;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.manila.ShareServer;

/**
 * Share Server Service for Manila Shared Filesystems.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareServerService extends RestService {
    /**
     * Lists all share servers.
     *
     * @return a list containing all share servers
     */
    List<? extends ShareServer> list();

    /**
     * Shows details for a share server.
     *
     * @param shareServerId the share server ID
     * @return the share server or null if not found
     */
    ShareServer get(String shareServerId);

    /**
     * Deletes a share server.
     *
     * @param shareServerId the share server ID
     * @return the action response
     */
    ActionResponse delete(String shareServerId);
}

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
import com.huawei.openstack4j.model.manila.ShareInstance;

/**
 * Share Instance Service for Manila Shared Filesystems.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareInstanceService extends RestService {
    /**
     * Lists all share instances.
     *
     * @return a list of all share instances
     */
    List<? extends ShareInstance> list();

    /**
     * Shows details for a share instance.
     *
     * @param shareInstanceId the share instance ID
     * @return the share instance or null if not found
     */
    ShareInstance get(String shareInstanceId);

    /**
     * Administrator only. Explicitly updates the state of a share instance.
     *
     * @param shareInstanceId the share instance ID
     * @param status the status to set
     * @return the action response
     */
    ActionResponse resetState(String shareInstanceId, ShareInstance.Status status);

    /**
     * Administrator only. Force-deletes a share instance.
     *
     * @param shareInstanceId the share instance ID
     * @return the action response
     */
    ActionResponse forceDelete(String shareInstanceId);
}

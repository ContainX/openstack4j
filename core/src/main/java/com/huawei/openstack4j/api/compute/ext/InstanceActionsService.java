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
package com.huawei.openstack4j.api.compute.ext;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.compute.InstanceAction;

/**
 * API to list executed instance actions.
 * 
 * @author Christian Banse
 */
public interface InstanceActionsService extends RestService {

    /**
     * List the executed actions on the specified {@code serverId}
     *
     * @param serverId
     *            the server id
     * @return List of instance actions
     */
    List<? extends InstanceAction> list(String serverId);

    /**
     * Shows information about a specified instance action
     *
     * @param serverId
     *            the server id
     * @param requestId
     *            the request identifier
     * @return the instance action
     */
    InstanceAction get(String serverId, String requestId);

}

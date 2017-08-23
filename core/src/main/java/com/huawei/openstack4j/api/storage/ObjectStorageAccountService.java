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
package com.huawei.openstack4j.api.storage;

import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.storage.object.SwiftAccount;

/**
 * The Object Storage Account based services
 * 
 * @author Jeremy Unruh
 */
public interface ObjectStorageAccountService extends RestService {

    /**
     * Gets the {@link SwiftAccount}.
     *
     * @return The {@link SwiftAccount} object.
     */
    SwiftAccount get();
    
    /**
     * Creates or updates the {@link SwiftAccount} metadata.
     * 
     * @param metadata the metadata to create or update.
     * @return true if the metadata was created or updated successfully, 
     *         false if not
     */
    boolean updateMetadata(Map<String, String> metadata);
    
    /**
     * Deletes the {@link SwiftAccount} metadata.
     * 
     * @param metadata the metadata to delete.
     * @return true if the metadata was deleted successfully, 
     *         false if not
     */
    boolean deleteMetadata(Map<String, String> metadata);
    
    /**
     * Replaces the temporary URL key for the {@link SwiftAccount}
     * 
     * @param temporaryUrlKey the temporary URL key
     * @return true if the update was successful
     */
    boolean updateTemporaryUrlKey(String temporaryUrlKey);
}

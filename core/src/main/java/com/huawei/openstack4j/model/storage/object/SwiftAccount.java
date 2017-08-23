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
package com.huawei.openstack4j.model.storage.object;

import java.util.Map;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * An Account representation for OpenStack Object Storage (Swift)
 * 
 * @author Jeremy Unruh
 */
public interface SwiftAccount extends ModelEntity {

    long getContainerCount();

    /**
     * The total number of objects that are stored in storage for the account.
     * 
     * @return total number of objects
     */
    long getObjectCount();
    
    /**
     * The total number of bytes that are stored in storage for the account.
     * 
     * @return total number of bytes
     */
    long getBytesUsed();
    
    /**
     * The secret key value for temporary URLs. If not set (null), this header is not returned by this operation.
     * 
     * @return the secret key value or null
     */
    String getTemporaryUrlKey();
    
    /**
     * The custom account metadata map
     * 
     * @return map of name to value of metadata
     */
    Map<String, String> getMetadata();
    
}

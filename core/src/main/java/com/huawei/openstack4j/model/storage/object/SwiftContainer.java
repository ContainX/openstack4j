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
 * Represents an OpenStack Swift Container which holds Objects
 * 
 * @author Jeremy Unruh
 */
public interface SwiftContainer extends ModelEntity {

    /**
     * The name of the Container
     * 
     * @return the container name
     */
    String getName();

    /**
     * The current object count for this container
     * 
     * @return the number of objects
     */
    int getObjectCount();

    /**
     * The total size of all the objects within this contain in bytes
     * 
     * @return total size in bytes
     */
    long getTotalSize();
    
    /**
     * The metadata for the current container.  NOTE: This is a lazy call to the server and will invoke each time this
     * method is called.
     * @return The metadata for this container
     */
    Map<String, String> getMetadata();
}

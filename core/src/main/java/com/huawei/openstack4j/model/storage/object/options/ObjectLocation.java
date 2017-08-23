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
package com.huawei.openstack4j.model.storage.object.options;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Holds location information for an Object (Container and Object name including path)
 * 
 * @author Jeremy Unruh
 */
public final class ObjectLocation {

    private String containerName;
    private String objectName;
    
    
    private ObjectLocation(String containerName, String objectName) {
        super();
        checkNotNull(containerName, "ContainerName cannot be null");
        checkNotNull(objectName, "ObjectName cannot be null");

        this.containerName = containerName;
        this.objectName = objectName;
    }
    
    public static ObjectLocation create(String containerName, String objectName) {
        return new ObjectLocation(containerName, objectName);
    }

    public String getContainerName() {
        return containerName;
    }

    public String getObjectName() {
        return objectName;
    }
    
    public String getURI() {
        return String.format("/%s/%s", containerName, objectName);
    }
}

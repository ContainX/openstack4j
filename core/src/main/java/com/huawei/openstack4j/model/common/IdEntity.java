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
package com.huawei.openstack4j.model.common;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * A simple entity which supports encapsulating an identifier
 * 
 * @author Jeremy Unruh
 */
public interface IdEntity extends ModelEntity {

    /**
     * @return the identifier for this resource
     */
    String getId();
    
    /**
     * Sets the identifier for this resource.  Note: creating a new resource should not have the idenfier set since OpenStack will 
     * assign one on the create call
     * 
     * @param id the identifier
     */
    void setId(String id);
}

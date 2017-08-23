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
package com.huawei.openstack4j.model.manila;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.manila.builder.ShareTypeCreateBuilder;

/**
 * Object used to create new share types.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareTypeCreate extends ModelEntity, Buildable<ShareTypeCreateBuilder> {
    /**
     * @return the extra specifications for the share type
     */
    ExtraSpecs getExtraSpecs();

    /**
     * @return indicates whether a share type is publicly accessible
     */
    Boolean getOsShareTypeAccessIsPublic();

    /**
     * @return the share type name
     */
    String getName();
}

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
package com.huawei.openstack4j.model.manila.builder;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.manila.ExtraSpecs;
import com.huawei.openstack4j.model.manila.ShareTypeCreate;

/**
 * Builds a {@link com.huawei.openstack4j.model.manila.ShareTypeCreate}.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareTypeCreateBuilder extends Buildable.Builder<ShareTypeCreateBuilder, ShareTypeCreate> {
    /**
     * Adds an extra specification for the share type.
     *
     * @param key the key of the extra specification
     * @param value the value of the extra specification
     * @return ShareTypeCreateBuilder
     */
    ShareTypeCreateBuilder addExtraSpec(String key, String value);

    /**
     * Set extra specifications for the share type.
     *
     * @param extraSpecs the extra specifications
     * @return ShareTypeCreateBuilder
     */
    ShareTypeCreateBuilder extraSpecs(ExtraSpecs extraSpecs);

     /**
      * Set whether a share type is publicly accessible. Default is true, or publicly accessible.
      *
      * @param osShareTypeAccessIsPublic whether the share type is public accessible
      * @return ShareTypeCreateBuilder
      */
     ShareTypeCreateBuilder osShareTypeAccessIsPublic(boolean osShareTypeAccessIsPublic);

    /**
     * Set the share type name.
     *
     * @param name the share type name
     * @return ShareTypeCreateBuilder
     */
    ShareTypeCreateBuilder name(String name);
}

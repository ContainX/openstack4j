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
package com.huawei.openstack4j.model.gbp;

import java.util.List;

import com.huawei.openstack4j.model.common.Resource;
/**
 * External Policy Model Entity
 * 
 * @author vinod borole
 */
public interface ExternalPolicy extends Resource{
    /**
     * Gets the consumed policy rule sets Ids
     *
     * @return the consumed policy rule sets ids
     */
    List<String> getConsumedPolicyRuleSets();

    /**
     * Gets the provided policy rule sets Ids
     *
     * @return the provided policy rule sets ids
     */
    List<String> getProvidedPolicyRuleSets();

    /**
     * Gets the external segments associated with this external policy Ids
     *
     * @return the external segment ids
     */
    List<String> getExternalSegments();
   
    /**
     * Gets the description
     *
     * @return the description
     */
    String getDescription();

    /**
     * Is external policy shared
     *
     * @return the true if shared and false if not shared
     */
    boolean isShared();

} 

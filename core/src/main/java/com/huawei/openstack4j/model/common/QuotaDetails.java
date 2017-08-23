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
 * Details for a specific Quota with a Quota-Set
 * 
 * @author Jeremy Unruh
 *
 */
public interface QuotaDetails extends ModelEntity {

    /**
     * The number of items in use for this tenant
     * 
     * @return number of items in use
     */
    int getInUse();
    
    /**
     * The number of items permitted for this tenant.
     * 
     * @return number of items permitted
     */
    int getLimit();
    
    /**
     * The number of reserved items for this tenant
     * 
     * @return the number of reserved items
     */
    int getReserved();
}

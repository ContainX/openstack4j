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
package com.huawei.openstack4j.openstack.common;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.common.QuotaDetails;

/**
 * Quota Details Entity describing quota usage
 *
 * @author Jeremy Unruh
 */
public class QuotaDetailsEntity implements QuotaDetails {

    private static final long serialVersionUID = 1L;

    @JsonProperty("in_use")
    private int inUse;
    @JsonProperty("limit")
    private int limit;
    @JsonProperty("reserved")
    private int reserved;

    @Override
    public int getInUse() {
        return inUse;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public int getReserved() {
        return reserved;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("in_use", inUse).add("limit", limit).add("reserved", reserved).toString();
    }

}

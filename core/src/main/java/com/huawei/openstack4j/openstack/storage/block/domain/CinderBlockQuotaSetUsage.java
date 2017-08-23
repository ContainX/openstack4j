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
package com.huawei.openstack4j.openstack.storage.block.domain;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.common.QuotaDetails;
import com.huawei.openstack4j.model.storage.block.BlockQuotaSetUsage;
import com.huawei.openstack4j.openstack.common.QuotaDetailsEntity;

/**
 * Block Quota-Set Usage Details
 *
 * @author Jeremy Unruh
 */
@JsonRootName("quota_set")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CinderBlockQuotaSetUsage implements BlockQuotaSetUsage {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private QuotaDetailsEntity snapshots;
    @JsonProperty
    private QuotaDetailsEntity volumes;
    @JsonProperty
    private QuotaDetailsEntity gigabytes;


    @Override
    public QuotaDetails getSnapshots() {
        return snapshots;
    }

    @Override
    public QuotaDetails getVolumes() {
        return volumes;
    }

    @Override
    public QuotaDetails getGigabytes() {
        return gigabytes;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("snapshots", snapshots).add("volumes", volumes).add("gigabytes", gigabytes).toString();
    }

}

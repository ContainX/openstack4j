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
package com.huawei.openstack4j.openstack.manila.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.manila.QuotaSet;

/**
 * Set of quotas for shares.
 *
 * @author Daniel Gonzalez Nothnagel
 */
@JsonRootName("quota_set")
public class ManilaQuotaSet implements QuotaSet {
    private String id;
    private Integer gigabytes;
    private Integer snapshots;
    private Integer shares;
    @JsonProperty("snapshot_gigabytes")
    private Integer snapshotGigabytes;
    @JsonProperty("share_networks")
    private Integer shareNetworks;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Integer getGigabytes() {
        return gigabytes;
    }

    @Override
    public Integer getSnapshots() {
        return snapshots;
    }

    @Override
    public Integer getShares() {
        return shares;
    }

    @Override
    public Integer getSnapshotGigabytes() {
        return snapshotGigabytes;
    }

    @Override
    public Integer getShareNetworks() {
        return shareNetworks;
    }
}

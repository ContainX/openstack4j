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
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.manila.QuotaSetUpdateOptions;

/**
 * Object used to update quotas.
 *
 * @author Daniel Gonzalez Nothnagel
 */
@JsonRootName("quota_set")
public class ManilaQuotaSetUpdate implements ModelEntity {
    private Integer gigabytes;
    private Integer snapshots;
    private Integer shares;
    @JsonProperty("snapshot_gigabytes")
    private Integer snapshotGigabytes;
    @JsonProperty("share_networks")
    private Integer shareNetworks;
    private Boolean force;

    private ManilaQuotaSetUpdate() {}

    /**
     * Creates a quota set update from options.
     *
     * @param options the quota set update options
     * @return a quota set update with the given options
     */
    public static ManilaQuotaSetUpdate fromOptions(QuotaSetUpdateOptions options) {
        ManilaQuotaSetUpdate quotaSetUpdate = new ManilaQuotaSetUpdate();
        quotaSetUpdate.gigabytes = options.getGigabytes();
        quotaSetUpdate.snapshots = options.getSnapshots();
        quotaSetUpdate.shares = options.getShares();
        quotaSetUpdate.snapshotGigabytes = options.getSnapshotGigabytes();
        quotaSetUpdate.shareNetworks = options.getShareNetworks();
        quotaSetUpdate.force = options.getForce();

        return quotaSetUpdate;
    }
}

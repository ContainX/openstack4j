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

/**
 * Options used to update a quota set.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public class QuotaSetUpdateOptions {
    private Integer gigabytes;
    private Integer snapshots;
    private Integer shares;
    private Integer snapshotGigabytes;
    private Integer shareNetworks;
    private Boolean force;

    public static QuotaSetUpdateOptions create() {
        return new QuotaSetUpdateOptions();
    }

    public QuotaSetUpdateOptions force(Boolean force) {
        this.force = force;
        return this;
    }

    public QuotaSetUpdateOptions gigabytes(Integer gigabytes) {
        this.gigabytes = gigabytes;
        return this;
    }

    public QuotaSetUpdateOptions snapshots(Integer snapshots) {
        this.snapshots = snapshots;
        return this;
    }

    public QuotaSetUpdateOptions shares(Integer shares) {
        this.shares = shares;
        return this;
    }

    public QuotaSetUpdateOptions snapshotGigabytes(Integer snapshotGigabytes) {
        this.snapshotGigabytes = snapshotGigabytes;
        return this;
    }

    public QuotaSetUpdateOptions shareNetworks(Integer shareNetworks) {
        this.shareNetworks = shareNetworks;
        return this;
    }

    public Integer getGigabytes() {
        return gigabytes;
    }

    public Integer getSnapshots() {
        return snapshots;
    }

    public Integer getShares() {
        return shares;
    }

    public Integer getSnapshotGigabytes() {
        return snapshotGigabytes;
    }

    public Integer getShareNetworks() {
        return shareNetworks;
    }

    public Boolean getForce() {
        return force;
    }
}

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

import com.huawei.openstack4j.model.manila.AbsoluteLimit;

/**
 * Absolute limits contain information about:
 * <ul>
 * <li>Total maximum share memory, in GBs.</li>
 * <li>Number of share-networks.</li>
 * <li>Number of share-snapshots.</li>
 * <li>Number of shares.</li>
 * <li>Shares and total used memory, in GBs.</li>
 * <li>Snapshots and total used memory, in GBs.</li>
 * </ul>
 *
 * @author Daniel Gonzalez Nothnagel
 */
public class ManilaAbsoluteLimit implements AbsoluteLimit {
    private static final long serialVersionUID = 1L;

    private int maxTotalShareGigabytes;
    private int maxTotalSnapshotGigabytes;
    private int maxTotalShares;
    private int maxTotalShareSnapshots;
    private int maxTotalShareNetworks;
    private int totalSharesUsed;
    private int totalShareSnapshotsUsed;
    private int totalShareNetworksUsed;
    private int totalShareGigabytesUsed;
    private int totalSnapshotGigabytesUsed;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxTotalShareGigabytes() {
        return maxTotalShareGigabytes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxTotalSnapshotGigabytes() {
        return maxTotalSnapshotGigabytes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxTotalShares() {
        return maxTotalShares;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxTotalShareSnapshots() {
        return maxTotalShareSnapshots;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxTotalShareNetworks() {
        return maxTotalShareNetworks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalSharesUsed() {
        return totalSharesUsed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalShareSnapshotsUsed() {
        return totalShareSnapshotsUsed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalShareNetworksUsed() {
        return totalShareNetworksUsed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalShareGigabytesUsed() {
        return totalShareGigabytesUsed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalSnapshotGigabytesUsed() {
        return totalSnapshotGigabytesUsed;
    }
}

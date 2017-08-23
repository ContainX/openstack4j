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
public interface AbsoluteLimit {
    /**
     * @return the total number of share gigabytes that are allowed in a project
     */
    int getMaxTotalShareGigabytes();

    /**
     * @return the total maximum number of snapshot gigabytes that are allowed in a project
     */
    int getMaxTotalSnapshotGigabytes();

    /**
     * @return the total maximum number of shares that are allowed in a project
     */
    int getMaxTotalShares();

    /**
     * @return the total maximum number of share snapshots that are allowed in a project
     */
    int getMaxTotalShareSnapshots();

    /**
     * @return the total maximum number of share-networks that are allowed in a project
     */
    int getMaxTotalShareNetworks();

    /**
     * @return the total number of created shares in a project
     */
    int getTotalSharesUsed();

    /**
     * @return the total number of created share snapshots in a project
     */
    int getTotalShareSnapshotsUsed();

    /**
     * @return the total number if created share-networks in a project
     */
    int getTotalShareNetworksUsed();

    /**
     * @return the total number if gigabytes used in aproject by shares
     */
    int getTotalShareGigabytesUsed();

    /**
     * @return the total number of gigabytes used in a project by snapshots
     */
    int getTotalSnapshotGigabytesUsed();
}
